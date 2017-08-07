package com.lvjc.service.analysis;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lvjc on 2017/7/25.
 */
@Getter
@Setter
public class AnalysisParameter {

    private String analysisName;

    private String year;

    private String month;

    public AnalysisParameter(){}


    public AnalysisParameter(String analysisName, String year, String month) {
        this.analysisName = analysisName;
        this.year = year;
        this.month = month;
    }
}
