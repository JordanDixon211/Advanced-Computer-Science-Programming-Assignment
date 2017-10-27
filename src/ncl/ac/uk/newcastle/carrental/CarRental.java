package ncl.ac.uk.newcastle.carrental;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ncl.ac.uk.newcastle.Person.Person;
import ncl.ac.uk.newcastle.car.*;
import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;

/**
 * Class witch creates a car Rental Application, 
 * Provides services to add cars to the rental, along 
 * with managing the amount of cars Out for rental.
 * **/
public final class CarRental {
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
		Random  rand = new Random ();
		
		   final String alphabet = "ABCDEFGHIKLMNOPQRSTVXYZ";
		   final int N = alphabet.length();

		    Random r = new Random();

		if(autoGiveRegistration) {
			for(int i = 0; i < amountOfSmallCars;i++) {
				char firstChar = alphabet.charAt(r.nextInt(N));
				char secondChar = alphabet.charAt(r.nextInt(N));

				int thirdNum = rand.nextInt(9) + 0;
				int secondNum = rand.nextInt(9) + 0;
				
				String FirstPart = Character.toString(firstChar) + Character.toString(secondChar);
				char forthChar = alphabet.charAt(r.nextInt(N));
				char fithChar = alphabet.charAt(r.nextInt(N));
				char sixthChar = alphabet.charAt(r.nextInt(N));

				String last = Character.toString(forthChar) + Character.toString(fithChar) + Character.toString(sixthChar);
				
				String registration = FirstPart + Integer.toString(thirdNum) + Integer.toString(secondNum) + " " + last;
				Car car = CarFactory.getInstance("SmallCar", registration);
				SmallCarsInStock++;
			}
			
			for(int i = 0; i < amountOfLargeCars;i++) {
				char firstChar = alphabet.charAt(r.nextInt(N));
				char secondChar = alphabet.charAt(r.nextInt(N));

				int thirdNum = rand.nextInt(9) + 0;
				int secondNum = rand.nextInt(9) + 0;
				
				String FirstPart = Character.toString(firstChar) + Character.toString(secondChar);
				char forthChar = alphabet.charAt(r.nextInt(N));
				char fithChar = alphabet.charAt(r.nextInt(N));
				char sixthChar = alphabet.charAt(r.nextInt(N));

				String last = Character.toString(forthChar) + Character.toString(fithChar) + Character.toString(sixthChar);
				
				String registration = FirstPart + Integer.toString(thirdNum) + Integer.toString(secondNum) + " " + last;
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
	
	/**
	 * calculates amount of cars that are not currently rented out. 
	 * looping through the instances set in the CarFactory.
	 * @param typeOfCar is the type of car user wishes to have
	 * @return return the amount of cars available. 
	 * **/
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
	
	/**
	 * gets all the map's values, as these are the cars rented
	 * @return returns the maps values as a new set so user cannot change the object.
	 * **/
	public Set<Car> getRentedCars(){
		return new HashSet<Car>(carRentals.values());
	}
	
	/**
	 * gets a car that is currently rented by a person
	 * uses maps get to get a car out of the map.
	 * if none sound getOrDefault will be used as it will return a car Object
	 * which is = to null;
	 * @param Person who is currently stored in the rentals map
	 * @return return the car associated with that person
	 * **/
	public Car getCar(final Person p) {
		Car rentedCar = carRentals.getOrDefault(p, null);
		if(rentedCar != null) {
			return rentedCar;
		}
		return null;
	}
	
	/**
	 * This Method will issue a car to a given person, giving that a perosn
	 * passes the given requirements such as a person who wants a small
	 * car has to have a drivingLicence for at least a year and must be at least 20
	 * For A large car age must be 25 and have a driving Licence for at least 5 years. 
	 * 
	 * once passing these requirements the person will be assoicated with a car given that 
	 * there are cars available for this person. a contract which will start now 
	 * will be given for the car, and a default time of 10 days will be given before
	 * car has to be returned.
	 *
	 * returns null if something goes wrong, or if a car cannot be issued
	 * 
	 * @param Person Object who will be added to associate the car to a person
	 * @param DrivingLicence associated with that person is passed using person.getDrivingLicence Method.
	 * @param TypeOfCar caller passes in a string representation of the car they would like.
	 * @return return the car associated with that person
	 * **/
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
							 Calendar endContract = new GregorianCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth() + standardContract);
							  car.setEndDate(endContract);
							  
							  if(!car.isFull()) {
								  car.addFuel();
							  }							  
							  carRentals.put(p, car);
							  return carRentals.get(p);
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
						 Calendar endContract = new GregorianCalendar(date.getYear(), date.getMonthValue(), date.getDayOfMonth() + standardContract);

						  car.setEndDate(endContract);
						  
						  if(!car.isFull()) {
							  car.addFuel();
						  }
						  carRentals.put(p, car);
						  return carRentals.get(p);

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
	
	/**
	 * 
	 * @param Person Object who will be added to associate the car to a person
	 * @return return the fuel left to refuel the car
	 * **/
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
