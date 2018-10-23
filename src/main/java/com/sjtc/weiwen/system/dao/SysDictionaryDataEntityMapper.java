package com.sjtc.weiwen.system.dao;

import java.util.List;

import com.sjtc.weiwen.system.dao.entity.SysDictionaryDataEntity;

public interface SysDictionaryDataEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(SysDictionaryDataEntity record);

    int insertSelective(SysDictionaryDataEntity record);

    SysDictionaryDataEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(SysDictionaryDataEntity record);

    int updateByPrimaryKey(SysDictionaryDataEntity record);

	List<SysDictionaryDataEntity> selectParentDictionaryDataByDiction(String dictionaryValue);

	List<SysDictionaryDataEntity> selectChildDictionaryDatasByParent(String parentOid);
}