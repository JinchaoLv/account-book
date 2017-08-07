package com.lvjc.config;

import com.lvjc.cons.JdbcConstants;
import com.lvjc.po.DetailUser;
import com.lvjc.po.Transaction;
import com.lvjc.po.TransactionField;
import com.lvjc.po.TransactionMode;
import com.lvjc.support.incrementer.IdGenerator;
import com.lvjc.support.incrementer.impl.ClassIdGenerator;
import com.lvjc.support.incrementer.impl.IdGenerationStrategy;
import com.lvjc.support.util.IdGenerationStrategyFactory;
import com.lvjc.support.util.TableNameUtil;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvjc on 2017/6/20.
 */
@Configuration
@ImportResource("classpath:applicationContext-mybatis.xml")
public class IdGeneratorConfig {

    @Autowired
    @Qualifier("accountBookDataSource")
    private DataSource dataSource;

    @Bean
    @Scope("prototype")
    public ClassIdGenerator userIdGenerator(){
        ClassIdGenerator idGenerator = new ClassIdGenerator();
        idGenerator.setCacheSize(10);

        idGenerator.setDataSource(dataSource);

        Map<Class, String> sequenceIdMap = new HashMap<>(3);
        sequenceIdMap.put(Transaction.class, JdbcConstants.KEY_TRANSACTION);
        sequenceIdMap.put(TransactionField.class, JdbcConstants.KEY_TRANSACTION_FIELD);
        sequenceIdMap.put(TransactionMode.class, JdbcConstants.KEY_TRANSACTION_MODE);
        idGenerator.setSequenceIdMap(sequenceIdMap);

        Map<Class, IdGenerationStrategy> strategyMap = new HashMap<>(1);
        strategyMap.put(Transaction.class, IdGenerationStrategyFactory.transactionIdGenerationStrategy());
        idGenerator.setIdGenerationStrategyMap(strategyMap);

        return idGenerator;
    }

    @Bean
    public ClassIdGenerator adminIdGenerator(){
        ClassIdGenerator idGenerator = new ClassIdGenerator();
        idGenerator.setCacheSize(10);

        idGenerator.setDataSource(dataSource);
        idGenerator.setSequenceTableName(TableNameUtil.getIdGeneratorTableName());
        Map<Class, String> sequenceIdMap = new HashMap<>();
        sequenceIdMap.put(DetailUser.class, JdbcConstants.KEY_USER);
        idGenerator.setSequenceIdMap(sequenceIdMap);

        return idGenerator;
    }
}
