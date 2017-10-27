package ncl.ac.uk.newcastle.car;

import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of a Large Car, Adds methods unique the Large Car such as the 
 * fuel usage and the capacity of the cars. 
 * Super constructor is used to init the fields
 **/

public final class  LargeCar extends CarFactory {
    private final int capacity = 60;
    private final int fuelUsage = 10;
    private final int fuelUsageOverFifty = 15;
    
    /**
     * Uses the superConstructor to init the fields
     * @param Registration number
     **/
      LargeCar(String registrationNumber) {
    	super(registrationNumber);
    }

      
     /**
     *gets the capacity of the largeCar, overriding the Car Interface.
  	 * @return returns the capacity of the car
  	 **/
    @Override
    public int getCapacity() {
        return capacity;
    }

    
    /**
	 * Compares the superMethod of fueldLoad and compares to the capacity
 	 * @return returns true if the currentFuelLoad is the same as the capacity 
 	 **/
    @Override
    public boolean isFull() {
        return super.getCurrentFuelLoad() == getCapacity();
    }

    
    /**
     * Large Car consumes fuel at different rate depending on how much distance is covered,
     * to cater if statment is used to see how much is driven and returns different values depending on
     * that
     * @param distance driven
	 * @return returns fuel Consumption
	 **/
	public int getConsumption(final int driven) {
		if(driven > 49) {
			return fuelUsageOverFifty;
		}else {
			return fuelUsage;
		}
	}
}
