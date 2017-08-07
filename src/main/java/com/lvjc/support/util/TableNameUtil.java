package com.lvjc.support.util;

import com.lvjc.cons.JdbcConstants;
import com.lvjc.dao.TableInfoDao;
import com.lvjc.po.DetailUser;
import com.lvjc.po.TableInfo;
import com.lvjc.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvjc on 2017/7/12.
 */
public class TableNameUtil {

    public static String getIdGeneratorTableNameOfUser(User user){
        return JdbcConstants.PREFIX_TABLE_ID_GENERATOR + user.getId();
    }

    public static String getIdGeneratorTableName(){
        return JdbcConstants.TABLE_INCREMENTER;
    }

    public static String getUserTableName(){
        return JdbcConstants.TABLE_USER;
    }

    public static String getAnalysisTableName(){
        return JdbcConstants.TABLE_ANALYSIS;
    }

    public static String getUserTransactionTableNameOfYear(User user, String year){
        return JdbcConstants.PREFIX_TABLE_TRANSACTION + year + "_" + user.getId();
    }

    public static String getUserTransactionModeTableName(User user){
        return JdbcConstants.PREFIX_TABLE_TRANSACTION_MODE + user.getId();
    }

    public static String getUserTransactionFieldTableName(User user){
        return JdbcConstants.PREFIX_TABLE_TRANSACTION_FIELD + user.getId();
    }

    public static String getUserTableInfoName(User user){
        return JdbcConstants.PREFIX_TABLE_TABLE_INFO + user.getId();
    }

    public static List<String> getAllTableNames(DetailUser user, TableInfoDao tableInfoDao){
        List<TableInfo> tableInfos = tableInfoDao.getAllEntities(user.getTableInfo());
        List<String> tableNames = new ArrayList<>(tableInfos.size());
        for(TableInfo tableInfo : tableInfos){
            tableNames.add(tableInfo.getTableName());
        }
        return tableNames;
    }
}
