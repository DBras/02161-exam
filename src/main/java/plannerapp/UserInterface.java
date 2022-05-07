package plannerapp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class UserInterface {
    PlannerApp planner_app;
    Scanner console;
    private String welcome_message_ascii = " ____  _                              _\n" +
            "|  _ \\| | __ _ _ __  _ __   ___ _ __ / \\   _ __  _ __\n" +
            "| |_) | |/ _` | '_ \\| '_ \\ / _ \\ '__/ _ \\ | '_ \\| '_ \\\n" +
            "|  __/| | (_| | | | | | | |  __/ | / ___ \\| |_) | |_) |\n" +
            "|_|   |_|\\__,_|_| |_|_| |_|\\___|_|/_/   \\_\\ .__/| .__/\n" +
            "                                          |_|   |_|\n";

    public UserInterface(PlannerApp planner_app) {
        this.planner_app = planner_app;
        this.console = new Scanner(System.in);
    }

    /**
     * Method for getting user input as well as printing visual cue.
     * @author Daniel
     * @return String representing user command
     */
    public String fetchUserInput() {
        System.out.print(">>> ");
        return console.nextLine();
    }

    /**
     * Method for printing  help message. Decoupled from main loop method.
     * @author Daniel
     */
    public void printHelpMessage() {
        System.out.println("The available commands are as follows:\n" +
                "project_add <title> <start date> (e.g. project_add \"New Project\" 2022-02-23)\n");
    }

    /**
     * Method for adding project with command line. Includes input sanitation as well as support
     * for errors thrown by PlannerApp.
     * @author Daniel
     * @param command_body String of user command
     */
    public void addProject(String[] command_body) {
        StringJoiner project_title = new StringJoiner(" ");
        int date_placement = command_body.length-1;
        for (int i = 1; i < date_placement; i++) {
            project_title.add(command_body[i]);
        }
        try {
            LocalDate starting_date = LocalDate.parse(command_body[date_placement]);
            Project project = new Project(project_title.toString(), starting_date);
            planner_app.addProject(project);
            System.out.printf("Project \"%s\" added with ID %s%n", project.getTitle(), project.getProject_id());
        } catch (OperationNotAllowedException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(Arrays.toString(command_body));
            System.out.println(project_title);
            System.out.println("An error occurred during date parsing. Make sure it is formatted as YYYY-dd-mm");
        }
    }

    /**
     * Method for listing all projects in the system, decoupled from the main loop.
     * @author Daniel
     */
    public void listProjects() {
        System.out.println("Current projects in PlannerApp are:\n");
        ArrayList<Project> projects = planner_app.projects;
        for (Project project : projects) {
            System.out.printf("ID: %s, title: %s, scheduled starting time: %s%n",
                    project.getProject_id(), project.getTitle(), project.getStart_date());
        }
    }

    /**
     * Method for running the main CLI loop. Should be called to run the user interface. Relies on other methods for
     * functionality to increase code readability.
     * @author Daniel
     */
    public void runMainLoop() {
        String user_input;
        boolean running = true;
        System.out.println("Welcome to the \n" + welcome_message_ascii + "CLI. For help, write help or ?.\n");

        while (running) {
            user_input = fetchUserInput();
            if (user_input.equals("help") || user_input.equals("?")) {
                printHelpMessage();
            } else if (user_input.startsWith("project_add")) {
                addProject(user_input.split(" "));
            } else if (user_input.equals("project_list")) {
                listProjects();
            } else if (user_input.equals("quit") || user_input.equals("exit")) {
                running = false;
            } else {
                System.out.println("Sorry, the input was invalid. For help, write help or ?.");
            }
        }
    }
}
