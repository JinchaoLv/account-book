package com.lvjc.service;

import com.lvjc.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lvjc on 2017/6/28.
 */
public class AdminServiceTest {

    private AdminService adminService;

    @Before
    public void setUp(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        adminService = (AdminService) applicationContext.getBean("adminService");
    }

    @Test
    public void testUpdateDataBase(){

    }
}
