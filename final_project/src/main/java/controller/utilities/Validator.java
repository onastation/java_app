package controller.utilities;

import model.entity.User;

import java.util.regex.Pattern;

public class Validator {
    public static void checkRegistrationCredentials(User user){
        if(
                Pattern.matches(ValidationRegex.usernameRegex,user.getUsername()) &&
                Pattern.matches(ValidationRegex.passwordRegex,user.getPassword())){
         return;
        }
        throw new CustomException("Invalid input data");
    }
    public static void checkActivityName(String activity){
        if(Pattern.matches(ValidationRegex.activityRegex,activity) && activity.length()!=0){
            return;
        }
        throw new CustomException("Invalid activity name");
    }

}
