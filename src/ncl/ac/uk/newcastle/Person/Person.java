package ncl.ac.uk.newcastle.Person;

import ncl.ac.uk.newcastle.drivinglicense.DrivingLicence;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class Person {
    private final String firstName;
    private final String secondName;
    private final Date dateOfBirth;
    private final DrivingLicence drivingLicense;
    
    public Person(String firstName,String secondName ,final Calendar dateOfBirth,final boolean hasDrivingLicence){
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
        
        if(hasDrivingLicence) {
        this.drivingLicense = new DrivingLicence(this.firstName , this.secondName, this.dateOfBirth);
        }else {
        	drivingLicense = null;
        	}
    	}else {
    		throw new IllegalStateException("fields cannot be blank");
    	}
    }

    public  String getFirstName() {
        return firstName;
    }

    public  String getSecondName() {
        return secondName;
    }

    public  Date getAge() {
        Date date = new Date();
        date.setTime(dateOfBirth.getTime());

        return date;
    }

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


    @Override
    public  int hashCode(){
        int hc = 17;
        hc = 37 * hc + (this.firstName == null ? 0 : firstName.hashCode())
                + (this.secondName == null ? 0 : secondName.hashCode())
                + (this.dateOfBirth == null ? 0 : dateOfBirth.hashCode());

        return hc;
    }


    @Override
    public  String toString(){
        return String.format("First Name: %s\tSecond Name: %s\tdate of birth: %s\n",this.firstName, this.secondName, this.dateOfBirth.toString());
    }
}
