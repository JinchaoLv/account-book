package com.lvjc.vo;

import com.lvjc.po.BaseDomain;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/7/12.
 */
@Getter
@Setter
public class TransactionVo extends BaseDomain{

    private String id;

    private String date;

    private String mode;

    private String amount;

    private String field;

    private String introduction;

    private String secret;
}
