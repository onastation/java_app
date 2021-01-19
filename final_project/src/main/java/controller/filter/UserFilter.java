package controller.filter;

import model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String allowed = "/user";
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        if(session!=null){
            if(((User) (session.getAttribute("user"))).getRole().equalsIgnoreCase("user")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else res.sendError(403);
        } else if(allowed.equalsIgnoreCase(req.getContextPath())){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            res.sendRedirect("/jsp/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
