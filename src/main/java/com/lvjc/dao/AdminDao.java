package com.lvjc.dao;

import com.lvjc.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lvjc on 2017/6/27.
 */
@Repository
@Mapper
public interface AdminDao {

    Admin getAdmin(@Param("adminName") String adminName);
}
