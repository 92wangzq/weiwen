package com.sjtc.weiwen.system.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.controllers.form.RoleVO;
import com.sjtc.weiwen.system.dao.SysPermissionEntityMapper;
import com.sjtc.weiwen.system.dao.SysRoleEntityMapper;
import com.sjtc.weiwen.system.dao.SysRolePermissionEntityMapper;
import com.sjtc.weiwen.system.dao.SysUserRoleEntityMapper;
import com.sjtc.weiwen.system.dao.entity.SysPermissionEntity;
import com.sjtc.weiwen.system.dao.entity.SysRoleEntity;
import com.sjtc.weiwen.system.dao.entity.SysRolePermissionEntity;
import com.sjtc.weiwen.system.dao.entity.SysUserRoleEntity;
import com.sjtc.weiwen.system.services.ISystemService;

@Service
public class SystemServiceImpl implements ISystemService {

	@Autowired
	private SysPermissionEntityMapper sysPermissionMapper;
	@Autowired
	private SysRoleEntityMapper sysRoleMapper;
	@Autowired
	private SysRolePermissionEntityMapper sysRolePermissionMapper;
	@Autowired
	private SysUserRoleEntityMapper sysUserRoleMapper;

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
				role.setName(roleEntity.getName());
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
				permission.setParent(this.getPermission(permissionEntity.getParentId()));
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
		List<SysPermissionEntity> permissionEntitys = this.sysPermissionMapper.selectPermissionByUser(userOid,
				parentId);
		if (!CollectionUtils.isEmpty(permissionEntitys)) {
			List<PermissionVO> permissions = new ArrayList<>();
			for (SysPermissionEntity permissionEntity : permissionEntitys) {
				PermissionVO permission = new PermissionVO();
				permission.setOid(permissionEntity.getOid());
				permission.setName(permissionEntity.getName());
				permission.setParent(this.getPermission(permissionEntity.getParentId()));
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
				vo.setName(entity.getName());
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

	@Override
	public PageInfo<PermissionVO> getParentPermissions(PermissionVO permission, Integer limit, Integer offset) {
		SysPermissionEntity params = new SysPermissionEntity();
		params.setName(permission.getName());
		params.setParentId("0");
		Page<SysPermissionEntity> page = PageHelper.startPage(offset, limit, true);
		List<SysPermissionEntity> list = this.sysPermissionMapper.selectPermissionsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<PermissionVO> vos = new ArrayList<>();
			for (SysPermissionEntity entity : list) {
				PermissionVO vo = new PermissionVO();
				vo.setOid(entity.getOid());
				vo.setName(entity.getName());
				vo.setAvailable(entity.getAvailable());
				vo.setParent(this.getPermission(entity.getParentId()));
				vo.setParentIds(entity.getParentIds());
				vo.setPermission(entity.getPermission());
				vo.setResourceType(entity.getResourceType());
				vo.setUrl(entity.getUrl());
				vo.setPermissions(this.getChildPermissions(entity.getOid()));
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
	public List<PermissionVO> getChildPermissions(String parentId) {
		SysPermissionEntity params = new SysPermissionEntity();
		params.setParentId(parentId);
		List<SysPermissionEntity> list = this.sysPermissionMapper.selectPermissionsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<PermissionVO> vos = new ArrayList<>();
			for (SysPermissionEntity entity : list) {
				PermissionVO vo = new PermissionVO();
				vo.setOid(entity.getOid());
				vo.setName(entity.getName());
				vo.setAvailable(entity.getAvailable());
				vo.setParent(this.getPermission(entity.getParentId()));
				vo.setParentIds(entity.getParentIds());
				vo.setPermission(entity.getPermission());
				vo.setResourceType(entity.getResourceType());
				vo.setUrl(entity.getUrl());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

	private PermissionVO getPermission(String oid) {
		SysPermissionEntity entity = this.sysPermissionMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			PermissionVO vo = new PermissionVO();
			vo.setOid(entity.getOid());
			vo.setName(entity.getName());
			vo.setAvailable(entity.getAvailable());
			vo.setParentIds(entity.getParentIds());
			vo.setPermission(entity.getPermission());
			vo.setResourceType(entity.getResourceType());
			vo.setUrl(entity.getUrl());
			return vo;
		}
		return null;
	}

	@Transactional
	@Override
	public BaseResult saveRole(RoleVO vo) {
		BaseResult result = new BaseResult();
		if (StringUtils.isEmpty(vo.getOid())) {
			SysRoleEntity entity = new SysRoleEntity();
			entity.setOid(UUID.randomUUID().toString().replace("-", ""));
			entity.setName(vo.getName());
			entity.setRole(vo.getRole());
			entity.setDescription(vo.getDescription());
			this.sysRoleMapper.insertSelective(entity);
			if (!StringUtils.isEmpty(vo.getPermissionOids())) {
				String permissionOids[] = vo.getPermissionOids().split(",");
				for (String permissionOid : permissionOids) {
					SysRolePermissionEntity rolePermission = new SysRolePermissionEntity();
					rolePermission.setPermissionOid(permissionOid);
					rolePermission.setRoleOid(entity.getOid());
					this.sysRolePermissionMapper.insert(rolePermission);
				}
			}
		} else {
			SysRoleEntity entity = this.sysRoleMapper.selectByPrimaryKey(vo.getOid());
			entity.setName(vo.getName());
			entity.setRole(vo.getRole());
			entity.setDescription(vo.getDescription());
			this.sysRoleMapper.updateByPrimaryKeySelective(entity);
			this.sysRolePermissionMapper.deleteByRoleOid(entity.getOid());
			if (!StringUtils.isEmpty(vo.getPermissionOids())) {
				String permissionOids[] = vo.getPermissionOids().split(",");
				for (String permissionOid : permissionOids) {
					SysRolePermissionEntity rolePermission = new SysRolePermissionEntity();
					rolePermission.setPermissionOid(permissionOid);
					rolePermission.setRoleOid(entity.getOid());
					this.sysRolePermissionMapper.insert(rolePermission);
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public BaseResult deleteRole(String oid) {
		this.sysRolePermissionMapper.deleteByRoleOid(oid);
		this.sysRoleMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public List<PermissionVO> getParentPermissionTreeview(String roleOid) {
		List<PermissionVO> permissionVOs = new ArrayList<>();
		if (!StringUtils.isEmpty(roleOid)) {
			permissionVOs = this.getPermissionsByRole(roleOid);
		}
		SysPermissionEntity params = new SysPermissionEntity();
		params.setParentId("0");
		List<SysPermissionEntity> list = this.sysPermissionMapper.selectPermissionsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<PermissionVO> vos = new ArrayList<>();
			for (SysPermissionEntity entity : list) {
				PermissionVO vo = new PermissionVO();
				vo.setOid(entity.getOid());
				vo.setNodeId(entity.getOid());
				vo.setText(entity.getName());
				vo.setNodes(this.getChildPermissionsTreeview(entity.getOid(), permissionVOs));
				if (!CollectionUtils.isEmpty(permissionVOs)) {
					for (PermissionVO permissionVO : permissionVOs) {
						if (permissionVO.getOid().equals(entity.getOid())) {
							vo.setState();
						}
					}
				}
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}
	
	private List<PermissionVO> getChildPermissionsTreeview(String parentId, List<PermissionVO> permissionVOs) {
		SysPermissionEntity params = new SysPermissionEntity();
		params.setParentId(parentId);
		List<SysPermissionEntity> list = this.sysPermissionMapper.selectPermissionsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<PermissionVO> vos = new ArrayList<>();
			for (SysPermissionEntity entity : list) {
				PermissionVO vo = new PermissionVO();
				vo.setOid(entity.getOid());
				vo.setNodeId(entity.getOid());
				vo.setText(entity.getName());
				vo.setNodes(this.getChildPermissionsTreeview(entity.getOid(), permissionVOs));
				if (!CollectionUtils.isEmpty(permissionVOs)) {
					for (PermissionVO permissionVO : permissionVOs) {
						if (permissionVO.getOid().equals(entity.getOid())) {
							vo.setState();
						}
					}
				}
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

	@Transactional
	@Override
	public void saveUserRole(SysUserRoleEntity userRoleEntity) {
		this.sysUserRoleMapper.insertSelective(userRoleEntity);
		
	}

	@Transactional
	@Override
	public void deleteUserRole(String userOid) {
		this.sysUserRoleMapper.deleteByUser(userOid);
	}
}
