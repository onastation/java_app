package controller;

import controller.command.Command;
import controller.command.CommandList;
import model.exception.UserAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class Servlet extends HttpServlet {
    private HashMap<String, CommandList> contoller = new HashMap<>();

    @Override
    public void init() throws ServletException {
        contoller.put("/registration", CommandList.REGISTER);
        contoller.put("/logout", CommandList.LOGOUT);
        contoller.put("/login", CommandList.LOGIN);
        contoller.put("/admin/addActivity", CommandList.ADD_ACTIVITY);
        contoller.put("/admin/managingActivities", CommandList.GET_ALL_ACTIVITIES);
        contoller.put("/admin/deleteActivity", CommandList.DELETE_ACTIVITY);
        contoller.put("/admin/editActivity", CommandList.EDIT_ACTIVITY);
        contoller.put("/admin/addUser", CommandList.ADD_USER);
        contoller.put("/admin/managingUsers", CommandList.GET_ALL_USERS);
        contoller.put("/admin/deleteUser", CommandList.DELETE_USER);
        contoller.put("/admin/editUser", CommandList.EDIT_USER);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URI = req.getRequestURI();
        Command command = contoller.get(URI).getCommand();
        String result = null;
        try {
            result = command.execute(req);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }
        if (result.contains("redirect:")) {
            resp.sendRedirect(result.replace("redirect:", ""));
        } else {
            req.getRequestDispatcher(result).forward(req, resp);
        }
    }

}