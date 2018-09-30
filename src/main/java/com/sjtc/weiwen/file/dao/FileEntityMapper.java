package com.sjtc.weiwen.file.dao;

import com.sjtc.weiwen.file.dao.entity.FileEntity;

public interface FileEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(FileEntity record);

    int insertSelective(FileEntity record);

    FileEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(FileEntity record);

    int updateByPrimaryKey(FileEntity record);
}