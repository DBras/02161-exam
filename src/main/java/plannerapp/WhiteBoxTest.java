package plannerapp;

import io.cucumber.java.en_old.Ac;
import io.cucumber.java.hu.De;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WhiteBoxTest {
    PlannerApp planner_app;

    public WhiteBoxTest() {
    }

    // Daniel
    @Test
    public void testSearchProjectsByTitleAndDateSetA() throws OperationNotAllowedException{
        this.planner_app = new PlannerApp();
        LocalDate start_date = LocalDate.parse("2022-06-05");
        Project project = new Project("New Software", start_date);
        this.planner_app.addProject(project);
        List<Project> projects = this.planner_app.searchProjectsByTitleAndDate("New Software", start_date);
        assertTrue(projects.get(0).getTitle().equals("New Software")
                && projects.get(0).getStartDate().equals(start_date)
                && projects.size() == 1);
    }

    // Daniel
    @Test
    public void testSearchProjectsByTitleAndDateSetB() throws OperationNotAllowedException{
        this.planner_app = new PlannerApp();
        LocalDate start_date = LocalDate.parse("2022-06-05");
        List<Project> projects = this.planner_app.searchProjectsByTitleAndDate("New Software Project", start_date);
        assertEquals(0, projects.size());
    }

    // Samuel
    @Test
    public void testAddProjectSetA() {
        LocalDate date = LocalDate.parse("2023-02-06");
        Project project = new Project("Titel", date);
        assertTrue(project.getTitle().equals("Titel") && project.getStartDate().equals(date));
    }

    // Samuel
    @Test
    public void testAddProjectSetB() {
        this.planner_app = new PlannerApp();
        LocalDate date = LocalDate.parse("2023-02-06");
        Project project = new Project("Hej", date);
        String error_message = "";
        try {
            this.planner_app.addProject(project);
            this.planner_app.addProject(new Project("Hej", date));
        } catch (OperationNotAllowedException e) {
            error_message = e.getMessage();
        }
        assertEquals(error_message, "Project already exists in the system");
    }

    // Samuel
    @Test
    public void testAddProjectSetC() {
        this.planner_app = new PlannerApp();
        LocalDate date = LocalDate.parse("2021-06-05");
        String error_message = "";
        Project project = new Project("Hej", date);
        try {
            this.planner_app.addProject(project);
        } catch (OperationNotAllowedException e) {
            error_message = e.getMessage();
        }
        assertEquals(error_message, "Project starting date has expired");
    }

    // Johannes
    @Test
    public void testAddDeveloperSetA() {
        this.planner_app = new PlannerApp();
        Developer developer = new Developer("JBN");
        String error_message = "";
        try {
            this.planner_app.addDeveloper(developer);
        } catch (OperationNotAllowedException e) {
            error_message = e.getMessage();
        }
        assertEquals(error_message, "");
    }

    // Johannes
    @Test
    public void testAddDeveloperSetB() {
        this.planner_app = new PlannerApp();
        Developer dev1 = new Developer("JBN");
        Developer dev2 = new Developer("JBN");
        String error_message = "";
        try {
            this.planner_app.addDeveloper(dev1);
            this.planner_app.addDeveloper(dev2);
        } catch (OperationNotAllowedException e) {
            error_message = e.getMessage();
        }
        assertEquals(error_message, "Developer is already registered in the system");
    }

    // Jonathan
    @Test
    public void testChangeStartDateSetA() {
        String error_message = "";
        try {
            Activity activity = new Activity("Per", 10, 2026, 35, 42);
            activity.changeActivityDate(1979, 57, 75);
        } catch (OperationNotAllowedException e) {
            error_message = e.getMessage();
        }
        assertEquals(error_message, "Activity starting date is expired");
    }

    // Jonathan
    @Test
    public void testChangeStartDateSetB() {
        String error_message = "";
        try {
            Activity activity = new Activity("Per", 10, 2026, 35, 42);
            activity.changeActivityDate(2026, 35, 47);
        } catch (OperationNotAllowedException e) {
            error_message = e.getMessage();
        }
        assertEquals(error_message, "");
    }
}
