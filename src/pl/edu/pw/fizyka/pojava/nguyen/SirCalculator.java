package pl.edu.pw.fizyka.pojava.nguyen;

import java.util.concurrent.BlockingQueue;

public class SirCalculator implements Runnable {
	private double transRate, recoveryRate;
	private int gridSizeM, gridSizeN, numOfSims, simTime;
	private int[] initialDistX, initialDistY;
	private double[][] totalSIROverTime;	
	private short[][] grid; 
	private BlockingQueue<short[][]> queue;
	private volatile boolean running = false;
	
	 public SirCalculator(BlockingQueue<short[][]> queue) {
	        this.queue = queue;	        
	 }
	
	// method allowing passing of the simulation parameters to this class
	public void loadParameters(double transRate, double recoveryRate, int gridSizeM,
			int gridSizeN, int numOfSims, int simTime, int[] initialDistX, int[] initialDistY) {
		// simulation parameters
		this.transRate = transRate;
		this.recoveryRate = recoveryRate;		
		this.gridSizeM = gridSizeM;
		this.gridSizeN = gridSizeN;
		this.numOfSims = numOfSims;
		this.simTime = simTime;		
		this.initialDistX = initialDistX;
		this.initialDistY = initialDistY;
	}
	
	// function calculating current total number of S or I or R in a grid
	private int getTotalSIR(short[][] grid, short state) {
		int susceptible = 0;
		int infected = 0;
		int removed = 0;
		for(int aa=0; aa<grid.length; aa++) {
			for(int bb=0; bb<grid[aa].length; bb++) {
				if(grid[aa][bb] == 0) susceptible++;
				if(grid[aa][bb] == 1) infected++;
				if(grid[aa][bb] == 2) removed++;
			}
		}	
		if(state == 0) {
			return susceptible;
		} else if(state == 1) {
			return infected;
		} else {
			return removed;
		}
	}
	
	// Getter functions
	public double getTransRate() {
		return this.transRate;
	}
	public double getRecoveryRate() {
		return this.recoveryRate;
	}
	public int getGridSizeM() {
		return this.gridSizeM;
	}
	public int getGridSizeN() {
		return this.gridSizeN;
	}
	public int getNumOfSims() {
		return this.numOfSims;
	}
	public int getSimTime() {
		return this.simTime;
	}
	public int[] getInitialDistX(){
		return this.initialDistX;
	}
	public int[] getInitialDistY(){
		return this.initialDistY;
	}	
	public boolean isRunning() {
		return this.running;
	}
	public double[][] getTotalSIROverTime(){
		return this.totalSIROverTime;
	}
	
	// method to stop the calculation 
	public void stop() {
		this.running = false;
		this.queue.clear();
	}
	
	// Before iterating over the grid, create initial grid and add data to totalSIROverTime[][] at timeStep = 0
	private void performStep0Calc() {
		// Create initial grid of the requested size and initial distribution
		grid = new short[gridSizeM][gridSizeN];		
		for(int aa=0; aa<gridSizeM; aa++) {
			for(int bb=0; bb<gridSizeN; bb++) {
				grid[aa][bb] = 0; 
			}
		}	
		for(int cc=0; cc<initialDistX.length; cc++) {
			int xValue = initialDistX[cc];
			int yValue = initialDistY[cc];
			grid[xValue][yValue] = 1; ;
		}
		
		try {
            // Put data into the queue
            queue.put(grid);	                
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		//totalSIROverTime = new double[simTime+1][3];
		totalSIROverTime[0][0] += getTotalSIR(grid, (short) 0 /*"S"*/);
		totalSIROverTime[0][1] += getTotalSIR(grid, (short) 1 /*"I"*/);
		totalSIROverTime[0][2] += getTotalSIR(grid, (short) 2 /*"R"*/);		
	}
	
	// Calculation thread 
	@Override
    public void run() {	
		// indicate that the calculation is running
		this.running = true;		
		// Declaring helper variables to be used in the calculation loop
		int timeStep = 1;
		short currentState; 		
		int startRow;
		int endRow;
		int startCol;
		int endCol;
		// array for storing average SIR data which can be used to draw a graph
		totalSIROverTime = new double[simTime+1][3];
		// starting the calculation loop
		for(int ii=0; ii<this.numOfSims; ii++) { // outer loop running whole simulation N times
			performStep0Calc();	// before starting, we create initial grid at time step = 0 manually		
			while(timeStep<simTime+1 && running) { // main loop for epidemic spread calculation over simTime
				// Create a new grid as a copy of the initial grid
				short[][] nextGrid = new short[grid.length][]; 
				for (int kk = 0; kk < grid.length; kk++) {
				    nextGrid[kk] = new short[grid[kk].length];
				    for (int ll = 0; ll < grid[kk].length; ll++) {
				        nextGrid[kk][ll] = grid[kk][ll];
				    }
				}
				// Looping over each cell in the grid and alter their states (S,I,R) accordingly
				for(int ee=0; ee<gridSizeM; ee++) {
					for(int ff=0; ff<gridSizeN; ff++) {
						currentState = grid[ee][ff];
						if(currentState == 0) {
							startRow = Math.max(0, ee - 1);
							endRow = Math.min(gridSizeM, ee + 2);
							startCol = Math.max(0, ff - 1);
							endCol = Math.min(gridSizeN, ff + 2);
							outerLoop:
							for (int gg = startRow; gg < endRow; gg++) {
							    for (int hh = startCol; hh < endCol; hh++) {
							        if(grid[gg][hh] == 1) {						        	
							        	if(Math.random() < transRate) {
							        		nextGrid[ee][ff] = 1;						        		
							        	} else {
							        		nextGrid[ee][ff] = 0;
							        	}
							        	break outerLoop;
							        }
							    } 
							}
						} 
						else if (currentState == 1) {
					    	if(Math.random() < recoveryRate) {
					    		nextGrid[ee][ff] = 2; 
					    	} else {
					    		nextGrid[ee][ff] = 1;
					    	}
					    }
						else if (currentState == 2) {
							nextGrid[ee][ff] = 2;
						}
					}
				}
				// now the just calculated and changed grid becomes the old grid
				grid = nextGrid;
				// push the just calculated grid to queue for LeftSubPanelGrid class to display
				try {	                
	                queue.put(grid);	                
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
				// At each time step, total S,I,R amounts in the grid is saved in the array
				// if simulation is run more than 1 time, they add up to be averaged out later
				totalSIROverTime[timeStep][0] += getTotalSIR(grid, (short)0 /*"S"*/);
				totalSIROverTime[timeStep][1] += getTotalSIR(grid, (short)1 /*"I"*/);
				totalSIROverTime[timeStep][2] += getTotalSIR(grid, (short)2 /*"R"*/);
				
				timeStep++;
			}
			timeStep = 1;
		}
		// here, average out the S,I,R amounts in the array		
		for(int jj=0; jj<this.simTime+1;jj++) {
			totalSIROverTime[jj][0] = totalSIROverTime[jj][0]/this.numOfSims;
			totalSIROverTime[jj][1] = totalSIROverTime[jj][1]/this.numOfSims;
			totalSIROverTime[jj][2] = totalSIROverTime[jj][2]/this.numOfSims;
		}
		// indicate the calculation is not running anymore
		this.running = false;		
    }
}
