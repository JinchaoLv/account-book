package com.lvjc.web;

import com.lvjc.cons.SessionConstants;
import com.lvjc.exception.user.*;
import com.lvjc.po.DetailUser;
import com.lvjc.service.AdminService;
import com.lvjc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.lvjc.cons.JspConstants.ERROR_MESSAGE;

/**
 * Created by lvjc on 2017/6/23.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;



    @RequestMapping(value = "/register")
    public ModelAndView register(DetailUser user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerSuccess");
        try{
            userService.register(user);
        } catch (UserExistException e){
            modelAndView.addObject(ERROR_MESSAGE, e.getMessage());
            modelAndView.setViewName("forward:/register.jsp");
        } catch (RegisterFailedException e) {
            modelAndView.addObject(ERROR_MESSAGE, e.getMessage());
            modelAndView.setViewName("forward:/register.jsp");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, String username, String password){
        ModelAndView modelAndView = new ModelAndView();
        String toUrl = (String) request.getSession().getAttribute(SessionConstants.LOGIN_TO_URL);
        if(adminService.adminLogin(request, username, password)){
            modelAndView.setViewName("admin");
        }else{
            toUrl = toUrl == null ? "user" : "redirect:" + toUrl;
            modelAndView.setViewName(toUrl);
            try{
                userService.login(request, username, password);
            } catch (UserNotExistException unee){
                modelAndView.addObject(ERROR_MESSAGE, unee.getMessage());
                modelAndView.setViewName("forward:/index.jsp");
            } catch (PasswordWrongException pwe){
                modelAndView.addObject(ERROR_MESSAGE, pwe.getMessage());
                modelAndView.setViewName("forward:/index.jsp");
            }
        }
        request.getSession().removeAttribute(SessionConstants.LOGIN_TO_URL);
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        DetailUser user = (DetailUser) request.getSession().getAttribute(SessionConstants.USER);
        userService.logout(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }


}
