package com.lvjc.exception.transaction;

/**
 * Created by lvjc on 2017/7/19.
 */
public class DeleteTransactionFailedException extends Exception {

    private static final String ERROR_MESSAGE = "删除交易失败";

    public DeleteTransactionFailedException(){
        super(ERROR_MESSAGE);
    }
}
