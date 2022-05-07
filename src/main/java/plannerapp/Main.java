package plannerapp;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        PlannerApp planner_app = new PlannerApp();
        UserInterface user_interface = new UserInterface(planner_app);
        user_interface.runMainLoop();
    }
}
