package com.sjtc.weiwen.role.dao;

import com.sjtc.weiwen.role.dao.entity.SysRoleEntity;

public interface SysRoleEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(SysRoleEntity record);

    int insertSelective(SysRoleEntity record);

    SysRoleEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int updateByPrimaryKey(SysRoleEntity record);
}