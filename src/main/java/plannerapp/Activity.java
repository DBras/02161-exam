package plannerapp;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Activity {
    private String activityTitle;
    private int expected_time, year, start_week, end_week;

    public Activity(String activityTitle, int expected_time, int year, int start_week, int end_week) {
        this.activityTitle = activityTitle;
        this.expected_time = expected_time;
        this.year = year;
        this.start_week = start_week;
        this.end_week = end_week;
    }


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

    public void changeStartDate(int year, int start_week, int end_week) throws OperationNotAllowedException{
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

    public void setName(String name) {
        this.activityTitle = name;
    }
}