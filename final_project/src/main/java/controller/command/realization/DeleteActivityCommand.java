package controller.command.realization;

import controller.command.Command;
import model.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class DeleteActivityCommand implements Command {
    private ActivityService activityService ;

    public DeleteActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        activityService.deleteActivity(Long.parseLong(request.getParameter("activity_id")));
        return "/admin/managingActivities";
    }

}
