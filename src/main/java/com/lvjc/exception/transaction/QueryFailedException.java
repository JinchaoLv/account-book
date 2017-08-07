package com.lvjc.exception.transaction;

/**
 * Created by lvjc on 2017/7/14.
 */
public class QueryFailedException extends Exception {

    private static final String ERROR_MESSAGE = "查询失败";

    public QueryFailedException(){
        super(ERROR_MESSAGE);
    }
}
