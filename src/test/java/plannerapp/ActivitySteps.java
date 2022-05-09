package plannerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Class of all steps relating to activities
 * @author Johannes
 */
public class ActivitySteps {
	
	ArrayList<Activity> activities;
    List<Developer> developers;
	
    private Activity activity;
    private LocalDate activitydate;
    private PlannerApp planner_app;
    private Developer developer;
    private ErrorMessageHolder error_message_holder;
	private Object errorMessageHolder;
	private Project project;
	
  
    public ActivitySteps(PlannerApp planner_app) {
    	this.planner_app = planner_app;
        this.error_message_holder = new ErrorMessageHolder();
        this.developers = new ArrayList<>();
        
    }
    
  

	@Given("there is an a new developer called {string}")
	public void there_is_an_a_new_developer_called(String initials) {
        developer = new Developer(initials);   
	}

	@When("the developer called {string} is added to the system")
	public void the_developer_called_is_added_to_the_system(String string) {
		 try {
	            planner_app.addDeveloper(developer);	
	        } catch (OperationNotAllowedException e) {
	            this.error_message_holder.setErrorMessage(e.getMessage());
	        }
	}
	@Then("the devolomer with name {string} is part of the of the system")
	public void the_devolomer_with_name_is_part_of_the_of_the_system(String initials) {
		Developer d = planner_app.getDeveloper(initials);
		assertEquals(d.getInitials(), initials);
	}
	
	
	
	
	@Given("there is a developer called {string}")
	public void there_is_a_developer_called(String initials) {
		developer = new Developer(initials); 
	}
	
	@And("the developer {string} is already in the system")
	public void the_developer_is_already_in_the_system(String initials) throws OperationNotAllowedException {
		this.planner_app.addDeveloper(new Developer(initials));
	}
	

	@When("the developer is added to the system")
	public void the_developer_is_added_to_the_system() {
		
		try {
            planner_app.addDeveloper(developer);	
        } catch (OperationNotAllowedException e) {
            this.error_message_holder.setErrorMessage(e.getMessage());
        }
	    
	}

	@Then("The error message {string} is given")
	public void the_error_message_is_given(String errorMessage){
		assertEquals(this.error_message_holder.getErrorMessage(), errorMessage);
	}
	
	
	@Given("that there is a project with name {string} and start date {string}")
	public void that_there_is_a_project_with_name_and_start_date(String title, String start_date_string) {
	    LocalDate start_date = LocalDate.parse(start_date_string);
	    this.project = new Project(title, start_date);
	}
	
	
	
	@Given("it is a Project manager")
	public void it_is_a_project_manager() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@And("there is a new activity with the title {string}, expected time {int}, start year {int}, start week {int}, and end week {int}")
	public void thereIsANewActivityWithTheTitleStartYearStartWeekAndEndWeek(String activityTitle, int expected_hours,
																			int year, int start_week, int end_week) {
		activity = new Activity(activityTitle, expected_hours, year, start_week, end_week);
	}

	@When("the activity is added to the project")
	public void the_activity_is_added_to_the_projekt() {
		try {
			this.project.addActivity(this.activity);
		} catch (OperationNotAllowedException e) {
			this.error_message_holder.setErrorMessage(e.getMessage());
		}
		
	}

	@Then("the activity with title {string}, expected time {int}, start year {int}, start week {int}, and end week {int} is contained in the project")
	public void theActivityWithTitleExpectedTimeStartYearStartWeekAndEndWeekIsContainedInTheProject(
			String activityTitle, int expected_hours, int year, int start_week, int end_week) {
		Object[] activities = this.project.searchActivitiesByTitleAndStartYear(activityTitle, year);
		assertEquals(1, activities.length);
		Activity activity = (Activity) activities[0];
		assertTrue(activity.getExpectedTime() == expected_hours
			&& activity.getYear() == year
			&& activity.getStartWeek() == start_week
			&& activity.getEndWeek() == end_week);
	}

	@When("the activity is changed to {string}, start year {int}, start week {int}, and end week {int}")
	public void theActivityIsChangedToStartYearStartWeekAndEndWeek(String name,
																   int year, int start_week, int end_week) {
		try {
			this.activity.changeActivityDate(year, start_week, end_week);
			this.activity.setName(name);
		} catch (OperationNotAllowedException e) {
			this.error_message_holder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity has name {string}, start year {int}, start week {int}, and end week {int}")
	public void theActivityHasNameStartYearStartWeekAndEndWeek(String name, int year, int start_week, int end_week) {
		assertTrue(this.activity.getName().equals(name)
			&& this.activity.getYear() == year
			&& this.activity.getStartWeek() == start_week
			&& this.activity.getEndWeek() == end_week);
	}
}
