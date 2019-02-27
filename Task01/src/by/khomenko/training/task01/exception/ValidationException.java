package by.khomenko.training.task01.exception;

/**
 * Exception thrown during array validation, usually means that data are not usable.
 */
public class ValidationException extends Exception {

    /**
     * Default constructor.
     */
    public ValidationException() {

    }

    /**
     * @param message passed to parent constructor.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
