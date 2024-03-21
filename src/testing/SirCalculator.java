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
		
}
