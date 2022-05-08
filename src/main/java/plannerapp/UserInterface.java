package plannerapp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class UserInterface {
    PlannerApp planner_app;
    Scanner console;
    private final String welcome_message_ascii = " ____  _                              _\n" +
            "|  _ \\| | __ _ _ __  _ __   ___ _ __ / \\   _ __  _ __\n" +
            "| |_) | |/ _` | '_ \\| '_ \\ / _ \\ '__/ _ \\ | '_ \\| '_ \\\n" +
            "|  __/| | (_| | | | | | | |  __/ | / ___ \\| |_) | |_) |\n" +
            "|_|   |_|\\__,_|_| |_|_| |_|\\___|_|/_/   \\_\\ .__/| .__/\n" +
            "                                          |_|   |_|\n";
    private final String syntax_error_message = "Command syntax incorrect. See help message for correct usage";

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
                "project_add <title> <start date> (e.g. project_add New Project 2022-02-23) - add new project\n" +
                "project_list - list all projects in the system\n" +
                "search_id <ID> (e.g. search_id 22001) - display information for project with <ID>\n" +
                "developer_add <initials> (e.g. developer_add Huba) - add new developer to the system\n" +
                "assign_manager <project_id> <manager_initials> (e.g. assign_manager 22001 Huba) - assign project manager\n" +
                "quit or exit - exit the program\n" +
                "help or ? - display this help message\n");
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
        List<Project> projects = planner_app.projects;
        for (Project project : projects) {
            System.out.printf("ID: %s, title: %s, scheduled starting time: %s%n",
                    project.getProject_id(), project.getTitle(), project.getStart_date());
        }
    }

    public void searchProject(String[] command_body) {
        if (command_body.length != 2) {
            System.out.println(syntax_error_message);
        } else {
            Project matching = this.planner_app.searchProjectsById(Integer.parseInt(command_body[1]));
            if (matching == null) {
                System.out.printf("No projects with id %s found%n", command_body[1]);
            } else {
                Developer manager = matching.getProjectManager();
                String manager_string;
                if (manager == null) {
                    manager_string = "None";
                } else {
                    manager_string = manager.getInitials();
                }
                System.out.printf("ID: %s, title: %s, scheduled starting time: %s, manager: %s%n",
                        matching.getProject_id(), matching.getTitle(), matching.getStart_date(),
                        manager_string);
            }
        }
    }

    public void assignManager(String[] command_body) {
        if (command_body.length != 3) {
            System.out.println(syntax_error_message);
        } else {
            try {
                int project_id = Integer.parseInt(command_body[1]);
                String manager_initials = command_body[2];
                Project project = this.planner_app.searchProjectsById(project_id);
                Developer developer = this.planner_app.getDeveloper(manager_initials);
                if (project == null || developer == null) {
                    System.out.println("Manager or project could not be found. Please check your input");
                } else {
                    project.setProjectManager(developer);
                    System.out.printf("Developer %s assigned as manager on project %s%n",
                            developer.getInitials(), project.getTitle());
                }
            } catch (NumberFormatException e) {
                System.out.println("Project ID was in incorrect format");
            }
        }
    }

    public void addDeveloper(String[] command_body) {
        if (command_body.length != 2) {
            System.out.println(syntax_error_message);
        } else if (command_body[1].length() > 4) {
            System.out.println("Developer name must be 4 characters at most");
        }
        else {
            Developer developer = new Developer(command_body[1]);
            try {
                this.planner_app.addDeveloper(developer);
                System.out.printf("Developer %s added%n", developer.getInitials());
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method for running the main CLI loop. Should be called to run the user interface. Relies on other methods for
     * functionality to increase code readability.
     * @author Daniel
     */
    public void runMainLoop() {
        String[] user_input;
        boolean running = true;
        System.out.println("Welcome to the \n" + welcome_message_ascii + "CLI. For help, write help or ?.\n");

        while (running) {
            user_input = fetchUserInput().split(" ");
            switch (user_input[0]) {
                case "help":
                case "?":
                    printHelpMessage();
                    break;
                case "project_add":
                    addProject(user_input);
                    break;
                case "project_list":
                    listProjects();
                    break;
                case "search_id":
                    searchProject(user_input);
                    break;
                case "assign_manager":
                    assignManager(user_input);
                    break;
                case "developer_add":
                    addDeveloper(user_input);
                    break;
                case "quit":
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Sorry, the input was invalid. For help, write help or ?.");
                    break;
            }
        }
    }
}
