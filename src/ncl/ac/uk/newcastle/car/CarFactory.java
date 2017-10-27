package ncl.ac.uk.newcastle.car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ncl.ac.uk.newcastle.fuelcalculator.FuelCalculator;
import ncl.ac.uk.newcastle.fuelcalculator.FuelAble;
import ncl.ac.uk.newcastle.rentable.Rentable;
/**
 * Car Factory is a class which produces objects 
 * of cars given a registration number does not already map
 * to an existing registration number.
 * 
 * Implements Car interface which creates a base interface to work from.
 * also implements fuelable as a car is fuelable, this is used to 
 * create a mixin using fuelCalculator. 
 * 
 * The Class has instances stored in a map, all instances recorded are gettable
 * through a getter, this is to check state of all the Cars also to update the cars
 * when they're updated in the Rental Application
 * 
 * 
 * **/

public abstract class CarFactory implements Car, FuelAble{
    private final String registration;
    private final String firstRegistrationPart;
    private final String secondRegistrationPart;
    private boolean Availablity = true;
    private int fuelLoad = 0;
    private final FuelCalculator fuelCalculator = new FuelCalculator();
    private LocalDate contractStartDate;
    private Date contractEndDate;
    private static Map<String, Car> carInstances = new HashMap<String, Car>();
    
	/**
	 * the package Private Constructor is responsible for created a registration number,
	 * it splits up the string registration number into 2 parts, to given options on how 
	 * to get the registration. 
	 * 
	 * also checks the registration number is valid. 
	 * @throws IllegalArgumentException If the registrationNumber is not formatted Correctly
	 * @param Registration number for this new car
	 * **/
    CarFactory(String registrationNumber) {
       if(registrationNumber == null) {
    	   registrationNumber = "";
       }
       
       if(registrationNumber.length() != 0) {
    	   if(format(registrationNumber) != null) {
    	   this.registration = format(registrationNumber);
    	   String finalSplit[]= registration.split(" ");
    	   this.firstRegistrationPart = finalSplit[0];
    	   this.secondRegistrationPart = finalSplit[1];
    	   }else {
    	 	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
    	   }
    }else {
 	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
    }   
   }
    
    
	/**
	 * private method to split the parts of a registrationNumber, returns null
	 * to the caller if the registration is not workable, eg number where a string should be.
	 * @param Registration number for the car
	 * @return formatted registration Number
	 * **/
    private static String format(String registrationNumber) {
     	   String split[] = registrationNumber.toUpperCase().split(" ");
     	   for(int i = 0; i < split.length;i++) {
     		   if(i == 0) {
     			   int j = 0;
     			   while (j < 3) {
     				   if(j < 2) {
     			   String c = split[i].substring(j, j + 1);
     			   char check = c.charAt(0);
     			   j++;
     			   if(check >= 'A' && check <= 'Z') {
     				   continue;
     			   }else {
     		    	  return null;
     			   }
     				   }else {
     					   int check;
     					   if(j == 3) {
     						   String c = split[i].substring(j);
         	    			    check = Integer.parseInt(c);

     					   }else {
     	    			   String c = split[i].substring(j, j + 1);
    	    			    	check = Integer.parseInt(c);
     	    			   j++;
     					   }
     	    			   if(check >= 0 && check <= 9) {
     	    				   continue;
     	    			   }else {
     	    				   return null;
     	    			   }
     				   }
     				  }
     			   
     		   }else if (i == 1) {
     			   int j = 0;
     			   while (j < 3) {
     			   String c = split[i].substring(j, j + 1);

     			   char check = c.charAt(0);
     			   j++;
     			   if(check >= 'A' && check <= 'Z') {
     				   continue;
     			   }else {
	    				   return null;
     			   }   		   
     	   }
     	   
        }else {
			   return null;
        }
      }
     	   return registrationNumber.substring(0, 8).toUpperCase(); 
    }

	/**
	 * Getter method to return all instances of 
	 * the cars, used to check 
	 * car's are available in rental class
	 * @return all of the map's instances
	 * **/
    public final static Collection<Car> getAllCarInstances(){
    	return carInstances.values();
    }

	/**
	 * Return cars Registration
	 * @return registration
	 * **/
    @Override
    public final String getRegistration() {
        return this.registration;
    }

	/**
	 * returns the current fueload of the car
	 * @return fuelLoad 
	 * **/
    @Override
    public final int getCurrentFuelLoad() {
        return this.fuelLoad;
    }

	/**
	 * This method makes use of the fuelCalculator to calculate usage
	 * it checks to see if the current car isAvailable to see if it is indeed
	 * out for rental and if the fueload is not equal to 0
	 * 
	 * The passes off the the fuelCalculator to Calculate how much fuel was used 
	 * 
	 * then the amount it taken away from the current fuel Load then gives back the amount
	 * of fuel that was used in that instance
	 * @param drive tells the method how many Kilometres the car has driven
	 * @return fuelUsed, returns -1 if requirements are not met
	 * **/
    @Override
    public final int drive(final int drive) { 
        if (fuelLoad < 1){
            return  -1;
        }
        
        if(!this.isAvailable()) {
        	return -1;
        }
        
        int fuelUsed = this.fuelCalculator.calculateUsage(this, drive);
        this.fuelLoad = this.fuelLoad - fuelUsed;
        return fuelUsed;
    }
    
    /**
     * adds specified amount of fuel 
	 * @param tells the method how much fuel to add
	 * @return returns the fuelload after its been added, returns -1
	 * if requirements are not met
	 * **/
    @Override
    public final int addFuel(final int fuelAmount) {
        if (this.getCurrentFuelLoad() + fuelAmount > this.getCapacity()){
            return -1;
        }
        
        if(fuelAmount < 1) {
        	return -1;
        }

        return this.fuelLoad += fuelAmount;
    }

    /**
     * adds fuel up to the capacity of the car.
	 * @return returns the fuelload after its been added, returns -1
	 * if requirements are not met
	 * **/
    @Override
    public final int addFuel() {
        int fuelToAdd = this.getCapacity() - this.fuelLoad;  
        if (fuelToAdd <= 0){
            return -1;
        }

        this.fuelLoad += fuelToAdd;
        return fuelToAdd;
    }
    
    /**
     * 
	 * @return returns fuelCalcualtor object
	  **/
    private FuelCalculator getFuelCalculator(){
        return fuelCalculator;
    }

    
    /**
     * Object factory here which is used to produce Car Objects,
     * Used to define uniquness over the Objects made, as two car objects should have 
     * different registration numbers in the car Rental.
     * 
     * @param Car Type, String which gets checked to see if the user wants a largeCar or a small car.
     * @param registration, String which is used to init the field using the constructor the class. 
	 * @return returns CarInstance, returns null if small car or large car is not enetered.
	 **/
    public final static Car getInstance(final String carType, final String registration) {
    	String formattedReg = format(registration);
    	
    	if(carType.equals("LargeCar")) {
    		if(carInstances.containsKey(formattedReg)) {
    			return carInstances.get(formattedReg);
    		}else {
    		 carInstances.put(formattedReg, new LargeCar(formattedReg));
    		 return carInstances.get(formattedReg);
    		}
    	}else if(carType.equals("SmallCar")) {
    		if(carInstances.containsKey(formattedReg)) {
    			return carInstances.get(formattedReg);
    		}else {
    			 carInstances.put(registration, new SmallCar(formattedReg));    
        		 return carInstances.get(formattedReg);

    			}
    	}
      return null;
    }
    
    /**
     * 
	 * @return returns availablity of the car
	 **/
	@Override
	public final boolean isAvailable() {
		// TODO Auto-generated method stub
		return Availablity;
	}
	
	 /**
     *@param avi, used to set availablty of the car
	 **/
	@Override
	public final void setAvailablity(boolean avi) {
		this.Availablity = avi;
	}
	
    /**
     * Used to set the current time, in which the contract will start.
     * @param LocalDate gets the current time which is why it is used here.
	 **/
	@Override
	public final void setStartDate(LocalDate cal) {
		// TODO Auto-generated method stub
		this.contractStartDate = cal;
	}

	 /**
     * Used to set End date of the contract
     * @param Calendar sets up the object to store the end date. Date verifies the date.
	 **/
	@Override
	public final void setEndDate(Calendar cal) {
		// TODO Auto-generated method stub
		this.contractEndDate = new Date();
		contractEndDate.setTime(cal.getTime().getTime());
	}
	
	 /**
     * Makes a copy of the date and returns it
     * @return Returns Contract end date.
	 **/
    @Override
	public final Date contractEndDate() {
		// TODO Auto-generated method stub
    	Date date = new Date();
    	date.setTime(this.contractEndDate.getTime());
		return date;
	}

    /**
     * @return returns start of the contract
	 **/
	@Override
	public final LocalDate contractStartDate() {
		// TODO Auto-generated method stub
		return contractStartDate;	
		}
	

    /**
     * uses \t to separate the variables, 
     * @return returns toString representation of my car Object
	 **/
	public final String toString() {
		return String.format("Registration:%s\tCurrent Fuel Load:%d\tAvailable:%s ", this.getRegistration(), this.getCurrentFuelLoad() , this.isAvailable() ? "Yes" : "No");
	}
}
