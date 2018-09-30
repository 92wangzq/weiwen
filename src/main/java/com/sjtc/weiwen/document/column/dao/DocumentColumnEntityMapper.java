package com.sjtc.weiwen.document.column.dao;

import java.util.List;

import com.sjtc.weiwen.document.column.dao.entity.DocumentColumnEntity;

public interface DocumentColumnEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(DocumentColumnEntity record);

    int insertSelective(DocumentColumnEntity record);

    DocumentColumnEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(DocumentColumnEntity record);

    int updateByPrimaryKey(DocumentColumnEntity record);

	List<DocumentColumnEntity> selectDocumentColumnsByFuzz(DocumentColumnEntity params);

	List<DocumentColumnEntity> selectChildrensByPOid(String pOid);
}