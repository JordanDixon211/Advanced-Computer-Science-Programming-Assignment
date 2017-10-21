package ncl.ac.uk.newcastle.car;

import ncl.ac.uk.newcastle.fuelcalculator.FuelCalculator;
import ncl.ac.uk.newcastle.fuelcalculator.IFuel;

public abstract class AbstractCar implements Car, IFuel {
    private final String registration;
    private int fuelLoad;
    private final FuelCalculator fuelCalculator = new FuelCalculator();

    //defensive Programming, ensuring the reg number is correctly fortmatted. if not correctly formatted then throw illegalArg exception.
    public AbstractCar(String registrationNumber) {
        String[] stringSplit = registrationNumber.split(" ");
        if (stringSplit.length == 2) {
            char[] firstString = stringSplit[0].toCharArray();
            if (firstString.length == 4) {
                for (int i = 0; i < firstString.length; i++) {
                    if (i < 2) {
                        if (firstString[i] > -1 && firstString[i] < 10) {
                            continue;
                        } else {
                            throw new IllegalArgumentException("Illegal Registration Number: a String is included in the first two spaces.");
                        }
                    }

                    if (i > 2 && i < 5) {
                        if (firstString[i] >= 'A' && firstString[i] <= 'Z') {
                            continue;
                        } else {
                            throw new IllegalArgumentException("Illegal Registration Number: a number is included in the 3'rd or 4th space.");
                        }
                    }
                }
            }
        }

        char[] secondString = stringSplit[1].toCharArray();
        if (secondString.length == 3) {
            for (int i = 0; i < secondString.length; i++) {
                if (i < 2) {
                    if (secondString[i] >= 'A' && secondString[i] <= 'Z') {
                        continue;
                    } else {
                        throw new IllegalArgumentException("Illegal Registration Number: a String is included in the first two spaces.");
                    }
                }
            }
            registration = registrationNumber;
            return;
        }
        throw new IllegalArgumentException("Illegal Registration Number");
    }

    @Override
    public String getRegistration() {
        return this.registration;
    }

    @Override
    public int getCurrentFuelLoad() {
        return this.fuelLoad;
    }

    @Override
    public int drive(int drive) {
        if (fuelLoad < 1){
            return  -1;
        }
        return this.fuelCalculator.calculateUsage(this, drive);
    }

    @Override
    public int addFuel(int fuelAmount) {
        if (this.getCurrentFuelLoad() + fuelAmount > this.getCapacity()){
            return -1;
        }

        return this.fuelLoad += fuelAmount;
    }

    //add fuel to the capacity.
    @Override
    public int addFuel() {
        int fuelToAdd = this.getCapacity() - this.fuelLoad;
        if (fuelToAdd < 0){
            return -1;
        }

        this.fuelLoad += fuelToAdd;
        return fuelToAdd;
    }

    private FuelCalculator getFuelCalculator(){
        return fuelCalculator;
    }

    //needs to return kilom per usage
    @Override
    public int fuelUsage() {
        return getCapacity();
    }

    @Override
    public int fuelCapacity(){
        return getCapacity();
    }
}
