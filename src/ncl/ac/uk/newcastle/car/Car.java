package ncl.ac.uk.newcastle.car;

import ncl.ac.uk.newcastle.rentable.Rentable;

public interface Car extends Rentable{
    public String getRegistration();
    public int getCapacity();
    public int getCurrentFuelLoad();
    public boolean isFull();
    public int drive(int drive);
    public int addFuel(int fuelAmount);
    public int addFuel();

}
