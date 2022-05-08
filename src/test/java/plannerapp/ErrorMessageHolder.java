package plannerapp;

/**
 * Class for holding an error message
 * @author Daniel
 */
public class ErrorMessageHolder {
    private String errorMessage = "";

    /**
     * Getter method for getting error message stored in holder
     * @author Daniel
     * @return String of error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Setter for setting error message to be stored in the holder
     * @author Daniel
     * @param errorMessage String of error message to store
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
