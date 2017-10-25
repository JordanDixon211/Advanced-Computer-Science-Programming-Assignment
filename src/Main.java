import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
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

    
    	
		Calendar date2 = new GregorianCalendar(1996, 02, 29);
    	System.out.println(date2.get(5));
    	System.out.println(date2.get(1));

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getMonthValue());
        
        


    }
}
