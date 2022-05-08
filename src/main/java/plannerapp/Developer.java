package plannerapp;

/**
 * Class representing a developer
 * @author Daniel
 */
public class Developer {
    private final String initials;

    /**
     * Class contructor for creating developer with given initials
     * @author Daniel
     * @param initials String of developer initials.
     */
    public Developer(String initials) {
        this.initials = initials;
    }

    /**
     * Getter method for getting developer initials
     * @author Daniel
     * @return String representing developer's initials
     */
    public String getInitials() {
        return this.initials;
    }
}
