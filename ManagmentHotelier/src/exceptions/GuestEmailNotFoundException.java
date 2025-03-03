package exceptions;

public class GuestEmailNotFoundException extends RuntimeException {
    public GuestEmailNotFoundException(String message) {
        super(message);
    }
}
