package controller.command.realization;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserManipPageCommand implements Command {
    private UserService userService;

    public GetUserManipPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> listOfUsers = userService.getAllUsers();
        request.setAttribute("users",listOfUsers);
        return "/jsp/admin/managingUsers.jsp";
    }
}
