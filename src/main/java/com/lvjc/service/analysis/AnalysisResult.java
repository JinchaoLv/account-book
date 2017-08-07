package com.lvjc.service.analysis;

/**
 * Created by lvjc on 2017/7/25.
 */
public class AnalysisResult<T>{
    private T data;
    public AnalysisResult(T data){
        this.data = data;
    }
    public T getData(){
        return this.data;
    }
}
