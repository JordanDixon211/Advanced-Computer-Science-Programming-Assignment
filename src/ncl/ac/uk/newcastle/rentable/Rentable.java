package ncl.ac.uk.newcastle.rentable;

import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;

public interface Rentable {
	public Date contractEndDate();
	public LocalDate contractStartDate();
	public void setStartDate(LocalDate cal);
	public void setEndDate(Calendar cal);
	public boolean isAvailable();
	public boolean returned();
	public void setAvailablity(boolean avi);


}
