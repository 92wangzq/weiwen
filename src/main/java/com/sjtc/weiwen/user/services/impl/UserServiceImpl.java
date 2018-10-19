package com.sjtc.weiwen.user.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;
import com.sjtc.weiwen.system.dao.entity.SysUserRoleEntity;
import com.sjtc.weiwen.system.services.ISystemService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.dao.UserEntityMapper;
import com.sjtc.weiwen.user.dao.entity.UserEntity;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserEntityMapper userMapper;
	@Autowired
	private IAdministrativeAreaService administrativeAreaService;
	@Autowired
	private ISystemService systemService;

	@Transactional
	@Override
	public BaseResult save(UserVO user) {
		BaseResult result = new BaseResult();
		if (StringUtil.isEmpty(user.getOid())) {
			UserEntity entity = new UserEntity();
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setRealName(user.getRealName());
			entity.setUserName(user.getUserName());
			entity.setSalt(UUID.randomUUID().toString().replace("-", ""));
			entity.setUserPwd(
					new SimpleHash("md5", user.getUserPwd(), ByteSource.Util.bytes(entity.getCredentialsSalt()), 2)
							.toString());
			entity.setState(UserEntity.STATE_NORMAL);
			entity.setAreaOid(user.getArea().getOid());
			entity.setInsertTime(new Date());
			entity.setUpdateTime(new Date());
			this.userMapper.insert(entity);
			if (!StringUtils.isEmpty(user.getRoleOids())) {
				String roleOids[] = user.getRoleOids().split(",");
				for (String roleOid : roleOids) {
					SysUserRoleEntity userRoleEntity = new SysUserRoleEntity();
					userRoleEntity.setRoleOid(roleOid);
					userRoleEntity.setUserOid(entity.getOid());
					this.systemService.saveUserRole(userRoleEntity);
				}
			}
		} else {
			UserEntity entity = this.userMapper.selectByPrimaryKey(user.getOid());
			entity.setRealName(user.getRealName());
			entity.setUserName(user.getUserName());
			entity.setState(user.getState());
			entity.setAreaOid(user.getArea().getOid());
			entity.setUpdateTime(new Date());
			this.userMapper.updateByPrimaryKeySelective(entity);
			this.systemService.deleteUserRole(entity.getOid());
			if (!StringUtils.isEmpty(user.getRoleOids())) {
				String roleOids[] = user.getRoleOids().split(",");
				for (String roleOid : roleOids) {
					SysUserRoleEntity userRoleEntity = new SysUserRoleEntity();
					userRoleEntity.setRoleOid(roleOid);
					userRoleEntity.setUserOid(entity.getOid());
					this.systemService.saveUserRole(userRoleEntity);
				}
			}
		}
		return result;
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.userMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public PageInfo<UserVO> getUsers(UserVO user, Integer limit, Integer offset) {
		UserEntity params = new UserEntity();
		params.setRealName(user.getRealName());
		params.setUserName(user.getUserName());
		params.setState(user.getState());
		Page<UserEntity> page = PageHelper.startPage(offset, limit, true);
		List<UserEntity> entitys = this.userMapper.selectUsersByFuzz(params);
		if (entitys != null && entitys.size() > 0) {
			List<UserVO> vos = new ArrayList<>();
			for (UserEntity entity : entitys) {
				UserVO vo = new UserVO();
				vo.setOid(entity.getOid());
				vo.setRealName(entity.getRealName());
				vo.setUserName(entity.getUserName());
				vo.setState(entity.getState());
				vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
				vo.setRoles(this.systemService.getRolesByUser(entity.getOid()));
				vo.setInsertTime(entity.getInsertTime());
				vo.setUpdateTime(entity.getUpdateTime());
				vos.add(vo);
			}
			PageInfo<UserVO> pageInfo = new PageInfo<>();
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
	public UserVO getUser(String oid) {
		UserEntity entity = this.userMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			UserVO vo = new UserVO();
			vo.setOid(entity.getOid());
			vo.setRealName(entity.getRealName());
			vo.setUserName(entity.getUserName());
			vo.setUserPwd(entity.getUserPwd());
			vo.setState(entity.getState());
			vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
			vo.setRoles(this.systemService.getRolesByUser(entity.getOid()));
			vo.setInsertTime(entity.getInsertTime());
			vo.setUpdateTime(entity.getUpdateTime());
			return vo;
		}
		return null;
	}

	@Override
	public UserVO getUserByAccount(String account) {
		UserEntity entity = this.userMapper.selectByUserName(account);
		if (entity != null) {
			UserVO vo = new UserVO();
			vo.setOid(entity.getOid());
			vo.setRealName(entity.getRealName());
			vo.setUserName(entity.getUserName());
			vo.setUserPwd(entity.getUserPwd());
			vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
			vo.setSalt(entity.getSalt());
			vo.setState(entity.getState());
			vo.setInsertTime(entity.getInsertTime());
			vo.setUpdateTime(entity.getUpdateTime());
			vo.setRoles(this.systemService.getRolesByUser(entity.getOid()));
			return vo;
		}
		return null;
	}

	@Override
	public List<UserVO> getUserByArea(String areaOid) {
		if (!StringUtils.isEmpty(areaOid)) {
			List<UserEntity> list = this.userMapper.selectUsersByArea(areaOid);
			if (!CollectionUtils.isEmpty(list)) {
				List<UserVO> vos = new ArrayList<>();
				for (UserEntity entity : list) {
					UserVO vo = new UserVO();
					vo.setOid(entity.getOid());
					vo.setRealName(entity.getRealName());
					vo.setUserName(entity.getUserName());
					vo.setUserPwd(entity.getUserPwd());
					vo.setSalt(entity.getSalt());
					vo.setState(entity.getState());
					vo.setInsertTime(entity.getInsertTime());
					vo.setUpdateTime(entity.getUpdateTime());
					vo.setRoles(this.systemService.getRolesByUser(entity.getOid()));
					vos.add(vo);
				}
				return vos;
			}
		} else {
			throw new IllegalArgumentException();
		}
		return null;
	}

	@Transactional
	@Override
	public BaseResult updateState(String oid, String state) {
		UserEntity entity = this.userMapper.selectByPrimaryKey(oid);
		if (entity == null) {
			return new BaseResult("00001", "用户信息不存在", null);
		}
		entity.setState(state);
		this.userMapper.updateByPrimaryKeySelective(entity);
		return new BaseResult();
	}

}