package plannerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

	@And("there is a new activity with the title {string}, start {string}")
	public void there_is_a_new_activity_with_the_title(String activityTitle, String start_date) {
	        activitydate = LocalDate.parse(start_date);//start dato
	        activity = new Activity(activityTitle, activitydate); //lav activity med arguments, navn, dato
	}

	@When("the activity is added to the projekt")
	public void the_activity_is_added_to_the_projekt() {
		try {
			this.project.addActivity(this.activity);
		} catch (OperationNotAllowedException e) {
			this.error_message_holder.setErrorMessage(e.getMessage());
		}
		
	}

	@Then("the activity with title {string}, start {string} is contained in the projekt")
	public void the_activity_with_title_start_is_contained_in_the_projekt(String activityTitle, String start_date) {
		LocalDate date = LocalDate.parse(start_date);
        Object[] activity = this.project.searchActivitiesByTitleAndDate(activityTitle, date);
        assertEquals(1, activity.length);
	}

	@When("the activity is changed to {string} and date {string}")
	public void theActivityIsChangedToAndDate(String name, String start_date_string) {
		LocalDate start_date = LocalDate.parse(start_date_string);
		try {
			this.activity.changeStartDate(start_date);
			this.activity.setName(name);
		} catch (OperationNotAllowedException e) {
			this.error_message_holder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity has name {string} and date {string}")
	public void theActivityHasNameAndDate(String name, String start_date_string) {
		LocalDate start_date = LocalDate.parse(start_date_string);
		assertTrue(this.activity.getName().equals(name) && this.activity.getStartDate().equals(start_date));
	}

	@Given("that there is an activity with name {string} and start date {string}")
	public void thatThereIsAnActivityWithNameAndStartDate(String name, String start_date_string) {
		LocalDate start_date = LocalDate.parse(start_date_string);
		this.activity = new Activity(name, start_date);
	}
}
