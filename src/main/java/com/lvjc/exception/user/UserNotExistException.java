package com.lvjc.exception.user;

/**
 * Created by lvjc on 2017/6/27.
 */
public class UserNotExistException extends Exception {

    private static final String USER_NOT_EXIST = "用户不存在";

    public UserNotExistException(){
        super(USER_NOT_EXIST);
    }
}
