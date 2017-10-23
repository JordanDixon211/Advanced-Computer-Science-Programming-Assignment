import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ncl.ac.uk.newcastle.Person.Person;
import ncl.ac.uk.newcastle.car.Car;
import ncl.ac.uk.newcastle.car.CarFactory;
import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;

public class Main {

    public static void main(String[] args) {
	// write your code here

    	Car car = CarFactory.getInstance("LargeCar", "NG57 HXE");
    	System.out.println(car.getRegistration());
		
    }
}
