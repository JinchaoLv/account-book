package com.lvjc.po;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/6/19.
 */
@Getter
@Setter
public class TransactionMode extends BaseDomain{

    private String id;

    private String name;

    public TransactionMode(){}

    public TransactionMode(String id, String name){
        this.id = id;
        this.name = name;
    }

}
