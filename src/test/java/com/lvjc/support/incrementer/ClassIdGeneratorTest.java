package com.lvjc.support.incrementer;

import com.lvjc.po.Transaction;
import com.lvjc.support.incrementer.impl.SequenceInfo;
import com.lvjc.support.incrementer.impl.ClassIdGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lvjc on 2017/6/21.
 */
public class ClassIdGeneratorTest {

    private ApplicationContext context;

    private ClassIdGenerator idGenerator;

    private DataSource dataSource;
    private String incrementerTableName = "t_id_generator";

    @Before
    public void setUp(){
        this.context = new ClassPathXmlApplicationContext("classpath:applicationContext-mybatis.xml");
        this.dataSource = (DataSource) context.getBean("dataSource");
        idGenerator = (ClassIdGenerator) context.getBean("tableNameIdGenerator");
        idGenerator.setDataSource(dataSource);
        idGenerator.setSequenceTableName(incrementerTableName);
        idGenerator.setCacheSize(5);
    }

    @Test
    public void testInit(){
        idGenerator.init();
        List<SequenceInfo> list = idGenerator.getSequenceInfoList();
        for(SequenceInfo info : list){
            System.out.println(info);
        }
    }
}
