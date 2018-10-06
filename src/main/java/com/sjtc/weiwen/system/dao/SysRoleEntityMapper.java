package com.sjtc.weiwen.system.dao;

import java.util.List;

import com.sjtc.weiwen.system.dao.entity.SysRoleEntity;

public interface SysRoleEntityMapper {
    int deleteByPrimaryKey(String oid);

    int insert(SysRoleEntity record);

    int insertSelective(SysRoleEntity record);

    SysRoleEntity selectByPrimaryKey(String oid);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int updateByPrimaryKey(SysRoleEntity record);

	List<SysRoleEntity> selectRoleByUser(String userOid);
}