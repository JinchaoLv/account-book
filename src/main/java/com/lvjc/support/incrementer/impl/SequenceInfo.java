package com.lvjc.support.incrementer.impl;

import com.lvjc.po.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by lvjc on 2017/6/20.
 */
@Setter
@Getter
public class SequenceInfo extends BaseDomain{

    private String sequenceId;

    private long sequenceValue;

    public SequenceInfo(){}

    public SequenceInfo(String sequenceId, long sequenceValue){
        this.sequenceId = sequenceId;
        this.sequenceValue = sequenceValue;
    }

}
