package com.lvjc.exception.analysis;

/**
 * Created by lvjc on 2017/7/24.
 */
public class AnalysisNotFoundException extends Exception {

    private static final String ERROR_MESSAGE = "分析失败：找不到指定的分析服务，请尝试做其它分析";

    public AnalysisNotFoundException(){
        super(ERROR_MESSAGE);
    }
}
