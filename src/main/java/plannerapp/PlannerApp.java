package plannerapp;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlannerApp {
    ArrayList<Project> projects;

    public PlannerApp() {
        this.projects = new ArrayList<>();
    }

    /**
     * Returns list of projects that match given title and date
     * @author Daniel
     * @param title Title of project
     * @param date Date of project
     * @return Array of matching projects
     */
    public Object[] searchProjectsByTitleAndDate(String title, LocalDate date) {
        ArrayList<Project> projects_matching = new ArrayList<>();
        for (Project project : this.projects) {
            if (project.getTitle().equals(title) && project.getStart_date().equals(date)) {
                projects_matching.add(project);
            }
        }
        return projects_matching.toArray();
    }

    public void addProject(Project project) throws OperationNotAllowedException{
        LocalDate time_now = LocalDate.now();
        if (searchProjectsByTitleAndDate(project.getTitle(), project.getStart_date()).length >= 1) {
            throw new OperationNotAllowedException("Project already exists in the system");
        } else if (time_now.isAfter(project.getStart_date())) {
            throw new OperationNotAllowedException("Project starting date has expired");
        }
        this.projects.add(project);
    }
}
