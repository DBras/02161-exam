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
}