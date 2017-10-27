package ncl.ac.uk.newcastle.Person;

import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Person class creates a Person, with the option of having a drivers licence.
 * Equality is based on if the person has firstName and second name and same date of 
 * birth 
 * 
 * Hashcode used are based on the same variables.
 * since a person can have a drivers licence a drivers licence object 
 * is used in this class instead of inheritance.
 **/
public final class Person {
    private final String firstName;
    private final String secondName;
    private final Date dateOfBirth;
    private final DrivingLicence drivingLicense;
    private final boolean hasDrivingLicence;

	/**
	 * Constructor, uses defensive programming to check first and second names are equal to something,
	 * if dateOfIssue is not equal to null it means that the person must have a drivers licence.
	 * @param Firstname of person
	 * @param Secondname of person
	 * @param dateofbirth of person
	 * @param dateofissue of their drivers licence
	 * @throws IllegalStateException
	 **/
    public Person(String firstName,String secondName ,final Calendar dateOfBirth, final Calendar dateOfIssue){
    	if(firstName == null) {
    		firstName = "";
    	}
    	
    	if(secondName == null) {
    		secondName = "";
    	}
    	
    	if(dateOfBirth != null) {
        this.firstName = firstName.trim();
        this.secondName = secondName.trim();
        this.dateOfBirth = dateOfBirth.getTime();
        
        if(this.firstName.length() == 0 || this.secondName.length() == 0 || this.dateOfBirth.getTime() == 0) {
    		throw new IllegalStateException("fields are blank.");
        }
        
        if(dateOfIssue != null) {
        this.drivingLicense = new DrivingLicence(this.firstName , this.secondName, this.dateOfBirth,dateOfIssue);
        hasDrivingLicence =true;
        }else {
        	drivingLicense = null;
            hasDrivingLicence =false;
        	}
    	}else {
    		throw new IllegalStateException("fields cannot be blank");
    	}
    }

	/**
	 * @return person firstName
	 * **/
    public  String getFirstName() {
        return firstName;
    }

	/**
	 * @return person secondname
	 * **/
    public  String getSecondName() {
        return secondName;
    }

    /** makes a copy of person's date of birth and returns 
     * it to the caller
	 * @return person age
	 * **/
    public  Date getAge() {
        Date date = new Date();
        date.setTime(dateOfBirth.getTime());

        return date;
    }

    /** 
     * Overridden the equality operator in Object base class 
     * checks if two people are equal based on 
     * their first name, second name and date of birth.
     * if these are the same they are then equal to each other.
     * @param obj object to be compared
	 * @return boolean true if they're equal false if not
	 * **/
    public boolean equals(final Object obj){
        //reflexivity
        if (this == obj){
            return true;
        }
        //null
        if (! (obj instanceof Person)){
            return false;
        }

        Person p = (Person) obj;
        
        return (this.dateOfBirth == null ?
                p.getAge() == null : this.dateOfBirth.equals(p.getAge()))
                && (this.firstName == null ?
                p.getFirstName() == null : firstName.equals(p.getFirstName()))
                && (this.secondName == null ?
                p.getSecondName() == null : this.secondName.equals(p.getSecondName()));
    }

    /** returns a copy of the driving licence for a person.
	 * @return persons driving licence
	 * **/
	public DrivingLicence getDrivingLicense() {
		return drivingLicense;
	}

	/**
	 * returns boolean true if person does have a driving licence
	 * @return hasDrivingLicence
	 * **/
	public boolean HasDrivingLicence() {
		return hasDrivingLicence;
	}

	/**
	 * adds hashcode's of the firstname, secondname and dateOfBirth together
	 * to get a unique hashcode of person Object
	 * @return hc hashcode for the 3 values
	 * **/
	@Override
    public  int hashCode(){
        int hc = 17;
        hc = 37 * hc + (this.firstName == null ? 0 : firstName.hashCode())
                + (this.secondName == null ? 0 : secondName.hashCode())
                + (this.dateOfBirth == null ? 0 : dateOfBirth.hashCode());

        return hc;
    }


	/**
	 * Returns string representation of a person 
	 * @return String toString
	 * **/
    @Override
    public  String toString(){
        return String.format("First Name: %s\tSecond Name: %s\tdate of birth: %s\n",this.firstName, this.secondName, this.dateOfBirth.toString());
    }
}
