package controller.command.realization;

import controller.command.Command;
import controller.utilities.CustomException;
import controller.utilities.Validator;
import model.entity.User;
import model.exception.UserAlreadyExistsException;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        if (username == null) {
            return "/jsp/registration.jsp";
        }
        User newUser = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                "user");

        try {
            Validator.checkRegistrationCredentials(newUser);
        }catch (CustomException customException) {
            request.setAttribute("error",customException.getMessage());
            return "/jsp/registration.jsp";
        }
        try {
            userService.createUser(newUser);
        } catch (UserAlreadyExistsException e) {
            request.setAttribute("error","User already exists");
        }
        return "redirect:/jsp/login.jsp";
    }
}

