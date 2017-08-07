package com.lvjc.exception.analysis;

/**
 * Created by lvjc on 2017/7/25.
 */
public class AnalyseFailedException extends Exception {

    private static final String ERROR_MESSAGE = "分析失败：请重试";

    public AnalyseFailedException(){
        super(ERROR_MESSAGE);
    }
}
