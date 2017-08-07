package com.lvjc.service.analysis;

import com.lvjc.exception.analysis.AnalysisNotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvjc on 2017/7/25.
 */
@Getter
@Setter
public class Analysis {

    private static Analysis field;
    private static List<Analysis> analysisList = new ArrayList<>();

    static {
        field = new Analysis("field","收支结构","transactionAnalysis");
        analysisList.add(field);
    }

    public static List<Analysis> getAllAnalysis(){
        return analysisList;
    }

    public static Analysis getAnalysis(String analysisId) throws AnalysisNotFoundException {
        for(Analysis analysis : analysisList){
            if(analysis.getId().equals(analysisId))
                return analysis;
        }
        throw new AnalysisNotFoundException();
    }

    public Analysis(String id, String name, String viewName){
        this.id = id;
        this.name = name;
        this.viewName = viewName;
    }

    public Analysis(){}

    private String id;
    private String name;
    private String viewName;
}
