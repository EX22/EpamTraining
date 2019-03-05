package by.khomenko.task02.exception;

/**
 * Exception thrown during line parsing, usually means that line
 * has inappropriate syntax format.
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
    public LineSyntaxException(final String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public LineSyntaxException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public LineSyntaxException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
