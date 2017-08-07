package com.lvjc.dao;

import com.lvjc.support.incrementer.impl.SequenceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by lvjc on 2017/6/20.
 */
@Mapper
@Repository
public interface SequenceInfoDao extends BaseDao<SequenceInfo>{


}
