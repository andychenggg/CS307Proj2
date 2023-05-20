package cse.cs307.databaseproj2.util;

import java.time.LocalDateTime;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A class user static method to manage Cookie
 */
public class CookieManager {
    private CookieManager(){}

    /**
     * find the current user if it's valid
     * will not update the validity
     * @param request
     * @return
     */
    public static long findCurrentUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("loginId")){
                    return Long.parseLong(cookie.getValue());
                }
            }
        }
        return -1;
    }

    /**
     * update specified cookie to 1h;
     * If cookie value is different, update the value.
     * @param request
     * @param response
     * @param cookieName
     * @return
     */
    public static boolean updateCookieValueAndValidity(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)){
                    if(!cookie.getValue().equals(cookieValue)){
                        // update to a new value
                        cookie.setValue(cookieValue);
                    }
                    cookie.setMaxAge(60*60);
                    response.addCookie(cookie);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Only update the maxAge of specified cookie to 1h;
     * @param request
     * @param response
     * @return
     */
    public static boolean updateCookieValidity(HttpServletRequest request, HttpServletResponse response, String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)){
                    cookie.setMaxAge(60*60);
                    response.addCookie(cookie);
                    return true;
                }
            }
        }
        return false;
    }

    // will not change maxAge
    public static boolean checkWhetherValid(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.err.println(cookie.getName()+" "+cookie.getValue()+" "+cookie.getMaxAge());
                if(cookie.getName().equals(cookieName)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAgeInSeconds){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAgeInSeconds);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
