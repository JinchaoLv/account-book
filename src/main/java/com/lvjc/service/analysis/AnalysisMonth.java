package com.lvjc.service.analysis;

/**
 * Created by lvjc on 2017/7/25.
 */
public enum AnalysisMonth {

    All("全部"), Jan("1"), Feb("2"), Mar("3"), Apr("4"), May("5"), Jun("6"), Jul("7"), Aug("8"), Sep("9"), Oct("10"),
    Nov("11"), Dec("12");

    private String value;

    private AnalysisMonth(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
