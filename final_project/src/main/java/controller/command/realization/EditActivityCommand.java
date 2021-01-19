package controller.command.realization;

import controller.command.Command;
import model.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class EditActivityCommand implements Command {
    private ActivityService activityService;

    public EditActivityCommand(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.setAttribute("activity_id",request.getParameter("activity_id"));
            return "/jsp/admin/editingActivities.jsp";
        } else {
            activityService.updateActivity(
                    Long.parseLong(request.getParameter("activity_id")),
                    request.getParameter("activity_name"),
                    request.getParameter("category"));
            return "/admin/managingActivities";
        }
    }
}
