package controller.command;

import model.exception.UserAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws UserAlreadyExistsException;
}