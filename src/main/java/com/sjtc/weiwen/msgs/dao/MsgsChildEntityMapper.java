package com.sjtc.weiwen.msgs.dao;

import java.util.List;

import com.sjtc.weiwen.msgs.dao.entity.MsgsChildEntity;

public interface MsgsChildEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(MsgsChildEntity record);

    int insertSelective(MsgsChildEntity record);

    MsgsChildEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(MsgsChildEntity record);

    int updateByPrimaryKeyWithBLOBs(MsgsChildEntity record);

    int updateByPrimaryKey(MsgsChildEntity record);

	List<MsgsChildEntity> selectMsgsChildsByParent(String parentOid);

	List<MsgsChildEntity> selectMsgsByFuzz(MsgsChildEntity params);
}