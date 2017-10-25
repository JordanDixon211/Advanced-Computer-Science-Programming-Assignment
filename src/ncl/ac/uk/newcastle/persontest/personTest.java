package ncl.ac.uk.newcastle.persontest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ncl.ac.uk.newcastle.Person.Person;

public class personTest {
	Calendar dob;
	Calendar dateOFIssue;
	Person p;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
		this.dob = new GregorianCalendar();
		this.dob.set(1980, 1, 28);
		
		this.dateOFIssue = new GregorianCalendar();
		this.dateOFIssue.set(1996, 1, 28);
		
		this.p = new Person("Jordan", "Dixon", dob, dateOFIssue);
		
	}

	@Test
	public void testHashCode() {
		Person p2 = new Person("Jordan", "Dixon", dob, dateOFIssue);	
		assertEquals(p.equals(p2), p2.hashCode() == p.hashCode());
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
		assertEquals(dob.getTime(), p.getAge());
	}


	@Test
	public void testEqualsReflexive() {
		GregorianCalendar calender = new GregorianCalendar();
		calender.set(1996, 1, 28);
		
		Person produceFalse = new Person("k", "Dixon", calender, calender);
		
		assertTrue(p.equals(p));
		assertFalse(p.equals(produceFalse));


	}


	@Test
	public void testEqualsSymmetric() {
		GregorianCalendar calender = new GregorianCalendar();
		calender.set(1980, 1, 28);
		
		Person x = new Person("Jordan", "Dixon", calender, calender);
		
		GregorianCalendar calenderFalse = new GregorianCalendar();
		calender.set(1980, 1, 28);
		
		Person produceFalse = new Person("k", "Dixon", calender, calender);
		
		assertTrue(p.equals(x));
		assertTrue(x.equals(p));

		
		assertFalse(produceFalse.equals(x));
		assertFalse(x.equals(produceFalse));
	}


	@Test
	public void testEqualsTransitive() {
		GregorianCalendar calender = new GregorianCalendar();
		calender.set(1980, 1, 28);
		
		Person x = new Person("Jordan", "Dixon", calender, calender);
		
		GregorianCalendar calender2 = new GregorianCalendar();
		calender.set(1980, 1, 28);
		
		Person y = new Person("Jordan", "Dixon", calender, calender);
		
		GregorianCalendar calenderFalse = new GregorianCalendar();
		calender.set(1980, 1, 28);
		
		Person produceFalse = new Person("k", "Dixon", calender, calender);
		
		assertTrue(x.equals(y));
		assertTrue(y.equals(this.p));
		assertTrue(x.equals(this.p));
		
		assertFalse(produceFalse.equals(y));
		assertTrue(y.equals(this.p));
		assertFalse(produceFalse.equals(this.p));
	}


	@Test
	public void testEqualsConsistant() {
		GregorianCalendar calender = new GregorianCalendar();
		calender.set(1980, 1, 28);
		
		Person x = new Person("Jordan", "Dixon", calender, null);
		assertTrue(p.equals(x));
		assertTrue(p.equals(x));
		assertTrue(p.equals(x));
		

	}
	
	@Test
	public void testNonNullReferance() {
		assertFalse(this.p.equals(null));
	}

	@Test
	public void testToString() {
		String expected = "First Name: " + p.getFirstName() + "\tSecond Name: " + p.getSecondName() + "\tdate of birth: " + p.getAge().toString() + "\n"; 
		assertEquals(expected, p.toString());
	}
	
	
	

	@Test
	public void testHasDrivingLicence() {
		assertEquals(true, p.HasDrivingLicence());
	}
}
