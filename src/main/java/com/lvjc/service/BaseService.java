package com.lvjc.service;

import com.lvjc.dao.*;
import com.lvjc.exception.transaction.IncorrectDatePatternException;
import com.lvjc.po.DetailUser;
import com.lvjc.po.Transaction;
import com.lvjc.support.incrementer.IdGenerator;
import com.lvjc.support.incrementer.impl.ClassIdGenerator;
import com.lvjc.support.util.DateUtil;
import com.lvjc.support.util.TableNameUtil;
import com.lvjc.vo.TransactionVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理用户独立的表
 * Created by lvjc on 2017/7/6.
 */
public class BaseService {

    @Autowired
    protected AdminDao adminDao;

    @Autowired
    protected DropTableDao dropTableDao;

    @Autowired
    protected SequenceInfoDao sequenceInfoDao;

    @Autowired
    protected TableInfoDao tableInfoDao;

    @Autowired
    protected TransactionDao transactionDao;

    @Autowired
    protected TransactionFieldDao transactionFieldDao;

    @Autowired
    protected TransactionModeDao transactionModeDao;

    @Autowired
    protected UserDao userDao;

    /**
     * userId-idGenerator
     */
    private static Map<String, ClassIdGenerator> idGeneratorMap = new HashMap<>();

    public void addUserIdGenerator(DetailUser user, ClassIdGenerator idGenerator){
        idGeneratorMap.put(user.getId(), idGenerator);
    }

    public void removeUserIdGenerator(DetailUser user){
        idGeneratorMap.remove(user.getId());
    }

    public ClassIdGenerator getIdGenerator(DetailUser user){
        return idGeneratorMap.get(user.getId());
    }

    protected List<Transaction> getTransactionsOfDay(DetailUser user, String day) throws IncorrectDatePatternException {
        String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromString(day));
        return transactionDao.getTransactionsByDay(tableName, day);
    }

    protected List<Transaction> getTransactionsOfMonth(DetailUser user, String month) throws IncorrectDatePatternException {
        String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromString(month));
        return transactionDao.getTransactionsByMonth(tableName, month);
    }

    protected List<Transaction> getTransactionsOfYear(DetailUser user, String year){
        String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, year);
        return transactionDao.getTransactionsByYear(tableName);
    }

    public List<Transaction> getTransactionsOfDate(DetailUser user, String date) throws IncorrectDatePatternException {
        DateUtil.DatePattern pattern = DateUtil.checkDatePattern(date);
        if(pattern.equals(DateUtil.DatePattern.DAY))
            return getTransactionsOfDay(user, date);
        if(pattern.equals(DateUtil.DatePattern.MONTH))
            return getTransactionsOfMonth(user, date);
        if(pattern.equals(DateUtil.DatePattern.YEAR)){
            return getTransactionsOfYear(user, date);
        }
        return null;
    }
}
