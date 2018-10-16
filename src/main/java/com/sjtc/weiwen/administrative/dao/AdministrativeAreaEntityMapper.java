package com.sjtc.weiwen.administrative.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;
import com.sjtc.weiwen.administrative.dao.entity.AdministrativeAreaEntity;

@Mapper
public interface AdministrativeAreaEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(AdministrativeAreaEntity record);

    int insertSelective(AdministrativeAreaEntity record);

    AdministrativeAreaEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(AdministrativeAreaEntity record);

    int updateByPrimaryKey(AdministrativeAreaEntity record);

	List<AdministrativeAreaEntity> selectAreaListPage(AdministrativeAreaVO vo);

	List<AdministrativeAreaEntity> selectChildrensByOid(String oid);

	List<String> selectChilOidsByParent(String parentID);
}