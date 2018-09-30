package com.sjtc.weiwen.personnel.type.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.personnel.type.controllers.form.PersonnelTypeVO;
import com.sjtc.weiwen.personnel.type.dao.PersonnelTypeEntityMapper;
import com.sjtc.weiwen.personnel.type.dao.entity.PersonnelTypeEntity;
import com.sjtc.weiwen.personnel.type.services.IPersonnelTypeService;

@Service
public class PersonnelTypeServiceImpl implements IPersonnelTypeService {

	
	@Autowired
	private PersonnelTypeEntityMapper personnelTypeMapper;
	
	@Override
	public BaseResult save(PersonnelTypeVO type) {
		PersonnelTypeEntity entity = new PersonnelTypeEntity();
		if (StringUtil.isEmpty(type.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setTitle(type.getTitle());
			entity.setpOid(type.getType().getOid());
			this.personnelTypeMapper.insert(entity);
		} else {
			entity.setOid(type.getOid());
			entity.setTitle(type.getTitle());
			entity.setpOid(type.getType().getOid());
			this.personnelTypeMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}

	@Override
	public PageInfo<PersonnelTypeVO> getTypes(Integer limit, Integer offset) {
		Page<PersonnelTypeEntity> page = PageHelper.startPage(offset, limit, true);
		List<PersonnelTypeEntity> list = this.personnelTypeMapper.selectTypesByFuzz();
		if (list != null && list.size() > 0) {
			List<PersonnelTypeVO> vos = new ArrayList<>();
			for (PersonnelTypeEntity entity : list) {
				PersonnelTypeVO vo = new PersonnelTypeVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vo.setType(this.getPersonnelType(entity.getpOid()));
				vos.add(vo);
			}
			PageInfo<PersonnelTypeVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}

	private PersonnelTypeVO getPersonnelType(String oid) {
		PersonnelTypeEntity entity = this.personnelTypeMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			PersonnelTypeVO vo = new PersonnelTypeVO();
			vo.setOid(entity.getOid());
			vo.setTitle(entity.getTitle());
			return vo;
		}
		return null;
	}

	@Override
	public List<PersonnelTypeVO> getTypeChildren(String pOid) {
		List<PersonnelTypeEntity> list = this.personnelTypeMapper.selectTypesByPOid(pOid);
		if (list != null && list.size() > 0) {
			List<PersonnelTypeVO> vos = new ArrayList<>();
			for (PersonnelTypeEntity entity : list) {
				PersonnelTypeVO vo = new PersonnelTypeVO();
				vo.setOid(entity.getOid());
				vo.setTitle(entity.getTitle());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

	@Override
	public PersonnelTypeVO getType(String oid) {
		PersonnelTypeEntity entity = this.personnelTypeMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			PersonnelTypeVO vo = new PersonnelTypeVO();
			vo.setOid(entity.getOid());
			vo.setTitle(entity.getTitle());
			vo.setType(this.getPersonnelType(entity.getpOid()));
			return vo;
		}
		return null;
	}

}
