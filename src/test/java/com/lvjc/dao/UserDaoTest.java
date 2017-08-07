package com.lvjc.dao;

import com.lvjc.po.DetailUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.List;

/**
 * Created by lvjc on 2017/6/21.
 */
public class UserDaoTest {

    private UserDao dao;

    private DetailUser user;

    private File image = new File("C:/Users/lvjc/Downloads/test.jpg");

    @Before
    public void setUp(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        dao = (UserDao) ctx.getBean("userDao");
        user = new DetailUser();
        user.setId("u2");
        user.setUsername("name");
        user.setPassword("password");
        user.setSex(DetailUser.Sex.FEMALE.getDescription());
        user.setAge(24);
        user.setPhone("13222222222");
        user.setEmail("123@qq.com");
        user.setIcon(file2ByteArray(image));
        user.setSignature("This is me.");
        user.setTableInfo("tableName");
    }

    @Test
    public void testAddUser(){
        //dao.addUser(user);
        dao.addEntity(null, user);
    }

    @Test
    public void testDeleteUser(){
        //dao.deleteUser(user.getId());
        dao.deleteEntity(null, user);
    }

    @Test
    public void testUpdateUser(){
        user.setAge(26);
        dao.updateEntity(null, user);
    }

    @Test
    public void testGetAllUsers(){
        List<DetailUser> users = dao.getAllEntities(null);
        for(DetailUser user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testGetUser(){
        DetailUser user = dao.getEntityById(null,"u1");
        File file = new File("C:/Users/lvjc/Desktop/icon.jpg");
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(user.getIcon());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] file2ByteArray(File file){
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            return FileCopyUtils.copyToByteArray(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
