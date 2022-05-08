package plannerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
	
    private PlannerApp planner_app;
    private Developer developer;
    private ErrorMessageHolder error_message_holder;
	private Object errorMessageHolder;
  
    public ActivitySteps(PlannerApp planner_app) {
    	this.planner_app = planner_app;
        this.error_message_holder = new ErrorMessageHolder();
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
	
}
