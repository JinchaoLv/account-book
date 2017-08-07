package com.lvjc.po;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/6/26.
 */
@Setter
@Getter
public class TableInfo extends BaseDomain {

    private String tableName;

    public TableInfo(String tableName){
        this.tableName = tableName;
    }
}
