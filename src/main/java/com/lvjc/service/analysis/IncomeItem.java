package com.lvjc.service.analysis;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by lvjc on 2017/7/25.
 */
@Setter
@Getter
public class IncomeItem {

    private String fieldName;
    private BigDecimal amount;

    public IncomeItem(){}

    public IncomeItem(String fieldName, BigDecimal amount){
        this.fieldName = fieldName;
        this.amount = amount;
    }
}
