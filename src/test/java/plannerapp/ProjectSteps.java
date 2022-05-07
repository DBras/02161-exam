package plannerapp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {
    private Project project;
    private LocalDate date;
    private PlannerApp planner_app;
    private ErrorMessageHolder error_message_holder;
    private Developer developer;

    public ProjectSteps(PlannerApp planner_app) {
        this.planner_app = planner_app;
        this.error_message_holder = new ErrorMessageHolder();
    }

    @Given("that there is a project with title {string} and starting date {string}")
    public void that_there_is_a_project_with_title_and_starting_date(String title, String start_date_string) {
        date = LocalDate.parse(start_date_string);
        project = new Project(title, date);
        assertTrue(project.getTitle().equals(title)
                && project.getStart_date().toString().equals(start_date_string));
    }

    @When("the project is added")
    public void the_project_is_added() {
        try {
            planner_app.addProject(project);
        } catch (OperationNotAllowedException e) {
            this.error_message_holder.setErrorMessage(e.getMessage());
        }
    }

    @Then("a project with title {string} and starting date {string} is contained in the system")
    public void a_project_with_title_and_starting_date_is_in_the_system(String title, String start_date_string) {
        LocalDate date = LocalDate.parse(start_date_string);
        Object[] projects = planner_app.searchProjectsByTitleAndDate(title, date);
        assertEquals(1, projects.length);
    }

    @Given("there is a project in app with title {string} and starting date {string}")
    public void there_is_a_project_in_app_with_title_and_starting_date(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the error {string} is given")
    public void the_error_is_given(String error_message) {
        assertEquals(error_message, this.error_message_holder.getErrorMessage());
    }

    @Given("that there is a project with no name and starting date {string}")
    public void that_there_is_a_project_with_no_name_and_starting_date(String start_date_string) {
        LocalDate date = LocalDate.parse(start_date_string);
        project = new Project(date);
    }

    @Then("there is a new project with no name and starting date {string} in the system")
    public void there_is_a_new_project_with_no_name_and_starting_date_in_the_system(String start_date_string) {
        LocalDate date = LocalDate.parse(start_date_string);
        Object[] projects = planner_app.searchProjectsByTitleAndDate("", date);
        assertEquals(1, projects.length);
    }

    @And("there are no two projects with the same project number in the system")
    public void thereAreNoTwoProjectsWithTheSameProjectNumberInTheSystem() {
        Set<Integer> unique_project_numbers = planner_app.projects.stream()
                .map(Project::getProject_id)
                .collect(Collectors.toSet());
        assertEquals(unique_project_numbers.size(), planner_app.projects.size());
    }

    @Given("these projects are contained in the system")
    public void these_projects_are_contained_in_the_system(List<List<String>> projects) throws Exception{
        for (List<String> project_info : projects) {
            planner_app.addProject(new Project(project_info.get(0),
                                                LocalDate.parse(project_info.get(1))));
        }
    }

    @Given("the project has no manager")
    public void the_project_has_no_manager() {
        assertNull(this.project.getProjectManager());
    }

    @Given("there is a developer with initials {string}")
    public void there_is_a_developer_with_initials(String initials) {
        this.developer = new Developer(initials);
    }

    @When("the  developer is assigned as project manager")
    public void the_developer_is_assigned_as_project_manager() {
        this.project.setProjectManager(this.developer);
    }

    @Then("the project manager is {string}")
    public void the_project_manager_is(String initials) {
        assertEquals(this.project.getProjectManager().getInitials(), initials);
    }

    @And("the project already has the manager {string}")
    public void theProjectAlreadyHasTheManager(String manager_initials) {
        this.project.setProjectManager(new Developer(manager_initials));
        assertEquals(this.project.getProjectManager().getInitials(), manager_initials);
    }
}
