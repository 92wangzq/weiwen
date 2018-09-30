package com.sjtc.weiwen.document.dao;

import java.util.List;

import com.sjtc.weiwen.document.dao.entity.DocumentEntity;

public interface DocumentEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(DocumentEntity record);

    int insertSelective(DocumentEntity record);

    DocumentEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(DocumentEntity record);

    int updateByPrimaryKey(DocumentEntity record);

	List<DocumentEntity> selectDocumentsByFuzz(DocumentEntity params);
}