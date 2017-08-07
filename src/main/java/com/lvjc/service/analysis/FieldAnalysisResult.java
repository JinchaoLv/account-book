package com.lvjc.service.analysis;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lvjc on 2017/7/25.
 */
@Getter
@Setter
public class FieldAnalysisResult {

    private List<ExpenditureItem> expenditureItems;
    private List<IncomeItem> incomeItems;

    public FieldAnalysisResult(){}

    /*public FieldAnalysisResult(Map<String, BigDecimal> expenditureMap, Map<String, BigDecimal> incomeMap){
        expenditureItems = new ArrayList<>(expenditureMap.size());
        incomeItems = new ArrayList<>(incomeMap.size());

        BigDecimal totalExpenditure = new BigDecimal("0.00");
        BigDecimal totalIncome = new BigDecimal("0.00");

        for(String fieldName : expenditureMap.keySet()){
            totalExpenditure = totalExpenditure.add(expenditureMap.get(fieldName));
            expenditureItems.add(new ExpenditureItem(fieldName, expenditureMap.get(fieldName)));
        }
        expenditureItems.add(new ExpenditureItem("总计", totalExpenditure));

        for(String fieldName : incomeMap.keySet()){
            totalIncome = totalIncome.add(incomeMap.get(fieldName));
            incomeItems.add(new IncomeItem(fieldName, incomeMap.get(fieldName)));
        }
        incomeItems.add(new IncomeItem("总计", totalIncome));
    }*/

    public FieldAnalysisResult(Map<String, BigDecimal> fieldNameAmountMap) {
        //计算所有的支出项和收入项
        expenditureItems = new ArrayList<>();
        incomeItems = new ArrayList<>();
        //在收入项和支出项最后分别加上总收入一项和总支出一项
        BigDecimal totalExpenditure = new BigDecimal("0.00");
        BigDecimal totalIncome = new BigDecimal("0.00");

        for(String fieldName : fieldNameAmountMap.keySet()){
            BigDecimal amount = fieldNameAmountMap.get(fieldName);
            if(amount.compareTo(BigDecimal.ZERO) > 0){
                incomeItems.add(new IncomeItem(fieldName, amount));
                totalIncome = totalIncome.add(amount);
            }else if(amount.compareTo(BigDecimal.ZERO) < 0){
                expenditureItems.add(new ExpenditureItem(fieldName, amount));
                totalExpenditure = totalExpenditure.add(amount);
            }
        }
        expenditureItems.add(new ExpenditureItem("总计", totalExpenditure));
        incomeItems.add(new IncomeItem("总计", totalIncome));
    }
}
