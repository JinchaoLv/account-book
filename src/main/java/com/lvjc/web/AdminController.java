package com.lvjc.web;

import com.lvjc.cons.SessionConstants;
import com.lvjc.po.DetailUser;
import com.lvjc.po.User;
import com.lvjc.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by lvjc on 2017/6/27.
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    private static final String userList = "userList";

    @RequestMapping("/userList")
    public ModelAndView getUserList(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        List<User> users = adminService.getAllUsers();
        modelAndView.addObject(userList, users);
        //request.getSession().setAttribute(SessionConstants.SESSION_USER_LIST, users);
        return modelAndView;
    }

    @RequestMapping("/detail")
    public ModelAndView getDetailOfUser(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping("/delete/{userId}")
    public ModelAndView deleteUser(@PathVariable("userId") String userId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/userList");
        adminService.deleteUserById(userId);
        return modelAndView;
    }



    @RequestMapping("/stopServer")
    public void stopServer(){
        if(adminService.updataDatabase()) {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String pid = name.split("@")[0];
            try {
                Runtime.getRuntime().exec("taskkill /pid " + pid + " /f");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
