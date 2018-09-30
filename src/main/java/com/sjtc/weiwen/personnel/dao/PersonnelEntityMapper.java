package com.sjtc.weiwen.personnel.dao;

import java.util.List;

import com.sjtc.weiwen.personnel.dao.entity.PersonnelEntity;

public interface PersonnelEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(PersonnelEntity record);

    int insertSelective(PersonnelEntity record);

    PersonnelEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(PersonnelEntity record);

    int updateByPrimaryKeyWithBLOBs(PersonnelEntity record);

    int updateByPrimaryKey(PersonnelEntity record);

	List<PersonnelEntity> selectPersonnelsByFuzz(PersonnelEntity params);
}