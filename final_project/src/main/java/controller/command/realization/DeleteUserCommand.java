package controller.command.realization;

import controller.command.Command;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private UserService userService ;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        userService.deleteUser(Long.parseLong(request.getParameter("user_id")));
        return "/admin/managingUsers";
    }

}
