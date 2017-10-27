package ncl.ac.uk.newcastle.carrentaltest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ncl.ac.uk.newcastle.Person.Person;
import ncl.ac.uk.newcastle.car.Car;
import ncl.ac.uk.newcastle.car.CarFactory;
import ncl.ac.uk.newcastle.carrental.CarRental;

public class CarRentalTest {
  static CarRental carRental;
	 Collection<Car> carSet;
	 Calendar dob;
	 Calendar dateOFIssue;
	 Person p;
	Car RentedCar;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		carRental = new CarRental(20, 10, true);

	}

	@Before
	public void setUp() throws Exception {
		carSet = CarFactory.getAllCarInstances();
		
		dob = new GregorianCalendar();
		dob.set(1980, 1, 28);
		
		dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(1996, 1, 28);
		
		p = new Person("Jordan", "Dixon", dob, dateOFIssue);
	}
	@Test
	public void testAvailableCars() {
		assertEquals(carRental.availableCars("SmallCar") , 9);
		assertEquals(carRental.availableCars("LargeCar") , 20);
		
		RentedCar = carRental.issue(p, p.getDrivingLicense(), "LargeCar");

		assertEquals(carRental.availableCars("SmallCar") , 9);
		assertEquals(carRental.availableCars("LargeCar") , 19);
		
	}

	@Test
	public void testGetRentedCars() {
		CarRental carRental = new CarRental(20, 10, true);

		assertEquals(carRental.getRentedCars().size(), 0);
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(1980, 1, 28);
		
		GregorianCalendar dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(1996, 1, 28);
		
		Person p = new Person("John", "Fred", dob, dateOFIssue);
		
		carRental.issue(p, p.getDrivingLicense(), "SmallCar");
		assertEquals(carRental.getRentedCars().size(), 1);

		
	}

	@Test
	public void testGetCar() {
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(1980, 1, 28);
		
		GregorianCalendar dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(1996, 1, 28);
		
		Person p = new Person("Chelsea", "Eliz", dob, dateOFIssue);
		
		Car checkEquality = carRental.issue(p, p.getDrivingLicense(), "SmallCar");
		assertSame(carRental.getCar(p), checkEquality);
	}

	@Test
	public void testIssue() {
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(1980, 1, 28);
		
		GregorianCalendar dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(1996, 1, 28);
		
		Person p = new Person("John", "Fred", dob, dateOFIssue);
		
		Car checkEquality = carRental.issue(p, p.getDrivingLicense(), "SmallCar");
		assertSame(carRental.getCar(p), checkEquality);
	}

	//drivers licence is not at least a year old. tested for both small
	//and largecar
	@Test
	public void testIssueErrorWrongDrivingLicence() {
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(1996, 1, 28);
		
		GregorianCalendar dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(2017, 1, 28);
		
		Person p = new Person("viv", "Fred", dob, dateOFIssue);
		
		Car checkEquality = carRental.issue(p, p.getDrivingLicense(), "SmallCar");
		assertSame(null, checkEquality);
		
		Person p2 = new Person("viv", "Fred", dob, dateOFIssue);
		
		Car checkEquality2 = carRental.issue(p2, p2.getDrivingLicense(), "LargeCar");
		assertSame(null, checkEquality2);
	}
	
	//driver is too young to drive small and large cars
	@Test
	public void testIssueErrorTooYoung() {
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(2000, 1, 28);
		
		GregorianCalendar dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(1996, 1, 28);
		
		Person p = new Person("viv", "Fred", dob, dateOFIssue);
		
		Car checkEquality = carRental.issue(p, p.getDrivingLicense(), "SmallCar");
		assertSame(carRental.getCar(p), checkEquality);
		
		
		Person p2 = new Person("viv", "Fred", dob, dateOFIssue);
		
		Car checkEquality2 = carRental.issue(p2, p2.getDrivingLicense(), "LargeCar");
		assertSame(null, checkEquality2);
	}

	@Test
	public void testTerminateRental() {
		GregorianCalendar dob = new GregorianCalendar();
		dob.set(2000, 1, 28);
		
		GregorianCalendar dateOFIssue = new GregorianCalendar();
		dateOFIssue.set(1996, 1, 28);
		
		Person p = new Person("viv", "Fred", dob, dateOFIssue);
		
		Car checkEquality = carRental.issue(p, p.getDrivingLicense(), "SmallCar");
		assertSame(carRental.getCar(p), checkEquality);
		
		
		Person p2 = new Person("viv", "Fred", dob, dateOFIssue);
		
		Car checkEquality2 = carRental.issue(p2, p2.getDrivingLicense(), "LargeCar");
		assertSame(null, checkEquality2);
		
		
		assertSame(carRental.getCar(p), checkEquality);
		
		carRental.terminateRental(p);
		//will be null due to be terminated.
		assertSame(carRental.getCar(p), null);
	}

}
