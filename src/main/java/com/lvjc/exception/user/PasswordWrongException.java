package com.lvjc.exception.user;

/**
 * Created by lvjc on 2017/6/27.
 */
public class PasswordWrongException extends Exception {

    private static final String PASSWORD_WRONG = "密码错误";

    public PasswordWrongException(){
        super(PASSWORD_WRONG);
    }
}
