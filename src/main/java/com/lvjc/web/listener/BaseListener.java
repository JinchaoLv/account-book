package com.lvjc.web.listener;

import com.lvjc.service.UserService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lvjc on 2017/7/17.
 */
public class BaseListener implements ServletContextListener {

    private UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userService = (UserService) WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean("userService");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public UserService getUserService(){
        return userService;
    }
}
