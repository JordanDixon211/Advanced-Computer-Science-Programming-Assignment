package ncl.ac.uk.newcastle.car;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ncl.ac.uk.newcastle.fuelcalculator.FuelCalculator;
import ncl.ac.uk.newcastle.fuelcalculator.IFuel;

public abstract class CarFactory implements Car, IFuel {
    private final String registration;
    private final String firstRegistrationPart;
    private final String secondRegistrationPart;

    private int fuelLoad = 0;
    private final FuelCalculator fuelCalculator = new FuelCalculator();
    private static Map<String, Car> carInstances = new HashMap<String, Car>();
    //defensive Programming, ensuring the reg number is correctly fortmatted. if not correctly formatted then throw illegalArg exception.
    CarFactory(String registrationNumber) {
       if(registrationNumber == null) {
    	   registrationNumber = "";
       }
       
       if(registrationNumber.length() != 0) {
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
    		    	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
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
    	    		    	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
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
    		    	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
    			   }   		   
    	   }
    	   
       }else {
    	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
       }
     }
    	   this.registration = registrationNumber.substring(0, 8).toUpperCase();
    	   String finalSplit[]= registrationNumber.split(" ");
    	   this.firstRegistrationPart = finalSplit[0];
    	   this.secondRegistrationPart = finalSplit[1];

    }else {
 	   throw new IllegalArgumentException("Registration format is incorrect, needs to be 2 letters followed by 2 numbers space then 3 more letters");
    }   
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
    public int drive(final int drive) {
        if (fuelLoad < 1){
            return  -1;
        }
        return this.fuelCalculator.calculateUsage(this, drive);
    }

    @Override
    public int addFuel(final int fuelAmount) {
        if (this.getCurrentFuelLoad() + fuelAmount > this.getCapacity()){
            return -1;
        }
        
        if(fuelAmount < 1) {
        	return -1;
        }

        return this.fuelLoad += fuelAmount;
    }

    //add fuel to the capacity.
    @Override
    public int addFuel() {
        int fuelToAdd = this.getCapacity() - this.fuelLoad;  
        if (fuelToAdd <= 0){
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
    
    public static Car getInstance(final String carType, final String registration) {
    	if(carType.equals("LargeCar")) {
    		if(carInstances.containsKey(registration)) {
    			return carInstances.get(registration);
    		}else {
    			carInstances.put(registration, new LargeCar(registration));
    		return new LargeCar(registration);
    		}
    	}else if(carType.equals("SmallCar")) {
    		if(carInstances.containsKey(registration)) {
    			return carInstances.get(registration);
    		}else {
    			carInstances.put(registration, new LargeCar(registration));
    		return new LargeCar(registration);
    		}
    	}
      return null;
    }
}
