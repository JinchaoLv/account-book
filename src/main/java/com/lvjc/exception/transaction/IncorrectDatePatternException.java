package com.lvjc.exception.transaction;

/**
 * Created by lvjc on 2017/7/13.
 */
public class IncorrectDatePatternException extends Exception{

    private static final String PATTERN_NOT_MATCH = "日期格式不正确";

    public IncorrectDatePatternException(){
        super(PATTERN_NOT_MATCH);
    }
}
