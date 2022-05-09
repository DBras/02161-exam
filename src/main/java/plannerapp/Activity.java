package plannerapp;

import java.time.LocalDate;

public class Activity {
    private String activityTitle;
    private  LocalDate activitydate;

    public Activity(String activityTitle, LocalDate activitydate) {
        this.activityTitle = activityTitle;
        this.activitydate =  activitydate;
    }


    public String getName() {
        return this.activityTitle;
    }
    
    public LocalDate getStartDate() {
    	return this.activitydate;
    }

    public void changeStartDate(LocalDate start_date) throws OperationNotAllowedException{
        LocalDate time_now = LocalDate.now();
        if (time_now.isAfter(start_date)) {
            throw new OperationNotAllowedException("Activity starting date is expired");
        }
        this.activitydate = start_date;
    }

    public void setName(String name) {
        this.activityTitle = name;
    }
}