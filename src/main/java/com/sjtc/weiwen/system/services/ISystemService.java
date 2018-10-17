package com.sjtc.weiwen.system.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.controllers.form.RoleVO;

public interface ISystemService {

	/**
	 * 获取所有父级资源
	 * 
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<PermissionVO> getParentPermissions(PermissionVO vo, Integer limit, Integer offset);

	/**
	 * 根据父级ID获取子级列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<PermissionVO> getChildPermissions(String parentId);

	/**
	 * 获取资源treeview列表
	 * 
	 * @return
	 */
	List<PermissionVO> getParentPermissionTreeview(String roleOid);
	
	/**
	 * 根据角色获取权限
	 * 
	 * @param user
	 * @return
	 */
	List<PermissionVO> getPermissionsByRole(String roleOid);

	/**
	 * 获取资源
	 * 
	 * @param oid
	 * @return
	 */
	List<PermissionVO> getMenus(String userOid, String parentId);

	/**
	 * 新增或修改角色
	 * 
	 * @param vo
	 * @return
	 */
	BaseResult saveRole(RoleVO vo);

	/**
	 * 删除角色
	 * 
	 * @param oid
	 * @return
	 */
	BaseResult deleteRole(String oid);

	/**
	 * 获取角色列表
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<RoleVO> getRoles(RoleVO vo, Integer limit, Integer offset);

	/**
	 * 获取用户角色
	 * 
	 * @param user
	 * @return
	 */
	List<RoleVO> getRolesByUser(String userOid);
}
