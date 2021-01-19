package controller.command.realization;

import controller.command.Command;
import model.entity.Activity;
import model.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class AddActivityCommand implements Command {
    private ActivityService activityService;

    public AddActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String newActivityName = request.getParameter("activity_name");
        String newActivityCategory = request.getParameter("category");
        Activity activity = new Activity(newActivityName, newActivityCategory);
        activityService.addActivity(activity);
        return "/admin/managingActivities";
    }
}
