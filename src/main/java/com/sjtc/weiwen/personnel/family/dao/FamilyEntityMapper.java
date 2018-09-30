package com.sjtc.weiwen.personnel.family.dao;

import java.util.List;

import com.sjtc.weiwen.personnel.family.dao.entity.FamilyEntity;

public interface FamilyEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(FamilyEntity record);

    int insertSelective(FamilyEntity record);

    FamilyEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(FamilyEntity record);

    int updateByPrimaryKey(FamilyEntity record);

	List<FamilyEntity> selectFamilysByPersonnelOid(String personnelOid);
}