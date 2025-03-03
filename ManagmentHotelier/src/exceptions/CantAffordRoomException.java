package exceptions;

public class CantAffordRoomException extends RuntimeException {
  public CantAffordRoomException(String message) {
    super(message);
  }
}
