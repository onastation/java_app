package controller.command.realization;

import controller.command.Command;
import model.entity.Activity;
import model.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetManipulationPageCommand implements Command {
    private ActivityService activitiesService;

    public GetManipulationPageCommand(ActivityService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Activity> listOfActivities = activitiesService.getAllActivities();
        request.setAttribute("activities",listOfActivities);
        return "/jsp/admin/managing.jsp";
    }
}
