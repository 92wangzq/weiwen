package com.sjtc.weiwen.user.type.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sjtc.weiwen.user.type.dao.entity.UserTypeEntity;

@Mapper
public interface UserTypeEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(UserTypeEntity record);

    int insertSelective(UserTypeEntity record);

    UserTypeEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(UserTypeEntity record);

    int updateByPrimaryKey(UserTypeEntity record);

	List<UserTypeEntity> selectUserTypes();
}