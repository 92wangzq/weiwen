package com.sjtc.weiwen.user.type.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.user.type.controllers.form.UserTypeVO;
import com.sjtc.weiwen.user.type.dao.UserTypeEntityMapper;
import com.sjtc.weiwen.user.type.dao.entity.UserTypeEntity;
import com.sjtc.weiwen.user.type.services.IUserTypeService;

@Service
public class UserTypeServiceImpl implements IUserTypeService {

	@Autowired
	private UserTypeEntityMapper userTypeMapper;
	
	@Transactional
	@Override
	public BaseResult save(UserTypeVO vo) {
		BaseResult result = new BaseResult();
		UserTypeEntity entity = new UserTypeEntity();
		if (StringUtil.isEmpty(vo.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setTitle(vo.getTitle());
			this.userTypeMapper.insert(entity);
		} else {
			entity.setTitle(vo.getTitle());
			this.userTypeMapper.updateByPrimaryKeySelective(entity);
		}
		return result;
	}
	
	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.userTypeMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public List<UserTypeVO> getUserTypes() {
		List<UserTypeEntity> list = this.userTypeMapper.selectUserTypes();
		if (list != null && list.size() != 0) {
			List<UserTypeVO> vos = new ArrayList<>();
			for (UserTypeEntity entity : list) {
				UserTypeVO vo = new UserTypeVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

}
