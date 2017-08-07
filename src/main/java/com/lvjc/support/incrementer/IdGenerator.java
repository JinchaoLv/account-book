package com.lvjc.support.incrementer;

/**
 * Created by lvjc on 2017/6/19.
 */
public interface IdGenerator {

    /**
     * 取不同类型的id值
     * @param info
     * @return
     */
    int nextIntId(PersistentObjectInfo info) throws Exception;
    long nextLongId(PersistentObjectInfo info) throws Exception;
    String nextStringId(PersistentObjectInfo info) throws Exception;

}
