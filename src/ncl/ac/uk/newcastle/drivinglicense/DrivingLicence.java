package ncl.ac.uk.newcastle.drivinglicense;

import ncl.ac.uk.newcastle.Person.Person;

import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public final class DrivingLicence {
    private final Date dateOfIssue;
    private final String initals;
    private final String yearOfBirth;
    private final String uniqueNumber;
    private final int yearOfBirthPlacement = 5;
    private final String licenceNumber;
    private final String formatter = "-";
    private static int count = 1;

    public DrivingLicence(String personFirstName,String personSecondName,final Date dateOfBirth) {
    	if(personFirstName == null) {
    		personFirstName = "";
    	}
    	
    	if(personSecondName == null) {
    		personSecondName = "";
    	}
    	
    	
        if (personFirstName.trim().length() > 0 && personSecondName.trim().length() > 0 && dateOfBirth != null) {
        	initals = personFirstName.trim().substring(0, 1) + personSecondName.trim().substring(0, 1);
        	
        	String [] splitDateOfBirth = dateOfBirth.toString().split(" ");
            yearOfBirth = splitDateOfBirth[5];
            
        	dateOfIssue = Calendar.getInstance().getTime();    	          
            this.uniqueNumber = Integer.toString(count); 	
            this.licenceNumber = this.initals + this.formatter + this.yearOfBirth + this.formatter + this.uniqueNumber;            
            count++;
        }else {
            throw new IllegalStateException("fields cannot be blank");        	
        }
    }

    private DrivingLicence(final String initals,final String year,final String last) {
        this.initals = initals;
        this.yearOfBirth = year;
        this.uniqueNumber = last;

        this.licenceNumber = this.initals + this.formatter + this.yearOfBirth + this.formatter + this.uniqueNumber;
        dateOfIssue = Calendar.getInstance().getTime();

    }

    public Date getDateOfIssue(){
        Date date = new Date();
        date.setTime(dateOfIssue.getTime());

        return date;
    }

    public String getLicenceNumber(){
        return licenceNumber;
    }
    //- formatter
    @Override
    public String toString() {
        return String.format("%s-%s-%s" , this.initals, this.yearOfBirth, this.uniqueNumber);
    }

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





}
