package plannerapp;


import java.time.LocalDate;


public class Project {
    // A static integer is kept which increases by 1 every time a new project is created.
    private static int rolling_project_id = 0;
    private final String title;
    private final int project_id;
    private final LocalDate start_date;
    private Developer project_manager;

    public Project(String title, LocalDate start_date) {
        this.title = title;
        this.start_date = start_date;
        this.project_id = nextProjectID(start_date);
    }
    public Project(LocalDate start_date) {
        this.title = "";
        this.start_date = start_date;
        this.project_id = nextProjectID(start_date);
    }

    public int nextProjectID(LocalDate start_date) {
        return (start_date.getYear() % 1000) * 1000 + (++rolling_project_id);
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
    public Developer getProjectManager() {return this.project_manager;}

    public void setProjectManager(Developer project_manager) {
        this.project_manager = project_manager;
    }
}
