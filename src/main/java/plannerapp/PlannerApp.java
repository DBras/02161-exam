package plannerapp;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlannerApp {
    ArrayList<Project> projects;

    public PlannerApp() {
        this.projects = new ArrayList<>();
    }

    public Object[] searchProjects(String title, LocalDate date) {
        ArrayList<Project> projects_matching = new ArrayList<>();
        System.out.println(this.projects.size());
        for (Project project : this.projects) {
            if (project.getTitle().equals(title) && project.getStart_date().equals(date)) {
                projects_matching.add(project);
            }
        }
        return projects_matching.toArray();
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
}
