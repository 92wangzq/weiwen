package com.sjtc.weiwen.personnel.type.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sjtc.weiwen.personnel.type.dao.entity.PersonnelTypeEntity;

@Mapper
public interface PersonnelTypeEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(PersonnelTypeEntity record);

    int insertSelective(PersonnelTypeEntity record);

    PersonnelTypeEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(PersonnelTypeEntity record);

    int updateByPrimaryKey(PersonnelTypeEntity record);

	List<PersonnelTypeEntity> selectTypesByFuzz();

	List<PersonnelTypeEntity> selectTypesByPOid(String oid);
}