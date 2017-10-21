package ncl.ac.uk.newcastle.car;

public interface Car {
    public String getRegistration();
    public int getCapacity();
    public int getCurrentFuelLoad();
    public boolean isFull();
    public int drive(int drive);
    public int addFuel(int fuelAmount);
    public int addFuel();

}
