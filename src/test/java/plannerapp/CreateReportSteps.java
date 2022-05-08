package plannerapp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.De;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CreateReportSteps {

    private ErrorMessageHolder error_message_holder;
    private Project project;
    private PlannerApp planner_app;

    public CreateReportSteps(PlannerApp planner_app) {
        this.planner_app = planner_app;
    }



    @Given("there is a project with description {string} and start date {string}")
    public void there_is_an_repport_with_description(String title, String start_date_string) {
        LocalDate start_date = LocalDate.parse(start_date_string);
        this.project = new Project(title, start_date);
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the project report is created")
    public void the_project_report_is_created() throws IOException{
        this.project.createReport();
    }


    @Then("there is a file with name {string}")
    public void the_repport_and_start_date_and_end_date_is_added_to_the_project(String file_name) {
    File f = new File(file_name);
    assertTrue(f.exists() && !f.isDirectory());
    }

    }