package ncl.ac.uk.newcastle.car;

/**
 * Implementation of a Small Car, Adds methods unique the Small Car such as the 
 * fuel usage and the capacity of the cars. 
 * Super constructor is used to init the fields
 **/

public final class SmallCar extends CarFactory {
	private final int capacity = 49;
	private final int fuelUsage = 20;
    
    /**
     * Uses the superConstructor to init the fields
     * @param Registration number
     **/
	SmallCar(String registrationNumber) {
		super(registrationNumber);
	}

    /**
    *gets the capacity of the SmallCar, overriding the Car Interface.
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
	 * Returns constant fuelUsage For a small car. 
     * @param distance driven
	 * @return returns fuel Consumption
	 **/
	public int getConsumption(final int driven) {
		return fuelUsage;
	}

}
