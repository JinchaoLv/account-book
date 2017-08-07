package com.lvjc.exception.user;

/**
 * Created by lvjc on 2017/6/23.
 */
public class UserExistException extends Exception {

    private static final String USER_EXIST = "用户已存在";

    public UserExistException(){
        super(USER_EXIST);
    }
}
