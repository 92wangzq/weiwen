package com.sjtc.weiwen.role.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sjtc.weiwen.role.dao.entity.SysRoleEntity;
import com.sjtc.weiwen.role.services.ISysRoleService;
import com.sjtc.weiwen.user.controllers.form.UserVO;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

	@Override
	public List<SysRoleEntity> selectRoleByUser(UserVO userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
