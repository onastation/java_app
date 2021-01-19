package controller.command.realization;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private UserService userService;
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);


    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession httpSession = request.getSession(true);
        User user = userService.findUserByUsername(request.getParameter("username"));
        String role ;
        if(user!=null){
            role = user.getRole();
            if(user.getPassword().equalsIgnoreCase(request.getParameter("password"))){
                log.info("User " + user.getUsername() + "is logged in");
                httpSession.setAttribute("user",user);
                return role.equalsIgnoreCase("admin")?"/jsp/admin/home.jsp" : "/jsp/home.jsp";
            }
        }else{
            request.setAttribute("error","Invalid username or password");
            return "/jsp/login.jsp";
        }
        return "/jsp/login.jsp";
    }
}
