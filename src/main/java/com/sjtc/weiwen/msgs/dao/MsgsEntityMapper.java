package com.sjtc.weiwen.msgs.dao;

import java.util.List;

import com.sjtc.weiwen.msgs.dao.entity.MsgsEntity;

public interface MsgsEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(MsgsEntity record);

    int insertSelective(MsgsEntity record);

    MsgsEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(MsgsEntity record);

    int updateByPrimaryKey(MsgsEntity record);

	List<MsgsEntity> selectMsgsByFuzz(MsgsEntity params);
}