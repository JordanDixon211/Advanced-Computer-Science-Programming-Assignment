package ncl.ac.uk.newcastle.car;

public final class SmallCar extends  CarFactory {
    private final int capacity = 49;

     SmallCar( String registrationNumber) {
        super(registrationNumber);
                }

@Override
public int getCapacity() {
        return capacity;
        }

@Override
public boolean isFull() {
        return super.getCurrentFuelLoad() == getCapacity();
        }
}
