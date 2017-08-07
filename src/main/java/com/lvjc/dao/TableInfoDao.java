package com.lvjc.dao;

import com.lvjc.po.TableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lvjc on 2017/6/26.
 */
@Repository
@Mapper
public interface TableInfoDao extends BaseDao<TableInfo> {


}
