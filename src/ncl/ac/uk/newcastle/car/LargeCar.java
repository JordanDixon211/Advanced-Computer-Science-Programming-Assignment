package ncl.ac.uk.newcastle.car;

public final class  LargeCar extends CarFactory {
    private final int capacity = 60;
     LargeCar(String registrationNumber) {
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
