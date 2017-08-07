package com.lvjc.support.incrementer.impl;

import com.lvjc.support.incrementer.PersistentObjectInfo;

/**
 * Created by lvjc on 2017/7/13.
 */
public interface IdGenerationStrategy{

    String nextId(PersistentObjectInfo Info, long sequenceValue) throws Exception;

    PersistentObjectInfo getPersistentObjectInfo(String id) throws Exception;
}
