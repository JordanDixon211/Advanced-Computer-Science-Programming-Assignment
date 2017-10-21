package ncl.ac.uk.newcastle.drivinglicensetest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import 	java.util.Date;
import ncl.ac.uk.newcastle.Person.Person;
import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;



public class DrivingLicenseUnitTest {
	static DrivingLicence dl;
	static Calendar calender;
	
	//testing the drivers License Class itself.
	@BeforeClass
	public static void setUp() throws Exception {
		calender = new GregorianCalendar();
		calender.set(1996, 1, 28);
		Date date = calender.getTime();
		
		Person p = new Person("Jordan" , "Dixon", calender, false);
		dl = new DrivingLicence(p.getFirstName() ,p.getSecondName(), date);
	}

	@Test 
	public void testgetLicenceNumber() {
		String expected = "JD-1996-1";
		assertEquals(expected , dl.getLicenceNumber().toString());
	}

	@Test 
	public void testgetDateOfIssue() {
		Calendar calender = new GregorianCalendar();
		assertEquals(calender.getTime().getTime() > dl.getDateOfIssue().getTime() , true);

	}
	
	@Test (expected = IllegalStateException.class)
	public void testNullParameterConstructor() {
		DrivingLicence dl2 = new DrivingLicence(null,null, null);
	}
	
	@Test
	public void testValueOf() {
		String expected = "CH-1996-02";
		DrivingLicence dl = DrivingLicence.valueOf("CH-1996-02");
		
		assertEquals(expected, dl.toString());
	}
	
	@Test
	public void testValueOfNull() {
		String expected = "null-null-null";
		DrivingLicence dl = DrivingLicence.valueOf("null-null-null");
		
		assertEquals(expected, dl.toString());
	}
}
