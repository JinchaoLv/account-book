package com.lvjc.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lvjc on 2017/6/26.
 */
public class TransactionDaoTest {

    private TransactionDao dao;

    private String tableName = "t_transaction_2017_u1";

    @Before
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        dao = (TransactionDao) context.getBean("transactionDao");
    }

    @Test
    public void testCreateTable(){
        dao.createTable(tableName);
    }
}
