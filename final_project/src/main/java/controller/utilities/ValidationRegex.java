package controller.utilities;

public interface ValidationRegex {
    String usernameRegex = "[a-zA-Z0-9\\._\\-]{3,}";
    String passwordRegex = ".{4,30}";
    String activityRegex = "^[a-zA-Z]*$";
}