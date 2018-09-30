package com.sjtc.weiwen.system.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.controllers.form.RoleVO;
import com.sjtc.weiwen.system.dao.SysPermissionEntityMapper;
import com.sjtc.weiwen.system.services.ISystemService;
import com.sjtc.weiwen.user.controllers.form.UserVO;

@Service
public class SystemServiceImpl implements ISystemService {

	@Autowired
	private SysPermissionEntityMapper sysPermissionMapper;
	
	@Override
	public PageInfo<PermissionVO> getPermissions(PermissionVO vo, Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleVO> getRolesByUser(UserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermissionVO> getPermissionsByRole(RoleVO role) {
		// TODO Auto-generated method stub
		return null;
	}

}
