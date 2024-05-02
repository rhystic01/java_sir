package pl.edu.pw.fizyka.pojava.nguyen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SirCalculator implements Runnable {
	private double transRate, recoveryRate;
	private int gridSizeM, gridSizeN, numOfSims, simTime;
	private int[] initialDistX, initialDistY;
	private int[][] totalSIROverTime;	
	private short[][] grid; //private String[][] grid;
	private BlockingQueue<short[][]> queue; //private BlockingQueue<String[][]> queue;
	private volatile boolean running = false;
	
	 public SirCalculator(/*BlockingQueue<String[][]>*/ BlockingQueue<short[][]> queue) {
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
		// adding initial total number of S, I, R
		/*this.numOfSusceptible[0] = (this.gridSizeM * this.gridSizeN) - this.initialDistX.length;
		this.numOfInfected[0] = this.initialDistX.length;
		this.numOfRemoved[0] = 0;*/
	}
	
	// function calculating current total number of S, I, R in the grid
	private int getTotalSIR(/*String[][] grid, String state*/ short[][] grid, short state) {
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
	public int[][] getTotalSIROverTime(){
		return this.totalSIROverTime;
	}
	
	// method to stop the calculation 
	public void stop() {
		this.running = false;
		this.queue.clear();
	}
	
	// Calculation thread 
	@Override
    public void run() {	
		this.running = true;
		// Create initial grid of the requested size and initial distribution
		grid = new short[gridSizeM][gridSizeN];		
		for(int aa=0; aa<gridSizeM; aa++) {
			for(int bb=0; bb<gridSizeN; bb++) {
				grid[aa][bb] = 0; //grid[aa][bb] = "0";
			}
		}	
		for(int cc=0; cc<initialDistX.length; cc++) {
			int xValue = initialDistX[cc];
			int yValue = initialDistY[cc];
			grid[xValue][yValue] = 1; //grid[xValue][yValue] = "1";
		}
		
		try {
            // Put data into the queue
            queue.put(grid);	                
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		totalSIROverTime = new int[simTime][3];
		totalSIROverTime[0][0] = getTotalSIR(grid, (short) 0 /*"S"*/);
		totalSIROverTime[0][1] = getTotalSIR(grid, (short)1/*"I"*/);
		totalSIROverTime[0][2] = getTotalSIR(grid, (short) 2/*"R"*/);
		
		// Calculation logic as described in the specification of the project
		short currentState; //String currentState;
		int time = 1;
		while(time<simTime && running) {
			// Create a new grid as a copy of the initial grid
			short[][] nextGrid = new short[grid.length][]; //String[][] nextGrid = new String[grid.length][];
			for (int kk = 0; kk < grid.length; kk++) {
			    nextGrid[kk] = new short[grid[kk].length];
			    for (int ll = 0; ll < grid[kk].length; ll++) {
			        nextGrid[kk][ll] = grid[kk][ll];
			    }
			}
			
			for(int ee=0; ee<gridSizeM; ee++) {
				for(int ff=0; ff<gridSizeN; ff++) {
					currentState = grid[ee][ff];
					if(currentState == 0) {
						int startRow = Math.max(0, ee - 1);
						int endRow = Math.min(gridSizeM, ee + 2);
						int startCol = Math.max(0, ff - 1);
						int endCol = Math.min(gridSizeN, ff + 2);
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
			grid = nextGrid;
			try {
                // Put data into the queue
                queue.put(grid);	                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			totalSIROverTime[time][0] = getTotalSIR(grid, (short)0 /*"S"*/);
			totalSIROverTime[time][1] = getTotalSIR(grid, (short)1 /*"I"*/);
			totalSIROverTime[time][2] = getTotalSIR(grid, (short)2 /*"R"*/);
			
			time++;
		}
		time = 1;
		this.running = false;		
    }
}
