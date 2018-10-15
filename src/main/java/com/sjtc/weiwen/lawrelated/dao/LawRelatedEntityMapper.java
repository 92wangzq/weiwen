package com.sjtc.weiwen.lawrelated.dao;

import java.util.List;

import com.sjtc.weiwen.lawrelated.dao.entity.LawRelatedEntity;

public interface LawRelatedEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(LawRelatedEntity record);

    int insertSelective(LawRelatedEntity record);

    LawRelatedEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(LawRelatedEntity record);

    int updateByPrimaryKey(LawRelatedEntity record);

	List<LawRelatedEntity> selectLawRelatedsByFuzz(LawRelatedEntity params);

	int selectCountByCaseType(String caseType, int year, int month);

	List<LawRelatedEntity> selectPercentageGroupByCaseType();
}