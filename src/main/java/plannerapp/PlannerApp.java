package plannerapp;

import java.util.ArrayList;

public class PlannerApp {
    ArrayList<Project> projects;

    public PlannerApp() {
        this.projects = new ArrayList<>();
    }

    public Object[] searchProjects(String title) {
        ArrayList<Project> projects_matching = new ArrayList<>();
        for (Project project : this.projects) {
            if (project.getTitle().equals(title)) {projects_matching.add(project);}
        }
        return projects_matching.toArray();
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
}
