package com.lvjc.dao;

import com.lvjc.po.Transaction;
import com.lvjc.support.util.StringUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lvjc on 2017/6/19.
 */
@Repository
@Mapper
public interface TransactionDao extends BaseDao<Transaction>{

    List<Transaction> getTransactionsByMonth(@Param("targetTable") String tableName, @Param("month") String month);

    List<Transaction> getTransactionsByDay(@Param("targetTable") String tableName, @Param("day") String day);

    Transaction getTransactionDetailById(@Param("targetTable")String tableName, @Param("id") String id);

    List<Transaction> getTransactionsByYear(@Param("targetTable") String tableName);
}
