package ncl.ac.uk.newcastle.rentable;

import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;
/**
 * Rentable Interface describes can object
 * that can be rented, giving contracts and availability 
 * of the item
 **/
public interface Rentable {
	public Date contractEndDate();
	public LocalDate contractStartDate();
	public void setStartDate(LocalDate cal);
	public void setEndDate(Calendar cal);
	public boolean isAvailable();
	public void setAvailablity(boolean avi);


}
