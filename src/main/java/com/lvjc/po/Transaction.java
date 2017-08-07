package com.lvjc.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lvjc on 2017/6/19.
 */
@Getter
@Setter
public class Transaction extends BaseDomain {

    private String id;

    private Date date;

    private String modeId;

    private BigDecimal amount;

    private String fieldId;

    private String introduction;

    private byte[] detail;

    private String secret;//Yes，No
}
