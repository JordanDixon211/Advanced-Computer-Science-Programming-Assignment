package ncl.ac.uk.newcastle.car;

import ncl.ac.uk.newcastle.rentable.Rentable;
/**
 * Interface for creating cars, 
 * creates base functionality for each car
 * Extends Rentable as a car has capability to be 
 * a Rentable car.
 * **/
public interface Car extends Rentable{
    public String getRegistration();
    public int getCapacity();
    public int getCurrentFuelLoad();
    public boolean isFull();
    public int drive(final int drive);
    public int addFuel(final int fuelAmount);
    public int addFuel();

}
