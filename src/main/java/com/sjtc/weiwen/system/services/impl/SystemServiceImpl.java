package com.sjtc.weiwen.system.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
	public PageInfo<PermissionVO> getPermissions(PermissionVO permission, Integer limit, Integer offset) {
		SysPermissionEntity params = new SysPermissionEntity();
		params.setName(permission.getName());
		params.setResourceType(permission.getResourceType());
		Page<SysPermissionEntity> page = PageHelper.startPage(offset, limit, true);
		List<SysPermissionEntity> list = this.sysPermissionMapper.selectPermissionsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<PermissionVO> vos = new ArrayList<>();
			for (SysPermissionEntity entity : list) {
				PermissionVO vo = new PermissionVO();
				vo.setOid(entity.getOid());
				vo.setName(entity.getName());
				vo.setAvailable(entity.getAvailable());
				vo.setParentId(entity.getParentId());
				vo.setParentIds(entity.getParentIds());
				vo.setPermission(entity.getPermission());
				vo.setResourceType(entity.getResourceType());
				vo.setUrl(entity.getUrl());
				vos.add(vo);
			}
			PageInfo<PermissionVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
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

	@Override
	public PageInfo<RoleVO> getRoles(RoleVO role, Integer limit, Integer offset) {
		SysRoleEntity params = new SysRoleEntity();
		params.setRole(role.getRole());
		
		Page<SysRoleEntity> page = PageHelper.startPage(offset, limit, true);
		List<SysRoleEntity> list = this.sysRoleMapper.selectRoles(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<RoleVO> vos = new ArrayList<>();
			for (SysRoleEntity entity : list) {
				RoleVO vo = new RoleVO();
				vo.setOid(entity.getOid());
				vo.setAvailable(entity.getAvailable());
				vo.setRole(entity.getRole());
				vo.setDescription(entity.getDescription());
				vo.setPermissions(this.getPermissionsByRole(entity.getOid()));
				vos.add(vo);
			}
			PageInfo<RoleVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}
}
