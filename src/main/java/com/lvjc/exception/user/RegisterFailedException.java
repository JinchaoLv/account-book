package com.lvjc.exception.user;

/**
 * Created by lvjc on 2017/6/27.
 */
public class RegisterFailedException extends Exception{

    private static final String REGISTER_FAILED_ERROR = "注册失败";

    public RegisterFailedException(){
        super(REGISTER_FAILED_ERROR);
    }
}
