package com.lvjc.exception.transaction;

/**
 * Created by lvjc on 2017/7/19.
 */
public class UpdateTransactionFailedException extends Exception {

    private static final String ERROR_MESSAGE = "修改交易信息失败";

    public UpdateTransactionFailedException(){
        super(ERROR_MESSAGE);
    }
}
