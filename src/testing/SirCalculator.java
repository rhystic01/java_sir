package testing;

import java.util.List;

public class SirCalculator {
	private double transRate, recoveryRate;
	private int gridSizeM, gridSizeN, numOfSims, simTime;
	
	public void loadParameters(List<Double> doubleParameters, List<Integer> integerParameters) {
		
		this.transRate = doubleParameters.get(0);
		this.recoveryRate = doubleParameters.get(1);
		
		this.gridSizeM = integerParameters.get(0);
		this.gridSizeN = integerParameters.get(1);
		this.numOfSims = integerParameters.get(2);
		this.simTime = integerParameters.get(3);
	}
	
	public void test() {
		System.out.println("started calculation");
		System.out.println("transRate: " + transRate);
		System.out.println("recoveryRate: " + recoveryRate);
		System.out.println("gridSizeM: " + gridSizeM);
		System.out.println("gridSizeN: " + gridSizeN);
		System.out.println("numOfSims: " + numOfSims);
		System.out.println("simTime: " + simTime);
	}
		
}
