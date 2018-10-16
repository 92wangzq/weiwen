package com.sjtc.weiwen.personnel.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;
import com.sjtc.weiwen.personnel.controllers.form.PersonnelVO;
import com.sjtc.weiwen.personnel.dao.PersonnelEntityMapper;
import com.sjtc.weiwen.personnel.dao.entity.PersonnelEntity;
import com.sjtc.weiwen.personnel.family.controllers.form.FamilyVO;
import com.sjtc.weiwen.personnel.family.services.IFamilyService;
import com.sjtc.weiwen.personnel.services.IPersonnelService;
import com.sjtc.weiwen.personnel.type.services.IPersonnelTypeService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

@Service
public class PersonnelServiceImpl implements IPersonnelService {

	@Autowired
	private PersonnelEntityMapper personnelMapper;
	@Autowired
	private IPersonnelTypeService personnelTypeService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IFamilyService familyService;
	@Autowired
	private IAdministrativeAreaService administrativeAreaService;
	
	@Transactional
	@Override
	public BaseResult save(PersonnelVO personnel, HttpServletRequest req) {
		PersonnelEntity entity = new PersonnelEntity();
		if (StringUtil.isEmpty(personnel.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setUserOid(req.getSession().getAttribute("user").toString());
			entity.setAreaOid(personnel.getArea().getOid());
			entity.setPersonnelTypeOid(personnel.getType().getOid());
			entity.setRealName(personnel.getRealName());
			entity.setSex(personnel.getSex());
			entity.setNation(personnel.getNation());
			entity.setIdentityCard(personnel.getIdentityCard());
			entity.setBirthday(personnel.getBirthday());
			entity.setWorkUnit(personnel.getWorkUnit());
			entity.setPoliticalStatus(personnel.getPoliticalStatus());
			entity.setDomicile(personnel.getDomicile());
			entity.setPresent(personnel.getPresent());
			entity.setTelephone(personnel.getTelephone());
			entity.setRealState(personnel.getRealState());
			entity.setMore(personnel.getMore());
			entity.setRiskRating(personnel.getRiskRating());
			entity.setEventAppeals(personnel.getEventAppeals());
			entity.setExperienceResults(personnel.getExperienceResults());
			entity.setInsertTime(new Date());
			entity.setEditTime(new Date());
			this.personnelMapper.insert(entity);
			if (!StringUtil.isEmpty(personnel.getFamilyJson())) {
				JSONArray array = JSONArray.parseArray(personnel.getFamilyJson());
				for (int i = 0; i < array.size(); i++) {
					JSONObject json = array.getJSONObject(i);
					FamilyVO vo = json.toJavaObject(FamilyVO.class);
					vo.setPersonnelOid(entity.getOid());
					this.familyService.save(vo);
				}
			}
		} else {
			entity.setOid(personnel.getOid());
			entity.setAreaOid(personnel.getArea().getOid());
			entity.setPersonnelTypeOid(personnel.getType().getOid());
			entity.setRealName(personnel.getRealName());
			entity.setSex(personnel.getSex());
			entity.setNation(personnel.getNation());
			entity.setIdentityCard(personnel.getIdentityCard());
			entity.setBirthday(personnel.getBirthday());
			entity.setWorkUnit(personnel.getWorkUnit());
			entity.setPoliticalStatus(personnel.getPoliticalStatus());
			entity.setDomicile(personnel.getDomicile());
			entity.setPresent(personnel.getPresent());
			entity.setTelephone(personnel.getTelephone());
			entity.setRealState(personnel.getRealState());
			entity.setMore(personnel.getMore());
			entity.setRiskRating(personnel.getRiskRating());
			entity.setEventAppeals(personnel.getEventAppeals());
			entity.setExperienceResults(personnel.getExperienceResults());
			entity.setEditTime(new Date());
			this.personnelMapper.updateByPrimaryKeySelective(entity);
			if (!StringUtil.isEmpty(personnel.getFamilyJson())) {
				JSONArray array = JSONArray.parseArray(personnel.getFamilyJson());
				for (int i = 0; i < array.size(); i++) {
					JSONObject json = array.getJSONObject(i);
					FamilyVO vo = json.toJavaObject(FamilyVO.class);
					vo.setPersonnelOid(entity.getOid());
					this.familyService.save(vo);
				}
			}
		}
		return new BaseResult();
	}
	
	String areaOids;

	@Override
	public PageInfo<PersonnelVO> getPersonnels(PersonnelVO personnel, Integer limit, Integer offset, HttpServletRequest req) {
		UserVO user = this.userService.getUser(req.getSession().getAttribute("user").toString());
		if (user == null) {
			throw new IllegalArgumentException();
		}
		PersonnelEntity params = new PersonnelEntity();
		List<String> areaOids = new ArrayList<>();
		if (personnel.getArea() != null) {
			areaOids = this.administrativeAreaService.getChildOids(personnel.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		} else {
			areaOids = this.administrativeAreaService.getChildOids(user.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		}
		params.setPersonnelTypeOid(personnel.getType() != null ? personnel.getType().getOid() : null);
		params.setRealName(personnel.getRealName());
		params.setSex(personnel.getSex());
		params.setNation(personnel.getNation());
		params.setIdentityCard(personnel.getIdentityCard());
		params.setBirthday(personnel.getBirthday());
		params.setWorkUnit(personnel.getWorkUnit());
		params.setPoliticalStatus(personnel.getPoliticalStatus());
		params.setDomicile(personnel.getDomicile());
		params.setPresent(personnel.getPresent());
		params.setTelephone(personnel.getTelephone());
		params.setRealState(personnel.getRealState());
		params.setMore(personnel.getMore());
		params.setRiskRating(personnel.getRiskRating());
		params.setEventAppeals(personnel.getEventAppeals());
		params.setExperienceResults(personnel.getExperienceResults());
		Page<PersonnelEntity> page = PageHelper.startPage(offset, limit, true);
		List<PersonnelEntity> list = this.personnelMapper.selectPersonnelsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<PersonnelVO> vos = new ArrayList<>();
			for (PersonnelEntity entity : list) {
				PersonnelVO vo = new PersonnelVO();
				vo.setOid(entity.getOid());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
				vo.setType(this.personnelTypeService.getType(entity.getPersonnelTypeOid()));
				vo.setRealName(entity.getRealName());
				vo.setSex(entity.getSex());
				vo.setNation(entity.getNation());
				vo.setIdentityCard(entity.getIdentityCard());
				vo.setBirthday(entity.getBirthday());
				vo.setWorkUnit(entity.getWorkUnit());
				vo.setPoliticalStatus(entity.getPoliticalStatus());
				vo.setDomicile(entity.getDomicile());
				vo.setPresent(entity.getPresent());
				vo.setTelephone(entity.getTelephone());
				vo.setRealState(entity.getRealState());
				vo.setMore(entity.getMore());
				vo.setRiskRating(entity.getRiskRating());
				vo.setEventAppeals(entity.getEventAppeals());
				vo.setExperienceResults(entity.getExperienceResults());
				vo.setInsertTime(new Date());
				vo.setEditTime(new Date());
				vos.add(vo);
			}
			PageInfo<PersonnelVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.personnelMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public void downExcel(PersonnelVO personnel, HttpServletResponse response) {
		PersonnelEntity params = new PersonnelEntity();
		params.setOid(personnel.getOid());
		params.setAreaOid(personnel.getArea() != null ? personnel.getArea().getOid() : null);
		params.setPersonnelTypeOid(personnel.getType() != null ? personnel.getType().getOid() : null);
		params.setRealName(personnel.getRealName());
		params.setSex(personnel.getSex());
		params.setNation(personnel.getNation());
		params.setIdentityCard(personnel.getIdentityCard());
		params.setBirthday(personnel.getBirthday());
		params.setWorkUnit(personnel.getWorkUnit());
		params.setPoliticalStatus(personnel.getPoliticalStatus());
		params.setDomicile(personnel.getDomicile());
		params.setPresent(personnel.getPresent());
		params.setTelephone(personnel.getTelephone());
		params.setRealState(personnel.getRealState());
		params.setMore(personnel.getMore());
		params.setRiskRating(personnel.getRiskRating());
		params.setEventAppeals(personnel.getEventAppeals());
		params.setExperienceResults(personnel.getExperienceResults());
		List<PersonnelEntity> list = this.personnelMapper.selectPersonnelsByFuzz(params);
		if (list != null && list.size() > 0) {
			List<PersonnelVO> vos = new ArrayList<>();
			for (PersonnelEntity entity : list) {
				PersonnelVO vo = new PersonnelVO();
				vo.setOid(entity.getOid());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
				vo.setType(this.personnelTypeService.getType(entity.getPersonnelTypeOid()));
				vo.setRealName(entity.getRealName());
				vo.setSex(entity.getSex());
				vo.setNation(entity.getNation());
				vo.setIdentityCard(entity.getIdentityCard());
				vo.setBirthday(entity.getBirthday());
				vo.setWorkUnit(entity.getWorkUnit());
				vo.setPoliticalStatus(entity.getPoliticalStatus());
				vo.setDomicile(entity.getDomicile());
				vo.setPresent(entity.getPresent());
				vo.setTelephone(entity.getTelephone());
				vo.setRealState(entity.getRealState());
				vo.setMore(entity.getMore());
				vo.setRiskRating(entity.getRiskRating());
				vo.setEventAppeals(entity.getEventAppeals());
				vo.setExperienceResults(entity.getExperienceResults());
				vo.setInsertTime(new Date());
				vo.setEditTime(new Date());
				vos.add(vo);
			}
			ExportParams excelParams = new ExportParams("人员信息表","人员信息");
	        Workbook workbook = ExcelExportUtil.exportExcel(excelParams,PersonnelVO.class,vos);
	        response.setHeader("content-Type","application/vnd.ms-excel");
	        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".xls");
	        response.setCharacterEncoding("UTF-8");
	        try {
	            workbook.write(response.getOutputStream());
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}

	@Override
	public PersonnelVO getPersonnel(String oid) {
		PersonnelEntity entity = this.personnelMapper.selectByPrimaryKey(oid);
		if (entity == null) {
			throw new IllegalArgumentException();
		}
		PersonnelVO vo = new PersonnelVO();
		vo.setOid(entity.getOid());
		vo.setUser(this.userService.getUser(entity.getUserOid()));
		vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
		vo.setType(this.personnelTypeService.getType(entity.getPersonnelTypeOid()));
		vo.setRealName(entity.getRealName());
		vo.setSex(entity.getSex());
		vo.setNation(entity.getNation());
		vo.setIdentityCard(entity.getIdentityCard());
		vo.setBirthday(entity.getBirthday());
		vo.setWorkUnit(entity.getWorkUnit());
		vo.setPoliticalStatus(entity.getPoliticalStatus());
		vo.setDomicile(entity.getDomicile());
		vo.setPresent(entity.getPresent());
		vo.setTelephone(entity.getTelephone());
		vo.setRealState(entity.getRealState());
		vo.setMore(entity.getMore());
		vo.setRiskRating(entity.getRiskRating());
		vo.setEventAppeals(entity.getEventAppeals());
		vo.setExperienceResults(entity.getExperienceResults());
		vo.setInsertTime(new Date());
		vo.setEditTime(new Date());
		return vo;
	}
}
