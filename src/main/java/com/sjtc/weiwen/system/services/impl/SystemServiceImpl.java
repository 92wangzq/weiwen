package com.sjtc.weiwen.system.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.controllers.form.RoleVO;
import com.sjtc.weiwen.system.dao.SysPermissionEntityMapper;
import com.sjtc.weiwen.system.dao.SysRoleEntityMapper;
import com.sjtc.weiwen.system.dao.entity.SysPermissionEntity;
import com.sjtc.weiwen.system.dao.entity.SysRoleEntity;
import com.sjtc.weiwen.system.services.ISystemService;

@Service
public class SystemServiceImpl implements ISystemService {

	@Autowired
	private SysPermissionEntityMapper sysPermissionMapper;
	@Autowired
	private SysRoleEntityMapper sysRoleMapper;
	
	@Override
	public PageInfo<PermissionVO> getPermissions(PermissionVO vo, Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleVO> getRolesByUser(String userOid) {
		List<SysRoleEntity> roleEntitys = this.sysRoleMapper.selectRoleByUser(userOid);
		if (!CollectionUtils.isEmpty(roleEntitys)) {
			List<RoleVO> roles = new ArrayList<>();
			for (SysRoleEntity roleEntity : roleEntitys) {
				RoleVO role = new RoleVO();
				role.setOid(roleEntity.getOid());
				role.setAvailable(roleEntity.getAvailable());
				role.setDescription(roleEntity.getDescription());
				role.setRole(roleEntity.getRole());
				role.setPermissions(this.getPermissionsByRole(roleEntity.getOid()));
				roles.add(role);
			}
			return roles;
		}
		return null;
	}

	@Override
	public List<PermissionVO> getPermissionsByRole(String roleOid) {
		List<SysPermissionEntity> permissionEntitys = this.sysPermissionMapper.selectPermissionByRole(roleOid);
		if (!CollectionUtils.isEmpty(permissionEntitys)) {
			List<PermissionVO> permissions = new ArrayList<>();
			for (SysPermissionEntity permissionEntity : permissionEntitys) {
				PermissionVO permission = new PermissionVO();
				permission.setOid(permissionEntity.getOid());
				permission.setName(permissionEntity.getName());
				permission.setParentId(permissionEntity.getParentId());
				permission.setParentIds(permissionEntity.getParentIds());
				permission.setPermission(permissionEntity.getPermission());
				permission.setResourceType(permissionEntity.getResourceType());
				permission.setUrl(permissionEntity.getUrl());
				permissions.add(permission);
			}
			return permissions;
		}
		return null;
	}

	@Override
	public List<PermissionVO> getMenus(String userOid, String parentId) {
		List<SysPermissionEntity> permissionEntitys = this.sysPermissionMapper.selectPermissionByUser(userOid, parentId);
		if (!CollectionUtils.isEmpty(permissionEntitys)) {
			List<PermissionVO> permissions = new ArrayList<>();
			for (SysPermissionEntity permissionEntity : permissionEntitys) {
				PermissionVO permission = new PermissionVO();
				permission.setOid(permissionEntity.getOid());
				permission.setName(permissionEntity.getName());
				permission.setParentId(permissionEntity.getParentId());
				permission.setParentIds(permissionEntity.getParentIds());
				permission.setPermission(permissionEntity.getPermission());
				permission.setResourceType(permissionEntity.getResourceType());
				permission.setUrl(permissionEntity.getUrl());
				permission.setPermissions(this.getMenus(userOid, permissionEntity.getOid()));
				permissions.add(permission);
			}
			return permissions;
		}
		return null;
	}
}
