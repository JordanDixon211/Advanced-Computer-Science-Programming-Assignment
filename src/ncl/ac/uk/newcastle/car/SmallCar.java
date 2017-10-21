package ncl.ac.uk.newcastle.car;

public final class SmallCar extends AbstractCar {
    private final int capacity = 49;

    public SmallCar(String registrationNumber) {
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
