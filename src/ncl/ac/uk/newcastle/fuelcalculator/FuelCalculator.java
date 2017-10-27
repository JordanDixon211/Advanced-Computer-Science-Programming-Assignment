package ncl.ac.uk.newcastle.fuelcalculator;

import ncl.ac.uk.newcastle.car.SmallCar;

/**
 * makes use of the FuelAble interface to only get methods needed for this calculation
 * gives a separation between car and calculations regarding fuel usage.
 * **/
public final class FuelCalculator {
	
	/**
	 * private method to split the parts of a registrationNumber, returns null
	 * to the caller if the registration is not workable, eg number where a string should be.
	 * @param Fuelable Interface used, returns only method needed for this calculation
	 * @param distance driven.
	 * @return the amount of fuel used
	 * **/
    public int calculateUsage(FuelAble fuel, int driven){
    	int count = 0;
    	for(int i = 0; i <= driven;i++) {
    		int temp = i / fuel.getConsumption(i);
    		if(temp != count) {
    			count += 1;
    		}
    	}
    	
    	return count;
    }
}
