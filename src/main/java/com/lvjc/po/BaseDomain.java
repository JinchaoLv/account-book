package com.lvjc.po;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by lvjc on 2017/6/19.
 */
public class BaseDomain implements Serializable{

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
