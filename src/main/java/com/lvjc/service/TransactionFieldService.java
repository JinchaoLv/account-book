package com.lvjc.service;

import com.lvjc.po.DetailUser;
import com.lvjc.po.TransactionField;
import com.lvjc.po.TransactionMode;
import com.lvjc.support.incrementer.PersistentObjectInfo;
import com.lvjc.support.util.TableNameUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvjc on 2017/6/19.
 */
@Service
public class TransactionFieldService extends BaseService{

    public TransactionField getTransactionFieldById(DetailUser user, String fieldId){
        return transactionFieldDao.getEntityById(TableNameUtil.getUserTransactionFieldTableName(user), fieldId);
    }

    public List<TransactionField> getAllTransactionFields(DetailUser user){
        String tableName = TableNameUtil.getUserTransactionFieldTableName(user);
        return transactionFieldDao.getAllEntities(tableName);
    }

    public String addTransactionField(DetailUser user, TransactionField field) throws Exception {
        String tableName = TableNameUtil.getUserTransactionFieldTableName(user);
        String id = getIdGenerator(user).nextStringId(new PersistentObjectInfo<>(TransactionField.class, null));
        field.setId(id);
        transactionFieldDao.addEntity(tableName, field);
        return id;
    }

    public String deleteTransactionField(DetailUser user, String id){
        String tableName = TableNameUtil.getUserTransactionFieldTableName(user);
        transactionFieldDao.deleteEntityById(tableName, id);
        return id;
    }
}
