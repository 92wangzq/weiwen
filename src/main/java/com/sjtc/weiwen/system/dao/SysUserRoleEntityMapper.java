package com.sjtc.weiwen.system.dao;

import com.sjtc.weiwen.system.dao.entity.SysUserRoleEntity;

public interface SysUserRoleEntityMapper {
    int insert(SysUserRoleEntity record);

    int insertSelective(SysUserRoleEntity record);

	void deleteByUser(String userOid);
}