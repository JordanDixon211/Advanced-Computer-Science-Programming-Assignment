import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ncl.ac.uk.newcastle.Person.Person;
import ncl.ac.uk.newcastle.car.Car;
import ncl.ac.uk.newcastle.car.CarFactory;
import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;

public class Main {

    public static void main(String[] args) {
	// write your code here
    	Car car = CarFactory.getInstance("LargeCar", "NG57 HXE");
    	Car car1 = CarFactory.getInstance("LargeCar", "NG58 HXE");
    	Car car2 = CarFactory.getInstance("LargeCar", "NG59 HXE");
    	Car car3 = CarFactory.getInstance("LargeCar", "NG67 HXE");
		Car car5 = CarFactory.getInstance("LargeCar", "N557 E7X");

    	car.addFuel();
    	car3.addFuel();


    	for(Car car6: CarFactory.getAllCarInstances()) {
    		System.out.println(car6.getCurrentFuelLoad());
    		System.out.println(car6.getRegistration());
    	}


    }
}
