package com.lvjc.support.incrementer;

/**
 * Created by lvjc on 2017/7/14.
 */
public class PersistentObjectInfo<T> {

    private Class persisClass;

    private T info;

    public PersistentObjectInfo(Class persisClass, T info){
        this.persisClass = persisClass;
        this.info = info;
    }

    public Class getPersistentClass(){
        return this.persisClass;
    }

    public T getInfo(){
        return info;
    }
}
