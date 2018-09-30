package com.sjtc.weiwen.news.dao;

import java.util.List;

import com.sjtc.weiwen.news.dao.entity.NewsEntity;

public interface NewsEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(NewsEntity record);

    int insertSelective(NewsEntity record);

    NewsEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(NewsEntity record);

    int updateByPrimaryKeyWithBLOBs(NewsEntity record);

    int updateByPrimaryKey(NewsEntity record);

	List<NewsEntity> selectNewsByFuzz(NewsEntity params);
}