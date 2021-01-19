package controller.command.realization;

import controller.command.Command;
import model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final Logger log = LogManager.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("User " + ((User)(session.getAttribute("user"))).getUsername() + " is logged out");
        request.getSession().invalidate();
        return "redirect:/jsp/index.jsp";
    }
}
