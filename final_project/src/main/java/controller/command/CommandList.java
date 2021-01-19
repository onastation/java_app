package controller.command;

import controller.command.realization.*;
import model.service.ServiceFactory;

public enum CommandList {
    REGISTER(new RegistrationCommand(ServiceFactory.getInstance().createUserService())) ,
    LOGIN(new LoginCommand(ServiceFactory.getInstance().createUserService())),
    LOGOUT(new LogoutCommand()),
    ADD_ACTIVITY(new AddActivityCommand(ServiceFactory.getInstance().createActivityService())),
    GET_ALL_ACTIVITIES(new GetManipulationPageCommand(ServiceFactory.getInstance().createActivityService())),
    DELETE_ACTIVITY(new DeleteActivityCommand(ServiceFactory.getInstance().createActivityService())),
    EDIT_ACTIVITY(new EditActivityCommand(ServiceFactory.getInstance().createActivityService())),
    ADD_USER(new AddUserCommand(ServiceFactory.getInstance().createUserService())),
    GET_ALL_USERS(new GetUserManipPageCommand(ServiceFactory.getInstance().createUserService())),
    DELETE_USER(new DeleteUserCommand(ServiceFactory.getInstance().createUserService())),
    EDIT_USER(new EditUserCommand(ServiceFactory.getInstance().createUserService()));

    private Command command;
    private CommandList(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
