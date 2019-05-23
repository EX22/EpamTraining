package by.khomenko.training.finaltask05.exception;

public class ValidationException extends  Exception {

    public ValidationException() {}

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}
