package com.sjtc.weiwen.system.services;

import java.util.List;

import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.controllers.form.RoleVO;
import com.sjtc.weiwen.user.controllers.form.UserVO;

public interface ISystemService {

	PageInfo<PermissionVO> getPermissions(PermissionVO vo, Integer limit, Integer offset);
	
	/**
	 * 获取用户角色
	 * @param user
	 * @return
	 */
	List<RoleVO> getRolesByUser(UserVO user);
	
	/**
	 *  根据角色获取权限
	 * @param user
	 * @return
	 */
	List<PermissionVO> getPermissionsByRole(RoleVO role);

}
