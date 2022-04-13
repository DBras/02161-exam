package plannerapp;


import java.time.LocalDate;


public class Project {
    private static int rolling_project_id = 0;
    private final String title;
    private final int project_id;
    private final LocalDate start_date;

    public Project(String title, LocalDate start_date) {
        this.title = title;
        this.start_date = start_date;
        this.project_id = (start_date.getYear() % 1000) * 1000 + (++rolling_project_id);
    }

    public String getTitle() {
        return this.title;
    }
    public int getProject_id() {
        return this.project_id;
    }
    public LocalDate getStart_date() {
        return this.start_date;
    }
}
