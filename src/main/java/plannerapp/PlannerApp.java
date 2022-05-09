package plannerapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PlannerApp {
    ArrayList<Project> projects;
    List<Developer> developers;
    ArrayList<Activity> activities;


    /**
     * Class constructor with no arguments
     * @author Johannes
     */
    public PlannerApp() {
        this.projects = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.activities = new ArrayList<>();
   
    }

    /**
     * Returns list of projects that match given title and date
     * Design-by-contract by Daniel
     * @author Daniel
     * @param title Title of project
     * @param date Date of project
     * @return Array of matching projects
     */
    public List<Project> searchProjectsByTitleAndDate(String title, LocalDate date) {
        assert title != null && date != null : "Precondition";
        List<Project> projects_matching = new ArrayList<>();
        for (Project project : this.projects) {                                                 // 1
            if (project.getTitle().equals(title) && project.getStartDate().equals(date)) {      // 2
                projects_matching.add(project);                                                 // 3
            }
        }
        assert allMatch(projects_matching, title, date) : "Post-condition";
        return projects_matching;
    }

    /**
     * Method for checking post-condition of above method. Returns true if all projects have matching date and title
     * @author Daniel
     * @param projects List object of projects to test on
     * @param title String of title to check
     * @param date String of date to check
     * @return boolean: true if all match, false if they do not
     */
    public boolean allMatch(List<Project> projects, String title, LocalDate date) {
        return projects.stream()
                .allMatch(p -> p.getTitle().equals(title) && p.getStartDate().equals(date));
    }

    /**
     * Return a single project with matching ID or null
     * @author Daniel
     * @param id int of id for which to search
     * @return Project with matching ID or null if no such project exists
     */
    public Project searchProjectsById(int id) {
        for (Project project : this.projects) {
            if (project.getProjectID() == id) {
                return project;
            }
        }
        return null;
    }

    /**
     * Method for getting developer
     * @author Johannes
     * @param initials String of developer initials
     * @return Developer with matching initials
     */
    public Developer getDeveloper(String initials) {
    	for (Developer dev : this.developers) {
    		if (dev.getInitials().equals(initials))
    		{return dev;}
    	}
    	
    	return null;
    }
   
    /**
     * Method for adding project to  the planner app - design by contract by Samuel.
     * @author Daniel
     * @param project Project object to add
     * @throws OperationNotAllowedException Throws exception if project starting date has expired
     * or the project already exists in the system
     */
    public void addProject(Project project) throws OperationNotAllowedException{
        LocalDate time_now = LocalDate.now();
        assert project.getTitle() != null && project.getStartDate() != null : "Pre-condition";
        if (searchProjectsByTitleAndDate(project.getTitle(), project.getStartDate()).size() >= 1) {
            throw new OperationNotAllowedException("Project already exists in the system");
        } else if (time_now.isAfter(project.getStartDate())) {
            throw new OperationNotAllowedException("Project starting date has expired");
        }
        this.projects.add(project);
        assert this.projects.contains(project) : "Post-condition";
    }

    /**
     * Method for adding developer to the system
     * @author Johannes
     * @param developer Developer object
     * @throws OperationNotAllowedException Throws exception if developer already exists in the system
     */
    public void addDeveloper(Developer developer) throws OperationNotAllowedException{
        assert developer.getInitials() != null : "Pre-condition";
    	for (Developer dev : this.developers) {
    		if (dev.getInitials().equals(developer.getInitials())) {
    			throw new OperationNotAllowedException("Developer is already registered in the system");
    		}
    	}
    	this.developers.add(developer);
        assert this.developers.contains(developer) : "Post-condition";
    }
}
