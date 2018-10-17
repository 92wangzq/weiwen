package com.sjtc.weiwen.system.dao;

import com.sjtc.weiwen.system.dao.entity.SysRolePermissionEntity;

public interface SysRolePermissionEntityMapper {
    int insert(SysRolePermissionEntity record);

    int insertSelective(SysRolePermissionEntity record);
    
    int deleteByRoleOid(String roleOid);
}