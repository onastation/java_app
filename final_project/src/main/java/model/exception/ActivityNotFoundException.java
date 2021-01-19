package model.exception;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
