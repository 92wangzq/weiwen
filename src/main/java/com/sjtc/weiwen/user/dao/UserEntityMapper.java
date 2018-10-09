package com.sjtc.weiwen.user.dao;

import java.util.List;

import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.dao.entity.UserEntity;

public interface UserEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

	List<UserEntity> selectUsersByFuzz(UserVO user);

	UserEntity selectByUserName(String loginName);

	List<UserEntity> selectUsersByArea(String areaOid);
}