package com.lvjc.dao;

import com.lvjc.cons.JdbcConstants;
import com.lvjc.po.TableInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by lvjc on 2017/6/28.
 */
public class TableInfoDaoTest {

    private TableInfoDao tableInfoDao;

    @Before
    public void setUp(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        this.tableInfoDao = (TableInfoDao) applicationContext.getBean("tableInfoDao");
    }

    @Test
    public void testGetAllEntities(){
        String tableInfo = "t_table_info_u4";
        List<TableInfo> tableInfos = tableInfoDao.getAllEntities(tableInfo);
    }

    @Test
    public void testGetEntity(){
        String tableInfo = "t_table_info_u19";
        TableInfo idGeneratorInfo = tableInfoDao.getEntityById(tableInfo, JdbcConstants.ID_TABLE_ID_GENERATOR);
        System.out.println(idGeneratorInfo);
    }
}
