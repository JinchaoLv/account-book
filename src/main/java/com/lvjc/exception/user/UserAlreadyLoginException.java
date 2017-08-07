package com.lvjc.exception.user;


/**
 * Created by lvjc on 2017/6/28.
 */
public class UserAlreadyLoginException extends Exception {

    private static final String USER_ALREADY_LOGIN = "用户已登录";

    public UserAlreadyLoginException(){
        super(USER_ALREADY_LOGIN);
    }
}
