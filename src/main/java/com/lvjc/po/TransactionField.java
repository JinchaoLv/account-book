package com.lvjc.po;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/6/19.
 */
@Setter
@Getter
public class TransactionField extends BaseDomain {

    private String id;

    private String name;

    public TransactionField(){}

    public TransactionField(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
