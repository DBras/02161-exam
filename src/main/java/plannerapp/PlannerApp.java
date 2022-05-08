package plannerapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlannerApp {
    ArrayList<Project> projects;
    List<Developer> developers;
    ArrayList<Activity> activities;

    
    public PlannerApp() {
        this.projects = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.activities = new ArrayList<>();
   
    }

   
    
    /**
     * Returns list of projects that match given title and date
     * @author Daniel
     * @param title Title of project
     * @param date Date of project
     * @return Array of matching projects
     */
    public Object[] searchProjectsByTitleAndDate(String title, LocalDate date) {
        List<Project> projects_matching = new ArrayList<>();
        for (Project project : this.projects) {
            if (project.getTitle().equals(title) && project.getStart_date().equals(date)) {
                projects_matching.add(project);
            }
        }
        return projects_matching.toArray();
    }

    /**
     * Return a single project with matching ID or null
     * @author Daniel
     * @param id int of id for which to search
     * @return Project with matching ID or null if no such project exists
     */
    public Project searchProjectsById(int id) {
        for (Project project : this.projects) {
            if (project.getProject_id() == id) {
                return project;
            }
        }
        return null;
    }
    
    
    
    
    
    public Developer getDeveloper(String initials) {
    	for (Developer dev : this.developers) {
    		if (dev.getInitials().equals(initials))
    		{return dev;}
    	}
    	
    	return null;
    }
   
    /**
     * Method for adding project to  the planner app
     * @author Daniel
     * @param project Project object to add
     * @throws OperationNotAllowedException Throws exception if project starting date has expired
     * or the project already exists in the system
     */
    public void addProject(Project project) throws OperationNotAllowedException{
        LocalDate time_now = LocalDate.now();
        if (searchProjectsByTitleAndDate(project.getTitle(), project.getStart_date()).length >= 1) {
            throw new OperationNotAllowedException("Project already exists in the system");
        } else if (time_now.isAfter(project.getStart_date())) {
            throw new OperationNotAllowedException("Project starting date has expired");
        }
        this.projects.add(project);
    }
    
    public void addDeveloper(Developer developer) throws OperationNotAllowedException{
    	for (Developer dev : this.developers) {
    		if (dev.getInitials().equals(developer.getInitials())) {
    			throw new OperationNotAllowedException("Developer is already registered in the system");
    		}
    	}
    	this.developers.add(developer);
    }
    
    public void addActivity(Activity activity) throws OperationNotAllowedException{

        if  (this.activities.contains(activity)) {
            throw new OperationNotAllowedException("Activity is already registered in the project");
        }
         this.activities.add(activity);
    }

    public Activity getActivity(String activityTitle) {
        for (Activity act : this.activities) {
            if (act.getName().equals(activityTitle)) {return act;}

        }
        return null;
    }
    
    
}
