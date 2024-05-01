package pl.edu.pw.fizyka.pojava.nguyen;

import java.util.List;

public class SirCalculator implements Runnable {
	private double transRate, recoveryRate;
	private int gridSizeM, gridSizeN, numOfSims, simTime;
	private List<Integer> initialDistX;
	private List<Integer> initialDistY;
	private String[][] grid;
	private DataCallback callback;
	
	 public SirCalculator(DataCallback callback) {
	        this.callback = callback;
	    }
	
	// method allowing passing of the simulation parameters to this class
	public void loadParameters(double transRate, double recoveryRate, int gridSizeM,
			int gridSizeN, int numOfSims, int simTime, List<List<Integer>> initialDistXY) {
		
		this.transRate = transRate;
		this.recoveryRate = recoveryRate;		
		this.gridSizeM = gridSizeM;
		this.gridSizeN = gridSizeN;
		this.numOfSims = numOfSims;
		this.simTime = simTime;		
		this.initialDistX = initialDistXY.get(0);
		this.initialDistY = initialDistXY.get(1);
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
	public List<Integer> getInitialDistX(){
		return this.initialDistX;
	}
	public List<Integer> getInitialDistY(){
		return this.initialDistY;
	}
	
	// test function outputting parameters to console
	public void printParamsToConsole() {
		System.out.println("started calculation");
		System.out.println("transRate: " + transRate);
		System.out.println("recoveryRate: " + recoveryRate);
		System.out.println("gridSizeM: " + gridSizeM);
		System.out.println("gridSizeN: " + gridSizeN);
		System.out.println("numOfSims: " + numOfSims);
		System.out.println("simTime: " + simTime);
		System.out.print("Initial infected dist: ");		
		for(int i=0; i<initialDistX.size();i++) {
			System.out.print(initialDistX.get(i) + "," + initialDistY.get(i) + " ");
		}
		System.out.println();
	}
	
	@Override
    public void run() {
		// Create initial grid of the requested size and initial distribution
		grid = new String[gridSizeM][gridSizeN];		
		for(int aa=0; aa<gridSizeM; aa++) {
			for(int bb=0; bb<gridSizeN; bb++) {
				grid[aa][bb] = "S";
			}
		}		
		for(int cc=0; cc<initialDistX.size(); cc++) {
			grid[initialDistX.get(cc)][initialDistY.get(cc)] = "I";
		}
		
		/*for (int ii = 0; ii < grid.length; ii++) {
		    for (int jj = 0; jj < grid[ii].length; jj++) {
		        System.out.print(grid[ii][jj] + " ");
		    }
		    System.out.println();
		}
		System.out.println();*/
		
		// Calculation logic as described in the specification of the project
		String currentState;				
		for(int dd=0; dd<simTime; dd++) {
			// Create a new grid as a copy of the initial grid
			String[][] nextGrid = new String[grid.length][];
			for (int kk = 0; kk < grid.length; kk++) {
			    nextGrid[kk] = new String[grid[kk].length];
			    for (int ll = 0; ll < grid[kk].length; ll++) {
			        nextGrid[kk][ll] = grid[kk][ll];
			    }
			}
			
			for(int ee=0; ee<gridSizeM; ee++) {
				for(int ff=0; ff<gridSizeN; ff++) {
					currentState = grid[ee][ff];
					if(currentState == "S") {
						int startRow = Math.max(0, ee - 1);
						int endRow = Math.min(gridSizeM, ee + 2);
						int startCol = Math.max(0, ff - 1);
						int endCol = Math.min(gridSizeN, ff + 2);
						outerLoop:
						for (int gg = startRow; gg < endRow; gg++) {
						    for (int hh = startCol; hh < endCol; hh++) {
						        if(grid[gg][hh] == "I") {						        	
						        	if(Math.random() < transRate) {
						        		nextGrid[ee][ff] = "I";						        		
						        	} else {
						        		nextGrid[ee][ff] = "S";
						        	}
						        	break outerLoop;
						        }
						    } 
						}
					} 
					else if (currentState == "I") {
				    	if(Math.random() < recoveryRate) {
				    		nextGrid[ee][ff] = "R"; 
				    	} else {
				    		nextGrid[ee][ff] = "I";
				    	}
				    }
					else if (currentState == "R") {
						nextGrid[ee][ff] = "R";
					}
				}
			}
			grid = nextGrid;
			// Send data to callback       
	        callback.onDataCalculated(grid);
		}
		        
    }
}
