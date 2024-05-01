package pl.edu.pw.fizyka.pojava.nguyen;

import java.util.List;

public class SirCalculator {
	private double transRate, recoveryRate;
	private int gridSizeM, gridSizeN, numOfSims, simTime;
	private List<Integer> initialDistX;
	private List<Integer> initialDistY;
	
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
	}
		
}
