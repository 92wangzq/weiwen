package com.sjtc.weiwen.msgs.dao;

import java.util.List;

import com.sjtc.weiwen.msgs.dao.entity.MsgsParentEntity;

public interface MsgsParentEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(MsgsParentEntity record);

    int insertSelective(MsgsParentEntity record);

    MsgsParentEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(MsgsParentEntity record);

    int updateByPrimaryKey(MsgsParentEntity record);

	List<MsgsParentEntity> selectMsgsByFuzz(MsgsParentEntity params);
}