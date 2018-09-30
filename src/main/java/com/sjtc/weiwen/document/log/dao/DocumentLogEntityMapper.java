package com.sjtc.weiwen.document.log.dao;

import java.util.List;

import com.sjtc.weiwen.document.log.dao.entity.DocumentLogEntity;

public interface DocumentLogEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(DocumentLogEntity record);

    int insertSelective(DocumentLogEntity record);

    DocumentLogEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(DocumentLogEntity record);

    int updateByPrimaryKey(DocumentLogEntity record);

	List<DocumentLogEntity> selectByDocumentOid(String documentOid);
}