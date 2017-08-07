package com.lvjc.support.util;

import com.lvjc.cons.JdbcConstants;
import com.lvjc.exception.transaction.IncorrectDatePatternException;
import com.lvjc.po.Transaction;
import com.lvjc.support.incrementer.PersistentObjectInfo;
import com.lvjc.support.incrementer.impl.IdGenerationStrategy;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by lvjc on 2017/7/13.
 */
public class IdGenerationStrategyFactory {

    private static class TransactionIdGenerationStrategy implements IdGenerationStrategy{

        @Override
        public String nextId(PersistentObjectInfo info, long sequenceValue) throws Exception{
            String date = DateUtil.dateToString((Date) info.getInfo());
            return JdbcConstants.KEY_TRANSACTION + date + sequenceValue;
        }

        @Override
        public PersistentObjectInfo getPersistentObjectInfo(String id) throws ParseException, IncorrectDatePatternException {
            Date date = DateUtil.stringToDate(id.substring(2, 10));
            return new PersistentObjectInfo<>(Transaction.class, date);
        }
    }

    public static IdGenerationStrategy transactionIdGenerationStrategy(){
        return new TransactionIdGenerationStrategy();
    }
}
