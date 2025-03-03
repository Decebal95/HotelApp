package exceptions;

public class InvalidGuestTypeException extends RuntimeException {
    public InvalidGuestTypeException(String message) {
        super(message);
    }
}
