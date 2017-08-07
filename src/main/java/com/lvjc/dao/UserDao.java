package com.lvjc.dao;

import com.lvjc.po.DetailUser;
import com.lvjc.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lvjc on 2017/6/19.
 */
@Mapper
@Repository
public interface UserDao extends BaseDao<DetailUser>{

    DetailUser getDetailUserByName(@Param("username") String username);

    List<User> getAllUsers();

    void updateStateById(@Param("id") String id, @Param("state")String state);
}
