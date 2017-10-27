package ncl.ac.uk.newcastle.drivinglicense;

import ncl.ac.uk.newcastle.Person.Person;

import java.util.Calendar;
import java.util.Date;

/**
 * DrivingLicence class provides specific methods to get information about 
 * Someone's driving Licence.
 * 
 *A value of method is provided if someone would like to produce an object for a specific person.
 *Static count is used to provide some uniqueness to each driving licence, they will be unique, 
 *but if someone used ValueOfMethod then you may violate this.
 *
 *Formatter separates out the dl for ValueOf method and toString
 * **/
public final class DrivingLicence {
    private final Date dateOfIssue;
    private final String initals;
    private final String yearOfBirth;
    private final String uniqueNumber;
    private final int yearOfBirthPlacement = 5;
    private final String licenceNumber;
    private final String formatter = "-";
    private static int count = 1;
    
	/**
	 * Uses First name and second name to split strings 
	 * to add them as a licenceNumber.
	 * @param Person First Name for the initals and licenceNumber
	 * @param Person Second Name initals and licenceNumber
	 * @param date Of Birth for the licenceNumber and the check restrictions 
	 * @param date of Issue, is the date the driving licence has been created.
	 * **/
    public DrivingLicence(String personFirstName,String personSecondName,final Date dateOfBirth, Calendar dateOfIssue) {
    	if(personFirstName == null) {
    		personFirstName = "";
    	}
    	
    	if(personSecondName == null) {
    		personSecondName = "";
    	}
    	
    	
        if (personFirstName.trim().length() > 0 && personSecondName.trim().length() > 0 && dateOfBirth != null && dateOfIssue != null) {
        	initals = personFirstName.trim().substring(0, 1) + personSecondName.trim().substring(0, 1);
        	
        	String [] splitDateOfBirth = dateOfBirth.toString().split(" ");
            yearOfBirth = splitDateOfBirth[5];
            
            this.dateOfIssue = dateOfIssue.getTime();
            this.uniqueNumber = Integer.toString(count); 	
            this.licenceNumber = this.initals + this.formatter + this.yearOfBirth + this.formatter + this.uniqueNumber;            
            count++;
        }else {
            throw new IllegalStateException("fields cannot be blank");        	
        }
    }

	/**
	 * private constructor to be used for the ValueOf Creation
	 * @param initals number for the car
	 * @param year date of birth for this person
	 * @param unique number for last.
	 * **/
    private DrivingLicence(final String initals,final String year,final String last) {
        this.initals = initals;
        this.yearOfBirth = year;
        this.uniqueNumber = last;

        this.licenceNumber = this.initals + this.formatter + this.yearOfBirth + this.formatter + this.uniqueNumber;
        dateOfIssue = Calendar.getInstance().getTime();

    }

	/**
	 * Get the date the driving licence has been issued
	 * @return dateOfIssue
	 * **/
    public Date getDateOfIssue(){
        Date date = new Date();
        date.setTime(dateOfIssue.getTime());

        return date;
    }

	/**
	 * gives back the caller a formatted driving licence
	 * @return licenceNumber formatted
	 * **/
    public String getLicenceNumber(){
        return licenceNumber;
    }

	/**
	 * @return formatted registration Number using the 3 fields
	 * **/
    @Override
    public String toString() {
        return String.format("%s-%s-%s" , this.initals, this.yearOfBirth, this.uniqueNumber);
    }

	/**
	 * Value Of method to create a driving licence
	 * with a string.
	 * @param String representation of a Driving Licence
	 * @return formatted Driving Licence
	 * **/
    public static DrivingLicence valueOf(final String value){
        final String parts[] = value.split("-");
         String first;
         String middle;
         String last;

        first = parts[0].equals("null") ? null : parts[0];
        middle = parts[1].equals("null") ? null : parts[1];
        last =  parts[2].equals("null") ? null : parts[2];

        return new DrivingLicence(first, middle, last);
    }

	/**
	 * gets year of birth of the person
	 * Associated with this driving licence
	 * @return formatted yearOfBirth
	 * **/
	public String getYearOfBirth() {
		return yearOfBirth;
	}
	
	/**
	 * gets unique number associated with static integer.
	 * Associated with this driving licence
	 * @return formatted yearOfBirth
	 * **/
	public String getUniqueNumber() {
		return uniqueNumber;
	}

	/**
	 * gets the placement of the year of birth with the calendar class
	 * @return formatted yearOfBirth
	 * **/
	private int getYearOfBirthPlacement() {
		return yearOfBirthPlacement;
	}





}
