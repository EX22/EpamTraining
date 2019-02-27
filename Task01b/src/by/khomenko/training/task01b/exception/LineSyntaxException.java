package by.khomenko.training.task01b.exception;

/**
 * Exception thrown during line parsing, usually means that line has inappropriate syntax format.
 */
public class LineSyntaxException extends Exception {

    /**
     * Default constructor.
     */
    public LineSyntaxException() {

    }

    /**
     * @param message passed to parent constructor.
     */
    public LineSyntaxException(String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public LineSyntaxException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public LineSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }


}
