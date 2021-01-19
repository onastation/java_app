package controller.command.realization;

import controller.command.Command;
import model.entity.User;
import model.exception.UserAlreadyExistsException;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AddUserCommand implements Command {
    private UserService userService;

    public AddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws UserAlreadyExistsException {
        String newUserName = request.getParameter("username");
        String newUserPassword = request.getParameter("password");
        String newUserRole = request.getParameter("role");
        User user = new User(newUserName, newUserPassword, newUserRole);
        userService.createUser(user);
        return "/admin/managingUsers";
    }
}
