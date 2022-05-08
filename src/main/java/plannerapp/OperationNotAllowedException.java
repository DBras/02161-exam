package plannerapp;

/**
 * Class creating new type of exception used in the program.
 * This class was copied from the Library App solution.
 * @author Hubert Baumeister
 */
public class OperationNotAllowedException extends Exception{

    private static final long serialVersionUID = 5644804383994321392L;

    /**
     * A new exception is constructed with error message errorMessage.
     * @param errorMessage the error message of the exception
     */
    public OperationNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
