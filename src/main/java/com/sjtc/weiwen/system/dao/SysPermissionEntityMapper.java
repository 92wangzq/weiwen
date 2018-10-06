package com.sjtc.weiwen.system.dao;

import java.util.List;

import com.sjtc.weiwen.system.dao.entity.SysPermissionEntity;

public interface SysPermissionEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(SysPermissionEntity record);

    int insertSelective(SysPermissionEntity record);

    SysPermissionEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(SysPermissionEntity record);

    int updateByPrimaryKey(SysPermissionEntity record);

	List<SysPermissionEntity> selectPermissionByRole(String roleOid);

	List<SysPermissionEntity> selectPermissionByUser(String userOid, String parentId);
}