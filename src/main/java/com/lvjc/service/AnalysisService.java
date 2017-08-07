package com.lvjc.service;

import com.lvjc.exception.analysis.AnalysisNotFoundException;
import com.lvjc.exception.transaction.IncorrectDatePatternException;
import com.lvjc.po.DetailUser;
import com.lvjc.po.Transaction;
import com.lvjc.service.analysis.Analysis;
import com.lvjc.service.analysis.AnalysisMonth;
import com.lvjc.service.analysis.AnalysisResult;
import com.lvjc.service.analysis.FieldAnalysisResult;
import com.lvjc.support.util.TableNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvjc on 2017/7/24.
 */
@Service
public class AnalysisService extends BaseService {

    @Autowired
    private TransactionFieldService transactionFieldService;

    public String viewName(String analysisName) throws AnalysisNotFoundException {
        return Analysis.getAnalysis(analysisName).getViewName();
    }

    public AnalysisResult getAnalysisResult(DetailUser user, String analysisId, String year, String month) throws AnalysisNotFoundException, IncorrectDatePatternException {
        Analysis analysis = Analysis.getAnalysis(analysisId);
        String formattedMonth = month.length() == 2 ? month : "0" + month;
        String date = formattedMonth.equals("全部") ? year : year + formattedMonth;
        switch (analysis.getId()){
            case "field":
                return getFieldAnalysisResult(user, date);
        }
        throw new AnalysisNotFoundException();
    }

    /*private AnalysisResult<FieldAnalysisResult> getFieldAnalysisResult(DetailUser user, String date) throws IncorrectDatePatternException {
        List<Transaction> transactions = this.getTransactionsOfDate(user, date);
        Map<String, BigDecimal> expenditureMap = new HashMap<>();
        Map<String, BigDecimal> incomeMap = new HashMap<>();

        for(Transaction transaction : transactions){
            BigDecimal amount = transaction.getAmount();
            String fieldName = transactionFieldService.getTransactionFieldById(user, transaction.getFieldId()).getName();
            if(amount.compareTo(new BigDecimal("0")) > 0){
                if(!incomeMap.containsKey(fieldName)){
                    incomeMap.put(fieldName, new BigDecimal("0"));
                }
                incomeMap.put(fieldName, incomeMap.get(fieldName).add(amount));
            } else{
                if(!expenditureMap.containsKey(fieldName)){
                    expenditureMap.put(fieldName, new BigDecimal("0"));
                }
                expenditureMap.put(fieldName, expenditureMap.get(fieldName).add(amount));
            }
        }
        return new AnalysisResult<>(new FieldAnalysisResult(expenditureMap, incomeMap));
    }*/

    private AnalysisResult<FieldAnalysisResult> getFieldAnalysisResult(DetailUser user, String date) throws IncorrectDatePatternException {
        List<Transaction> transactions = this.getTransactionsOfDate(user, date);
        //每个款项的总收入或支出，总额为正则判断为收入，繁殖为支出
        Map<String, BigDecimal> fieldNameAmountMap = new HashMap<>();

        for(Transaction transaction : transactions){
            BigDecimal amount = transaction.getAmount();
            String fieldName = transactionFieldService.getTransactionFieldById(user, transaction.getFieldId()).getName();
            if(!fieldNameAmountMap.containsKey(fieldName)){
                fieldNameAmountMap.put(fieldName, new BigDecimal("0"));
            }
            fieldNameAmountMap.put(fieldName, fieldNameAmountMap.get(fieldName).add(amount));
        }
        return new AnalysisResult<>(new FieldAnalysisResult(fieldNameAmountMap));
    }

    public List<AnalysisMonth> getAnalysisMonth(){
        List<AnalysisMonth> list = new ArrayList<>(13);
        for(AnalysisMonth month : AnalysisMonth.values()){
            list.add(month);
        }
        return list;
    }

}
