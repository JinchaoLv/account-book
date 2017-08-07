package com.lvjc.dao;

import com.lvjc.po.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by lvjc on 2017/6/27.
 */
public class AdminDaoTest {

    private UserDao userDao;

    @Before
    public void setUp(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        userDao = (UserDao) applicationContext.getBean("userDao");
    }

    @Test
    public void testGetAllUsers(){
        List<User> users = userDao.getAllUsers();
        for(User user : users){
            System.out.println(user);
        }
    }
}
