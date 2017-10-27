package ncl.ac.uk.newcastle.cartest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ncl.ac.uk.newcastle.car.Car;
import ncl.ac.uk.newcastle.car.CarFactory;
import ncl.ac.uk.newcastle.fuelcalculator.FuelAble;

public class CarTest {
	static Car car;
	static Car car1;
	static Car car2;
	static Car car3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		car = CarFactory.getInstance("SmallCar", "NG57 HXE");
		car1 = CarFactory.getInstance("LargeCar", "NG58 HXE");
		car2 = CarFactory.getInstance("LargeCar", "NG59 HXE");
		car3 = CarFactory.getInstance("LargeCar", "NG60 HXE");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetCurrentFuelLoad() {
		Car car = CarFactory.getInstance("LargeCar", "NG61 HXE");
		assertEquals(car.getCurrentFuelLoad(), 0);
		
		car.addFuel(60);
		assertEquals(car.getCurrentFuelLoad(), 60);
		
		assertEquals(car.getCurrentFuelLoad(), 60);
		
	}
	@Test
	public void testGetRegistration() {
		assertEquals("NG57 HXE", car.getRegistration());
		assertEquals("NG58 HXE", car1.getRegistration());
		
		assertEquals("NG59 HXE", car2.getRegistration());
		assertEquals("NG60 HXE", car3.getRegistration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailInit() {
		Car car1 = CarFactory.getInstance("LargeCar", "NG57 E7X");

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailInit2() {
		Car car1 = CarFactory.getInstance("LargeCar", "N557 E7X");

	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testFailInit3() {
		Car car1 = CarFactory.getInstance("LargeCar", "NE5E E7X");

	}


	@Test
	public void testDrive() {
		car.addFuel();
		car.drive(20);
		
		car2.addFuel();
		car2.drive(20);
		
		assertEquals(48 , car.getCurrentFuelLoad());
		assertEquals(58 , car2.getCurrentFuelLoad());

	}

	@Test
	public void testAddFuelInt() {
		Car car1 = CarFactory.getInstance("LargeCar", "NE55 EXE");

		assertEquals(-1, car1.addFuel(70));
		assertEquals(0, car1.getCurrentFuelLoad());


	}

	@Test
	public void testAddFuel() {
		Car car1 = CarFactory.getInstance("LargeCar", "NE55 EXX");

		assertEquals(car1.addFuel(), 60);
	}

	@Test
	public void testFuelUsage() {
		FuelAble fuel = (FuelAble) CarFactory.getInstance("LargeCar", "NE55 EXX");
		assertEquals(fuel.getConsumption(20), 10);
		
		FuelAble fuel2 = (FuelAble) CarFactory.getInstance("SmallCar", "NE55 EXX");
		assertEquals(fuel2.getConsumption(20), 10);
	}


	
}
