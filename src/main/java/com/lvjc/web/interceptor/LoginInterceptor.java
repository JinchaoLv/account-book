package com.lvjc.web.interceptor;

import com.lvjc.cons.SessionConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lvjc on 2017/7/11.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final String[] URI_ADMIN_LOGIN_NEEDED = {"/admin"};

    private static final String[] URI_USER_LOGIN_NEEDED = {"/transaction"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        for(String uri : URI_ADMIN_LOGIN_NEEDED){
            if(requestUrl.indexOf(uri) >= 0 && request.getSession().getAttribute(SessionConstants.ADMIN) == null){
                forwardToIndex(request, response, null);
                return false;
            }
        }
        for(String uri : URI_USER_LOGIN_NEEDED){
            if(requestUrl.indexOf(uri) >= 0 && request.getSession().getAttribute(SessionConstants.USER) == null){
                forwardToIndex(request, response, requestUrl);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void forwardToIndex(HttpServletRequest request, HttpServletResponse response, String loginToUrl) throws Exception{
        request.getSession().setAttribute(SessionConstants.LOGIN_TO_URL, loginToUrl);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
