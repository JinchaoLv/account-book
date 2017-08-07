package com.lvjc.service;

import com.lvjc.po.DetailUser;
import com.lvjc.po.TransactionMode;
import com.lvjc.support.incrementer.PersistentObjectInfo;
import com.lvjc.support.util.TableNameUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lvjc on 2017/6/19.
 */
@Service
public class TransactionModeService extends BaseService{

    public TransactionMode getTransactionModeById(DetailUser user, String modeId){
        return transactionModeDao.getEntityById(TableNameUtil.getUserTransactionModeTableName(user), modeId);
    }

    public List<TransactionMode> getAllTransactionModes(DetailUser user){
        String tableName = TableNameUtil.getUserTransactionModeTableName(user);
        return transactionModeDao.getAllEntities(tableName);
    }

    public String addTransactionMode(DetailUser user, TransactionMode mode) throws Exception {
        String tableName = TableNameUtil.getUserTransactionModeTableName(user);
        String id = getIdGenerator(user).nextStringId(new PersistentObjectInfo<>(TransactionMode.class, null));
        mode.setId(id);
        transactionModeDao.addEntity(tableName, mode);
        return id;
    }

    public String deleteTransactionMode(DetailUser user, String id){
        String tableName = TableNameUtil.getUserTransactionModeTableName(user);
        transactionModeDao.deleteEntityById(tableName, id);
        return id;
    }
}
