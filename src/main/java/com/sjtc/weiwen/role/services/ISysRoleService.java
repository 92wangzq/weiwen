package com.sjtc.weiwen.role.services;

import java.util.List;

import com.sjtc.weiwen.role.dao.entity.SysRoleEntity;
import com.sjtc.weiwen.user.controllers.form.UserVO;

public interface ISysRoleService {

	List<SysRoleEntity> selectRoleByUser(UserVO userInfo);

}
