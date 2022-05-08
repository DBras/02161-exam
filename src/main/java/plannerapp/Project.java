package plannerapp;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Class representing a project with name, ID, start date, manager, and activities.
 */
public class Project {
    // A static integer is kept which increases by 1 every time a new project is created.
    private static int rolling_project_id = 0;
    private final String title;
    private final int project_id;
    private LocalDate start_date;
    private Developer project_manager;
    private List<Activity> activities;

    public Project(String title, LocalDate start_date) {
        this.title = title;
        this.start_date = start_date;
        this.project_id = nextProjectID(start_date);
        this.activities = new ArrayList<>();
    }
    /**
     * Project constructor from starting date
     * @author Daniel
     * @param start_date LocalDate of starting date
     */
    public Project(LocalDate start_date) {
        this.title = "";
        this.start_date = start_date;
        this.project_id = nextProjectID(start_date);
        this.activities = new ArrayList<>();
    }

    /**
     * Method for creating next project id which is the year and a rolling ID.
     * @author Daniel
     * @param start_date LocalDate of project starting date - used to specify year
     * @return Integer of next ID
     */
    public int nextProjectID(LocalDate start_date) {
        return (start_date.getYear() % 1000) * 1000 + (++rolling_project_id);
    }

    /**
     * Project title getter
     * @author Daniel
     * @return String of project title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Project ID getter
     * @author Daniel
     * @return Integer of project ID
     */
    public int getProjectID() {
        return this.project_id;
    }

    /**
     * Starting date getter
     * @author Daniel
     * @return LocalDate of project starting date
     */
    public LocalDate getStartDate() {
        return this.start_date;
    }

    /**
     * Project Manager getter
     * @author Daniel
     * @return Developer object representing project manager
     */
    public Developer getProjectManager() {return this.project_manager;}

    public List<Activity> getActivities() {
        return this.activities;
    }

    /**
     * Project manager setter
     * @author Daniel
     * @param project_manager Developer representing project manager
     */
    public void setProjectManager(Developer project_manager) {
        this.project_manager = project_manager;
    }

    /**
     * Setter method for changing project start date
     * @param start_date LocalDate representing new start date
     */
    public void setStartDate(LocalDate start_date) {
        this.start_date = start_date;
    }
    
    public void addActivity(Activity activity) throws OperationNotAllowedException{
    	for (Activity a : this.activities) {
    		if (a.getName().equals(activity.getName())) {
    			throw new OperationNotAllowedException("This activity already exists");
    		}
    	}
    	this.activities.add(activity);
    }
    
    public Object[] searchActivitiesByTitleAndDate(String activityTitle, LocalDate start_date) {
        List<Activity> activity_matching = new ArrayList<>();
        for (Activity activity : this.activities) {
            if (activity.getName().equals(activityTitle) && activity.getStartDate().equals(start_date)) {
                activity_matching.add(activity);
            }
        }
        return activity_matching.toArray();
    }
    
    public void createReport() throws IOException {

        File file = new File(this.title + ".txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

          pw.print("Developers");
          pw.print("Date");
          pw.print("Activity/ pending activity");

          pw.close();
    }
}
