package com.sjtc.weiwen.document.dao;

import com.sjtc.weiwen.document.dao.entity.DocumentAreaEntity;

public interface DocumentAreaEntityMapper {
    int insert(DocumentAreaEntity record);

    int insertSelective(DocumentAreaEntity record);
}