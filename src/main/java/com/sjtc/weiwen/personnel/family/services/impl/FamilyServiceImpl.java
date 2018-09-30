package com.sjtc.weiwen.personnel.family.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.personnel.family.controllers.form.FamilyVO;
import com.sjtc.weiwen.personnel.family.dao.FamilyEntityMapper;
import com.sjtc.weiwen.personnel.family.dao.entity.FamilyEntity;
import com.sjtc.weiwen.personnel.family.services.IFamilyService;

@Service
public class FamilyServiceImpl implements IFamilyService {

	@Autowired
	private FamilyEntityMapper familyMapper;

	@Transactional
	@Override
	public BaseResult save(FamilyVO family) {
		if (StringUtil.isEmpty(family.getOid())) {
			FamilyEntity entity = new FamilyEntity();
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setPersonnelOid(family.getPersonnelOid());
			entity.setRelationship(family.getRelationship());
			entity.setRealName(family.getRealName());
			entity.setNation(family.getNation());
			entity.setIdentityCard(family.getIdentityCard());
			entity.setWorkUnit(family.getWorkUnit());
			entity.setDomicile(family.getDomicile());
			entity.setPresent(family.getPresent());
			entity.setTelephone(family.getTelephone());
			entity.setInsertTime(new Date());
			entity.setEditTime(new Date());
			this.familyMapper.insert(entity);
		} else {
			FamilyEntity entity =this.familyMapper.selectByPrimaryKey(family.getOid());
			if (entity == null) {
				return new BaseResult("-1", "family oid not find", null);
			}
			entity.setRelationship(family.getRelationship());
			entity.setRealName(family.getRealName());
			entity.setNation(family.getNation());
			entity.setIdentityCard(family.getIdentityCard());
			entity.setWorkUnit(family.getWorkUnit());
			entity.setDomicile(family.getDomicile());
			entity.setPresent(family.getPresent());
			entity.setTelephone(family.getTelephone());
			entity.setEditTime(new Date());
			this.familyMapper.updateByPrimaryKeySelective(entity);
		}
		return new BaseResult();
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.familyMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public List<FamilyVO> getFamilys(String personnelOid) {
		List<FamilyEntity> list = this.familyMapper.selectFamilysByPersonnelOid(personnelOid);
		if (list != null && list.size() > 0) {
			List<FamilyVO> vos = new ArrayList<>();
			for (FamilyEntity entity : list) {
				FamilyVO vo = new FamilyVO();
				vo.setOid(entity.getOid());
				vo.setPersonnelOid(entity.getPersonnelOid());
				vo.setRelationship(entity.getRelationship());
				vo.setRealName(entity.getRealName());
				vo.setNation(entity.getNation());
				vo.setIdentityCard(entity.getIdentityCard());
				vo.setWorkUnit(entity.getWorkUnit());
				vo.setDomicile(entity.getDomicile());
				vo.setPresent(entity.getPresent());
				vo.setTelephone(entity.getTelephone());
				vo.setInsertTime(entity.getInsertTime());
				vo.setEditTime(entity.getEditTime());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}
}
