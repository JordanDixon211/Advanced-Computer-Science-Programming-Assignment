package ncl.ac.uk.newcastle.fuelcalculator;
/**
 * used as a mixin for the carFactory, 
 * returns fuel consumption of a vehicle 
 **/
public interface FuelAble {
   public int getConsumption(final int driven);
}
