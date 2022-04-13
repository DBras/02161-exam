package plannerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectSteps {
    private Project project;
    private LocalDate date;
    private PlannerApp planner_app;

    public ProjectSteps(PlannerApp planner_app) {
        this.planner_app = planner_app;
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
        planner_app.addProject(project);
    }

    @Then("a project with title {string} and starting date {string} is in the system")
    public void a_project_with_title_and_starting_date_is_in_the_sytem(String title, String start_date_string) {
        Object[] projects = planner_app.searchProjects(title);
        assertEquals(1, projects.length);
    }

    @Given("there is a project in app with title {string} and starting date {string}")
    public void there_is_a_project_in_app_with_title_and_starting_date(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the error {string} is given")
    public void the_error_is_given(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("that there is a project with no name and starting date {string}")
    public void that_there_is_a_project_with_no_name_and_starting_date(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("there is a new project with no name and starting date {string} in the system")
    public void there_is_a_new_project_with_no_name_and_starting_date_in_the_system(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("there are no two projects with the same project number in the system")
    public void thereAreNoTwoProjectsWithTheSameProjectNumberInTheSystem() {
        Set<Integer> unique_project_numbers = planner_app.projects.stream()
                .map(Project::getProject_id)
                .collect(Collectors.toSet());
        System.out.println(unique_project_numbers);
        assertEquals(unique_project_numbers.size(), planner_app.projects.size());
    }
}
