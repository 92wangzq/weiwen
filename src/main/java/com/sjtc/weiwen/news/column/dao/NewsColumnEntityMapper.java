package com.sjtc.weiwen.news.column.dao;

import java.util.List;

import com.sjtc.weiwen.news.column.dao.entity.NewsColumnEntity;

public interface NewsColumnEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(NewsColumnEntity record);

    int insertSelective(NewsColumnEntity record);

    NewsColumnEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(NewsColumnEntity record);

    int updateByPrimaryKey(NewsColumnEntity record);

	List<NewsColumnEntity> selectNewsColumnsByFuzz(NewsColumnEntity params);

	List<NewsColumnEntity> selectChildrensByPOid(String pOid);

	List<NewsColumnEntity> selectParentNewsColumns(NewsColumnEntity params);

	List<String> selectNewsColumnOidsByArea(String areaOid);

	List<String> selectNewsColumnOidsByParent(String parentOid);
}