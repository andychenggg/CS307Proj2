package cse.cs307.databaseproj2.Interceptor;

import cse.cs307.databaseproj2.util.CookieManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserAccessInterceptor implements HandlerInterceptor {
    // We only check whether login
    // if not, redirect to /login
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(CookieManager.checkWhetherValid(request, "loginId"))
            return true;
        else {
            System.err.println("Interceptor");
            response.sendRedirect("/login");
            return false;
        }
    }


}
