package com.lvjc.service;

import com.lvjc.exception.transaction.*;
import com.lvjc.exception.user.PasswordWrongException;
import com.lvjc.po.DetailUser;
import com.lvjc.po.Transaction;
import com.lvjc.po.TransactionField;
import com.lvjc.po.TransactionMode;
import com.lvjc.support.incrementer.PersistentObjectInfo;
import com.lvjc.support.util.DateUtil;
import com.lvjc.support.util.StringUtil;
import com.lvjc.support.util.TableNameUtil;
import com.lvjc.vo.TransactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lvjc on 2017/7/6.
 */
@Service
public class TransactionService extends BaseService {

    @Autowired
    TransactionModeService transactionModeService;
    @Autowired
    TransactionFieldService transactionFieldService;

    private static final String SECRET_STRING = "****";

    public String addTransaction(DetailUser user, Transaction transaction) throws AddTransactionFailedException {
        try{
            Date date = transaction.getDate();
            String transactionTableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
            String id = getIdGenerator(user).nextStringId(new PersistentObjectInfo<>(Transaction.class, date));
            transaction.setId(id);
            transactionDao.addEntity(transactionTableName, transaction);
            return id;
        } catch (Exception e){
            throw new AddTransactionFailedException();
        }
    }

    public String updateTransaction(DetailUser user, Transaction transaction) throws UpdateTransactionFailedException {
        Date date = transaction.getDate();
        String transactionTableName = null;
        try {
            transactionTableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
        } catch (IncorrectDatePatternException e) {
            throw new UpdateTransactionFailedException();
        }
        transactionDao.updateEntity(transactionTableName, transaction);
        return transaction.getId();
    }

    public void deleteTransaction(DetailUser user, String id) throws DeleteTransactionFailedException {
        try{
            Date date = this.getDateById(user, id);
            String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
            transactionDao.deleteEntityById(tableName, id);
        } catch (Exception e){
            throw new DeleteTransactionFailedException();
        }
    }

    public List<TransactionVo> getTransactionsOfCurrentMonth(DetailUser user) throws QueryFailedException {
        try{
            String month = DateUtil.getMonthFromString(DateUtil.currentDate());
            List<Transaction> transactions = getTransactionsOfMonth(user, month);
            List<TransactionVo> transactionVos = buildTransactionVoList(user, transactions);
            return transactionVos;
        }catch (Exception e){
            throw new QueryFailedException();
        }
    }

    public TransactionVo getTransactionById(DetailUser user, String id) throws QueryFailedException {
        try{
            Date date = this.getDateById(user, id);
            String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
            return buildTransactionVo(user, transactionDao.getEntityById(tableName, id));
        }catch (Exception e){
            throw new QueryFailedException();
        }
    }

    public String getDetailById(DetailUser user, String id) throws QueryFailedException {
        try{
            Date date = getDateById(user, id);
            String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
            Transaction transaction = transactionDao.getTransactionDetailById(tableName, id);
            byte[] detail = transaction.getDetail();
            detail = detail == null ? new byte[]{} : detail;
            String secret = transaction.getSecret();
            String detailStr = secret.toUpperCase().equals("YES") ? SECRET_STRING : StringUtil.byteArrayToString(detail);
            return detailStr;
        } catch (Exception e){
            throw new QueryFailedException();
        }
    }

    public List<TransactionVo> buildTransactionVoList(DetailUser user, List<Transaction> transactions){
        List<TransactionVo> list = new ArrayList<>();
        for(Transaction transaction : transactions){
            list.add(buildTransactionVo(user, transaction));
        }
        return list;
    }

    public TransactionVo buildTransactionVo(DetailUser user, Transaction transaction){
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setId(transaction.getId().toUpperCase());
        transactionVo.setDate(DateUtil.dateToString(transaction.getDate(), "yyyy-MM-dd"));
        TransactionMode mode = transactionModeService.getTransactionModeById(user, transaction.getModeId());
        String modeName = mode == null ? null : mode.getName();
        transactionVo.setMode(modeName);
        TransactionField field = transactionFieldService.getTransactionFieldById(user, transaction.getFieldId());
        String fieldName = field == null ? null : field.getName();
        transactionVo.setField(fieldName);
        transactionVo.setAmount(transaction.getAmount().toString());
        transactionVo.setIntroduction(transaction.getIntroduction());
        transactionVo.setSecret(transaction.getSecret());
        checkForSecretTransactionField(transactionVo);
        return transactionVo;
    }

    public Transaction buildTransaction(DetailUser user, TransactionVo transactionVo, String detail) throws Exception {
        String id = transactionVo.getId();
        Transaction transaction;
        if(id == null){
            transaction = new Transaction();
        } else{
            Date date = getDateById(user, id);
            String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
            transaction = transactionDao.getEntityById(tableName, id);
        }
        transaction.setDate(DateUtil.stringToDate(transactionVo.getDate()));
        transaction.setModeId(transactionModeService.getTransactionModeById(user, transactionVo.getMode()).getId());
        transaction.setFieldId(transactionFieldService.getTransactionFieldById(user, transactionVo.getField()).getId());
        transaction.setSecret(transactionVo.getSecret());
        String amount = transactionVo.getAmount();
        if(amount != null && !amount.equals(SECRET_STRING)){
            checkForNumber(amount);
            transaction.setAmount(new BigDecimal(amount));
        }
        String introduction = transactionVo.getIntroduction();
        if(introduction != null && !introduction.equals(SECRET_STRING)){
            transaction.setIntroduction(transactionVo.getIntroduction());
        }
        if(detail != null && !detail.equals(SECRET_STRING)){
            transaction.setDetail(StringUtil.stringToByteArray(detail));
        } else {
            Date date = getDateById(user, id);
            String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromDate(date));
            Transaction detailTransaction = transactionDao.getTransactionDetailById(tableName, id);
            byte[] detailByte = detailTransaction.getDetail();
            detailByte = detailByte == null ? new byte[]{} : detailByte;
            transaction.setDetail(detailByte);
        }
        return transaction;
    }

    public void checkUpdatePassword(DetailUser user, TransactionVo transactionVo, String password) throws IncorrectDatePatternException, PasswordWrongException {
        String id = transactionVo.getId();
        String tableName = TableNameUtil.getUserTransactionTableNameOfYear(user, DateUtil.getYearFromString(transactionVo.getDate()));
        Transaction origin = transactionDao.getEntityById(tableName, id);
        String originSecret = origin.getSecret();
        String newSecret = transactionVo.getSecret();
        if(!originSecret.toUpperCase().equals(newSecret.toUpperCase()) && newSecret.toUpperCase().equals("NO")){
            String userPassword = user.getPassword();
            if(!userPassword.equals(password))
                throw new PasswordWrongException();
        }
    }

    public Date getDateById(DetailUser user, String id) throws Exception {
        Date date = (Date) this.getIdGenerator(user).getPersistentObjectInfo(id, Transaction.class).getInfo();
        return date;
    }

    private void checkForNumber(String str){
        Double.parseDouble(str);
    }

    private void checkForSecretTransactionField(TransactionVo transactionVo){
        if(transactionVo != null && transactionVo.getSecret().toUpperCase().equals("YES")){
            transactionVo.setAmount(SECRET_STRING);
            transactionVo.setIntroduction(SECRET_STRING);
        }
    }
}
