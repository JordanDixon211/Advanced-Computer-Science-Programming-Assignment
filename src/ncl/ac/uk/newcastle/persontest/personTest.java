package ncl.ac.uk.newcastle.persontest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ncl.ac.uk.newcastle.Person.Person;

public class personTest {
	Calendar calender;
	Person p;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
		this.calender = new GregorianCalendar();
		this.calender.set(1996, 1, 28);
		
		this.p = new Person("Jordan", "Dixon", calender, true);
		
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetFirstName() {
		String expected = "Jordan";
		assertEquals(expected, p.getFirstName());
	}

	@Test
	public void testGetSecondName() {
		String expected = "Dixon";
		assertEquals(expected, p.getSecondName());
	}

	@Test
	public void testGetAge() {
		assertEquals(calender.getTime(), p.getAge());
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		String expected = "First Name: " + p.getFirstName() + "\tSecond Name: " + p.getSecondName() + "\tdate of birth: " + p.getAge().toString() + "\n"; 
		assertEquals(expected, p.toString());
	}

}
