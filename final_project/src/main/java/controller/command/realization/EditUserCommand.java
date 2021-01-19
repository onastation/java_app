package controller.command.realization;

import controller.command.Command;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements Command {
    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.setAttribute("user_id",request.getParameter("user_id"));
            return "/jsp/admin/editingUsers.jsp";
        } else {
            userService.updateUser(
                    Long.parseLong(request.getParameter("user_id")),
                    request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("role"));
            return "/admin/managingUsers";
        }
    }
}
