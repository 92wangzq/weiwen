package com.sjtc.weiwen.system.dao;

import com.sjtc.weiwen.system.dao.entity.SysDictionaryEntity;

public interface SysDictionaryEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(SysDictionaryEntity record);

    int insertSelective(SysDictionaryEntity record);

    SysDictionaryEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(SysDictionaryEntity record);

    int updateByPrimaryKey(SysDictionaryEntity record);
}