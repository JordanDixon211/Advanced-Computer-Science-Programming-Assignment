package ncl.ac.uk.newcastle.carrental;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ncl.ac.uk.newcastle.Person.Person;
import ncl.ac.uk.newcastle.car.*;
import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;

public final class CarRental {
	//availableCars
	//getRentedCars
	//getCar
	//issueCar
	//terminateRental
	private final Map<Person, Car> carRentals = new HashMap<>();
	private final Map<Person, Integer> fuelPay = new HashMap<>();

	private final int amountOfLargeCars;
	private final int amountOfSmallCars;
	private int LargeCarsInStock;
	private int SmallCarsInStock;
	private final int standardContract = 10;
	/**
	 * Constructor, which init's the following rental class with the amount of 
	 * cars wanting to be allocated 
	 * @param amountOfLargeCars this defines the amountOfLargeCars needed
	 * @param amountOfSmallCars this defines the amountOfSmallCars needed
	 * @param autoGiveRegistration if the user would like cars to be given registrations automatically default ones will be given
	 * **/
	public CarRental(final int amountOfLargeCars,final int amountOfSmallCars, boolean autoGiveRegistration) {
		this.amountOfLargeCars = amountOfLargeCars;
		this.amountOfSmallCars = amountOfSmallCars;
		
		if(autoGiveRegistration) {
			for(int i = 0; i <= amountOfSmallCars;i++) {
				String firstPart = "AA"; 
				int thirdNum = i;
				int secondNum = i;
				
				String last = "HXE";
				
				String registration = firstPart + Integer.toString(thirdNum) + Integer.toString(secondNum) + " " + last;
				Car car = CarFactory.getInstance("SmallCar", registration);
				SmallCarsInStock++;
			}
			
			for(int i = 0; i <= amountOfLargeCars;i++) {
				String firstPart = "BB"; 
				int thirdNum = i;
				int secondNum = i;
				
				String last = "HXE";
				
				String registration = firstPart + Integer.toString(thirdNum) + Integer.toString(secondNum) + " " + last;
				Car car = CarFactory.getInstance("LargeCar", registration);
				LargeCarsInStock++;
			}
		}	
	}
	
	
	/**
	 * add a large car to the set in the carFactory Set	
	 * only add if there not already max allowed cars in the rental
	 * @param Registration number for this new car
	 * @return return the car object back to the caller, or null if max allowed cars have already been returned.
	 * **/
	public Car addLargeCar(final String Registration) {
		if(this.LargeCarsInStock < this.amountOfLargeCars) {
			Car car = CarFactory.getInstance("LargeCar", Registration);
			return car;
		}else {
			return null;
		}
	}
	
	/**
	 * add a Small car to the set in the carFactory Set	
	 * only add if there not already max allowed cars in the rental
	 * @param Registration number for this new car
	 * @return return the car object back to the caller, or null if max allowed cars have already been returned.
	 * **/
	public Car addSmallCar(final String Registration) {
		if(this.SmallCarsInStock < this.SmallCarsInStock) {
			Car car = CarFactory.getInstance("LargeCar", Registration);
			return car;
		}else {
			return null;
		}
	}
	
	public int availableCars(final String typeOfCar) {
		int count = 0;
		if(typeOfCar.equals("LargeCar")) {
			for(Car car: CarFactory.getAllCarInstances()) {
				if(car.isAvailable() && car instanceof LargeCar) {
					count++;
				}
			}
			
			return count;
		}else if(typeOfCar.equals("SmallCar")) {
			for(Car car: CarFactory.getAllCarInstances()) {
				if(car.isAvailable() && car instanceof SmallCar) {
					count++;
				}
			}
			
			return count;
		}
		
		return -1;
	}
	
	public Set<Car> getRentedCars(){
		return new HashSet<Car>(carRentals.values());
	}
	
	public Car getCar(final Person p) {
		Car rentedCar = carRentals.getOrDefault(p, null);
		if(rentedCar != null) {
			return rentedCar;
		}
		return null;
	}
	
	public Car issue(final Person p,final DrivingLicence dl,final String typeOfCar) {
		LocalDate date = LocalDate.now();
		if(carRentals.containsKey(p) || !(p.HasDrivingLicence())) {
			return null;
		}
		
		if(typeOfCar.equals("SmallCar")) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(p.getAge());
			
			int yearDifference = date.getYear() - cal.get(1);
			int monthDifference = date.getMonthValue() -cal.get(2);
			int dayDifference = date.getDayOfMonth() - cal.get(5);

			if(yearDifference > 20 || (yearDifference == 20 && monthDifference < 0 )|| (yearDifference == 20 && monthDifference == 0 && dayDifference <= 0) ) {
				Calendar dateOfIssue = new GregorianCalendar();
				dateOfIssue.setTime(dl.getDateOfIssue());
				
				int yearDifferenceDL = date.getYear() - dateOfIssue.get(1);
				int monthDifferenceDL = date.getMonthValue() -dateOfIssue.get(2);
				int dayDifferenceDL = date.getDayOfMonth() - dateOfIssue.get(5);
				
				if(yearDifferenceDL > 1 || (yearDifferenceDL == 1 && monthDifferenceDL < 0 )
						|| (yearDifferenceDL == 1 && monthDifferenceDL == 0 && dayDifferenceDL <= 0) ) {
					for(Car car: CarFactory.getAllCarInstances()) {
						if(car instanceof SmallCar && car.isAvailable()) {
							 car.setAvailablity(false);
							 car.setStartDate(date);
							 Calendar endContract = new GregorianCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth() + 10);

							  car.setEndDate(endContract);
							  
							  if(!car.isFull()) {
								  car.addFuel();
							  }							  
						
							  return carRentals.put(p, car);
						}
					}
				}else {
					return null;
				}
			}else {
				return null;
			}
		}else if(typeOfCar.equals("LargeCar")) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(p.getAge());
			
			int yearDifference = date.getYear() - cal.get(1);
			int monthDifference = date.getMonthValue() -cal.get(2);
			int dayDifference = date.getDayOfMonth() - cal.get(5);

			if(yearDifference > 25 || (yearDifference == 25 && monthDifference < 0 )|| (yearDifference == 25 && monthDifference == 0 && dayDifference <= 0) ) {
				Calendar dateOfIssue = new GregorianCalendar();
				dateOfIssue.setTime(dl.getDateOfIssue());
				
				int yearDifferenceDL = date.getYear() - dateOfIssue.get(1);
				int monthDifferenceDL = date.getMonthValue() -dateOfIssue.get(2);
				int dayDifferenceDL = date.getDayOfMonth() - dateOfIssue.get(5);
				
				if(yearDifferenceDL > 5 || (yearDifferenceDL == 5 && monthDifferenceDL < 0 )
						|| (yearDifferenceDL == 5 && monthDifferenceDL == 0 && dayDifferenceDL <= 0) ) {
					for(Car car: CarFactory.getAllCarInstances()) {
						if(car instanceof LargeCar && car.isAvailable()) {
						  car.setAvailablity(false);
						  car.setStartDate(date);
						 Calendar endContract = new GregorianCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth() + 10);

						  car.setEndDate(endContract);
						  
						  if(!car.isFull()) {
							  car.addFuel();
						  }
						  return  carRentals.put(p, car);

						}
					}
				}else {
					return null;
				}
			}else {
				return null;
			}
		}
	
	return null;
	}
	
	
	public int terminateRental(Person p) {
		if((carRentals.containsKey(p))) {
			Car car = carRentals.get(p);
			car.setAvailablity(true);
			car.setStartDate(null);
			car.setEndDate(null);
			carRentals.remove(p);
			int fuelToAdd = car.getCapacity() - car.getCurrentFuelLoad();
			if(fuelToAdd > 0) {
			this.fuelPay.put(p, fuelToAdd);
			return car.getCapacity() - car.getCurrentFuelLoad();
			}
			return 0;
			
		}else {
			return -1;
		}
	}
	
}
