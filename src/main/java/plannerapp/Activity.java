package plannerapp;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Activity {
    private String activityTitle;
    private int expected_time, year, start_week, end_week;

    /**
     * Class constructor
     * @author Daniel
     * @param activityTitle String of activity title
     * @param expected_time Int of expected hours
     * @param year Int of year
     * @param start_week Int of start week
     * @param end_week Int of end week
     */
    public Activity(String activityTitle, int expected_time, int year, int start_week, int end_week) {
        this.activityTitle = activityTitle;
        this.expected_time = expected_time;
        this.year = year;
        this.start_week = start_week;
        this.end_week = end_week;
    }


    // Getter methods for class
    // @author Daniel
    public String getName() {
        return this.activityTitle;
    }

    public int getExpectedTime() {
        return this.expected_time;
    }
    public int getYear() {
        return this.year;
    }
    public int getStartWeek() {
        return this.start_week;
    }
    public int getEndWeek() {
        return this.end_week;
    }

    /**
     * Change start and end date of activity
     * @author Jonathan
     * @param year Year to change to
     * @param start_week Start week to change to
     * @param end_week End week to change to
     * @throws OperationNotAllowedException
     */
    public void changeActivityDate(int year, int start_week, int end_week) throws OperationNotAllowedException{
        LocalDate time_now = LocalDate.now();
        int current_week_of_year = time_now.get(WeekFields.of(Locale.ENGLISH).weekOfYear());
        if (year < time_now.getYear()
            || year == time_now.getYear() && start_week < current_week_of_year) {
            throw new OperationNotAllowedException("Activity starting date is expired");
        }
        this.year = year;
        this.start_week = start_week;
        this.end_week = end_week;
    }

    /**
     * Setter method for setting name
     * @author Daniel
     * @param name String to change to
     */
    public void setName(String name) {
        this.activityTitle = name;
    }
}