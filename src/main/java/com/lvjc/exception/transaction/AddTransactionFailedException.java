package com.lvjc.exception.transaction;

/**
 * Created by lvjc on 2017/7/14.
 */
public class AddTransactionFailedException extends Exception {

    private static final String ERROR_MESSAGE = "添加交易失败";

    public AddTransactionFailedException(){
        super(ERROR_MESSAGE);
    }
}
