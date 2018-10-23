package com.sjtc.weiwen.lawrelated.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;
import com.sjtc.weiwen.lawrelated.controllers.form.CaseTypeStatisticalVO;
import com.sjtc.weiwen.lawrelated.controllers.form.LawRelatedVO;
import com.sjtc.weiwen.lawrelated.dao.LawRelatedEntityMapper;
import com.sjtc.weiwen.lawrelated.dao.entity.LawRelatedEntity;
import com.sjtc.weiwen.lawrelated.services.ILawRelatedService;
import com.sjtc.weiwen.system.services.ISystemDictionaryService;
import com.sjtc.weiwen.user.controllers.form.UserVO;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

@Service
public class LawRelatedServiceImpl implements ILawRelatedService {

	@Autowired
	private LawRelatedEntityMapper lawRelatedMapper;
	@Autowired
	private IAdministrativeAreaService administrativeAreaService;
	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	@Transactional
	@Override
	public BaseResult save(LawRelatedVO vo) {
		BaseResult result = new BaseResult();
		String userStr = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
		UserVO user = JSON.parseObject(userStr, UserVO.class);
		LawRelatedEntity entity = new LawRelatedEntity();
		if (StringUtils.isEmpty(vo.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replace("-", ""));
			entity.setRealName(vo.getRealName());
			entity.setSex(vo.getSex());
			entity.setNation(vo.getNation());
			entity.setIdentityCard(vo.getIdentityCard());
			entity.setBirthday(vo.getBirthday());
			entity.setPoliticalStatus(vo.getPoliticalStatus());
			entity.setWorkUnit(vo.getWorkUnit());
			entity.setPresent(vo.getPresent());
			entity.setDomicile(vo.getDomicile());
			entity.setTelephone(vo.getTelephone());
			entity.setAppeals(vo.getAppeals());
			entity.setOriginalRealName(vo.getOriginalRealName());
			entity.setOriginalSex(vo.getOriginalSex());
			entity.setOriginalNation(vo.getOriginalNation());
			entity.setOriginalIdentityCard(vo.getOriginalIdentityCard());
			entity.setOriginalBirthday(vo.getOriginalBirthday());
			entity.setOriginalPoliticalStatus(vo.getOriginalPoliticalStatus());
			entity.setOriginalWorkUnit(vo.getOriginalWorkUnit());
			entity.setOriginalPresent(vo.getOriginalPresent());
			entity.setOriginalDomicile(vo.getOriginalDomicile());
			entity.setOriginalTelephone(vo.getOriginalTelephone());
			entity.setOriginalAppeals(vo.getOriginalAppeals());
			entity.setAreaOid(user.getArea().getOid());
			entity.setCaseType(vo.getCaseType());
			entity.setLetterVisitOrder(vo.getLetterVisitOrder());
			entity.setPowerAffairsUnit(vo.getPowerAffairsUnit().getOid());
			entity.setAttendTo(vo.getAttendTo());
			entity.setInsertTime(new Date());
			entity.setUpdateTime(new Date());
			this.lawRelatedMapper.insertSelective(entity);
		} else {
			entity = this.lawRelatedMapper.selectByPrimaryKey(vo.getOid());
			if (entity == null) {
				throw new IllegalArgumentException();
			}
			entity.setRealName(vo.getRealName());
			entity.setSex(vo.getSex());
			entity.setNation(vo.getNation());
			entity.setIdentityCard(vo.getIdentityCard());
			entity.setBirthday(vo.getBirthday());
			entity.setPoliticalStatus(vo.getPoliticalStatus());
			entity.setWorkUnit(vo.getWorkUnit());
			entity.setPresent(vo.getPresent());
			entity.setDomicile(vo.getDomicile());
			entity.setTelephone(vo.getTelephone());
			entity.setAppeals(vo.getAppeals());
			entity.setOriginalRealName(vo.getOriginalRealName());
			entity.setOriginalSex(vo.getOriginalSex());
			entity.setOriginalNation(vo.getOriginalNation());
			entity.setOriginalIdentityCard(vo.getOriginalIdentityCard());
			entity.setOriginalBirthday(vo.getOriginalBirthday());
			entity.setOriginalPoliticalStatus(vo.getOriginalPoliticalStatus());
			entity.setOriginalWorkUnit(vo.getOriginalWorkUnit());
			entity.setOriginalPresent(vo.getOriginalPresent());
			entity.setOriginalDomicile(vo.getOriginalDomicile());
			entity.setOriginalTelephone(vo.getOriginalTelephone());
			entity.setOriginalAppeals(vo.getOriginalAppeals());
			entity.setCaseType(vo.getCaseType());
			entity.setLetterVisitOrder(vo.getLetterVisitOrder());
			entity.setPowerAffairsUnit(vo.getPowerAffairsUnit().getOid());
			entity.setAttendTo(vo.getAttendTo());
			entity.setUpdateTime(new Date());
			this.lawRelatedMapper.updateByPrimaryKeySelective(entity);
		}
		return result;
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.lawRelatedMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public PageInfo<LawRelatedVO> getlawRelateds(LawRelatedVO lawRelated, Integer limit, Integer offset) {
		LawRelatedEntity params = new LawRelatedEntity();
		List<String> areaOids = new ArrayList<>();
		if (lawRelated.getArea() != null && !StringUtils.isEmpty(lawRelated.getArea().getOid())) {
			areaOids = this.administrativeAreaService.getChildOids(lawRelated.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		} else {
			String userStr = JSON.toJSON(SecurityUtils.getSubject().getPrincipal()).toString();
			UserVO user = JSON.parseObject(userStr, UserVO.class);
			areaOids = this.administrativeAreaService.getChildOids(user.getArea().getOid());
			if (!CollectionUtils.isEmpty(areaOids)) {
				params.setAreaOids(areaOids);
			}
		}
		params.setRealName(lawRelated.getRealName());
		params.setNation(lawRelated.getNation());
		params.setIdentityCard(lawRelated.getIdentityCard());
		params.setBirthday(lawRelated.getBirthday());
		params.setOriginalRealName(lawRelated.getOriginalRealName());
		params.setOriginalNation(lawRelated.getOriginalNation());
		params.setOriginalIdentityCard(lawRelated.getOriginalIdentityCard());
		params.setOriginalBirthday(lawRelated.getOriginalBirthday());
		Page<LawRelatedEntity> page = PageHelper.startPage(offset, limit, true);
		List<LawRelatedEntity> list = this.lawRelatedMapper.selectLawRelatedsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<LawRelatedVO> vos = new ArrayList<>();
			for (LawRelatedEntity entity : list) {
				LawRelatedVO vo = new LawRelatedVO();
				vo.setOid(entity.getOid());
				vo.setRealName(entity.getRealName());
				vo.setSex(entity.getSex());
				vo.setNation(entity.getNation());
				vo.setIdentityCard(entity.getIdentityCard());
				vo.setBirthday(entity.getBirthday());
				vo.setPoliticalStatus(entity.getPoliticalStatus());
				vo.setWorkUnit(entity.getWorkUnit());
				vo.setPresent(entity.getPresent());
				vo.setDomicile(entity.getDomicile());
				vo.setTelephone(entity.getTelephone());
				vo.setAppeals(entity.getAppeals());
				vo.setOriginalRealName(entity.getOriginalRealName());
				vo.setOriginalSex(entity.getOriginalSex());
				vo.setOriginalNation(entity.getOriginalNation());
				vo.setOriginalIdentityCard(entity.getOriginalIdentityCard());
				vo.setOriginalBirthday(entity.getOriginalBirthday());
				vo.setOriginalPoliticalStatus(entity.getOriginalPoliticalStatus());
				vo.setOriginalWorkUnit(entity.getOriginalWorkUnit());
				vo.setOriginalPresent(entity.getOriginalPresent());
				vo.setOriginalDomicile(entity.getOriginalDomicile());
				vo.setOriginalTelephone(entity.getOriginalTelephone());
				vo.setOriginalAppeals(entity.getOriginalAppeals());
				vo.setCaseType(entity.getCaseType());
				vo.setLetterVisitOrder(entity.getLetterVisitOrder());
				vo.setPowerAffairsUnit(this.systemDictionaryService.getDictionaryData(entity.getPowerAffairsUnit()));
				vo.setAttendTo(entity.getAttendTo());
				vo.setInsertTime(entity.getInsertTime());
				vo.setUpdateTime(entity.getUpdateTime());
				vos.add(vo);
			}
			PageInfo<LawRelatedVO> pageInfo = new PageInfo<>();
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
	public LawRelatedVO getLawRelated(String oid) {
		LawRelatedEntity entity = this.lawRelatedMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			LawRelatedVO vo = new LawRelatedVO();
			vo.setOid(entity.getOid());
			vo.setRealName(entity.getRealName());
			vo.setSex(entity.getSex());
			vo.setNation(entity.getNation());
			vo.setIdentityCard(entity.getIdentityCard());
			vo.setBirthday(entity.getBirthday());
			vo.setPoliticalStatus(entity.getPoliticalStatus());
			vo.setWorkUnit(entity.getWorkUnit());
			vo.setPresent(entity.getPresent());
			vo.setDomicile(entity.getDomicile());
			vo.setTelephone(entity.getTelephone());
			vo.setAppeals(entity.getAppeals());
			vo.setOriginalRealName(entity.getOriginalRealName());
			vo.setOriginalSex(entity.getOriginalSex());
			vo.setOriginalNation(entity.getOriginalNation());
			vo.setOriginalIdentityCard(entity.getOriginalIdentityCard());
			vo.setOriginalBirthday(entity.getOriginalBirthday());
			vo.setOriginalPoliticalStatus(entity.getOriginalPoliticalStatus());
			vo.setOriginalWorkUnit(entity.getOriginalWorkUnit());
			vo.setOriginalPresent(entity.getOriginalPresent());
			vo.setOriginalDomicile(entity.getOriginalDomicile());
			vo.setOriginalTelephone(entity.getOriginalTelephone());
			vo.setOriginalAppeals(entity.getOriginalAppeals());
			vo.setCaseType(entity.getCaseType());
			vo.setLetterVisitOrder(entity.getLetterVisitOrder());
			vo.setPowerAffairsUnit(this.systemDictionaryService.getDictionaryData(entity.getPowerAffairsUnit()));
			vo.setAttendTo(entity.getAttendTo());
			vo.setInsertTime(entity.getInsertTime());
			vo.setUpdateTime(entity.getUpdateTime());
			return vo;
		}
		return null;
	}

	@Override
	public void downExcel(LawRelatedVO lawRelated, HttpServletResponse response) {
		LawRelatedEntity params = new LawRelatedEntity();
		params.setRealName(lawRelated.getRealName());
		params.setNation(lawRelated.getNation());
		params.setIdentityCard(lawRelated.getIdentityCard());
		params.setBirthday(lawRelated.getBirthday());
		params.setOriginalRealName(lawRelated.getOriginalRealName());
		params.setOriginalNation(lawRelated.getOriginalNation());
		params.setOriginalIdentityCard(lawRelated.getOriginalIdentityCard());
		params.setOriginalBirthday(lawRelated.getOriginalBirthday());
		List<LawRelatedEntity> list = this.lawRelatedMapper.selectLawRelatedsByFuzz(params);
		if (!CollectionUtils.isEmpty(list)) {
			List<LawRelatedVO> vos = new ArrayList<>();
			for (LawRelatedEntity entity : list) {
				LawRelatedVO vo = new LawRelatedVO();
				vo.setOid(entity.getOid());
				vo.setRealName(entity.getRealName());
				vo.setSex(entity.getSex());
				vo.setNation(entity.getNation());
				vo.setIdentityCard(entity.getIdentityCard());
				vo.setBirthday(entity.getBirthday());
				vo.setPoliticalStatus(entity.getPoliticalStatus());
				vo.setWorkUnit(entity.getWorkUnit());
				vo.setPresent(entity.getPresent());
				vo.setDomicile(entity.getDomicile());
				vo.setTelephone(entity.getTelephone());
				vo.setAppeals(entity.getAppeals());
				vo.setOriginalRealName(entity.getOriginalRealName());
				vo.setOriginalSex(entity.getOriginalSex());
				vo.setOriginalNation(entity.getOriginalNation());
				vo.setOriginalIdentityCard(entity.getOriginalIdentityCard());
				vo.setOriginalBirthday(entity.getOriginalBirthday());
				vo.setOriginalPoliticalStatus(entity.getOriginalPoliticalStatus());
				vo.setOriginalWorkUnit(entity.getOriginalWorkUnit());
				vo.setOriginalPresent(entity.getOriginalPresent());
				vo.setOriginalDomicile(entity.getOriginalDomicile());
				vo.setOriginalTelephone(entity.getOriginalTelephone());
				vo.setOriginalAppeals(entity.getOriginalAppeals());
				vo.setCaseType(entity.getCaseType());
				vo.setLetterVisitOrder(entity.getLetterVisitOrder());
				vo.setPowerAffairsUnit(this.systemDictionaryService.getDictionaryData(entity.getPowerAffairsUnit()));
				vo.setAttendTo(entity.getAttendTo());
				vo.setInsertTime(entity.getInsertTime());
				vo.setUpdateTime(entity.getUpdateTime());
				vos.add(vo);
			}
			ExportParams excelParams = new ExportParams("涉法涉诉信息表","人员信息");
	        Workbook workbook = ExcelExportUtil.exportExcel(excelParams,LawRelatedVO.class,vos);
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
	public List<CaseTypeStatisticalVO> caseTypeStatistical() {
		List<CaseTypeStatisticalVO> vos = new ArrayList<>();
		Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
		for (int i = 0; i < 12; i++) {
			CaseTypeStatisticalVO vo = new CaseTypeStatisticalVO();
			vo.setMonth(year+"-"+(i+1 < 10 ? "0"+(i+1) : (i+1)));
			vo.setMs(this.lawRelatedMapper.selectCountByCaseType("MS", Integer.parseInt(year), i+1));
			vo.setXz(this.lawRelatedMapper.selectCountByCaseType("XZ", Integer.parseInt(year), i+1));
			vo.setXs(this.lawRelatedMapper.selectCountByCaseType("XS", Integer.parseInt(year), i+1));
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<CaseTypeStatisticalVO> getCaseTypeStatisticalDonut() {
		List<LawRelatedEntity> list = this.lawRelatedMapper.selectPercentageGroupByCaseType();
		if (!CollectionUtils.isEmpty(list)) {
			List<CaseTypeStatisticalVO> vos = new ArrayList<>();
			for (LawRelatedEntity entity : list) {
				CaseTypeStatisticalVO vo = new CaseTypeStatisticalVO();
				if (entity.getLabel().equals("XS")) {
					vo.setLabel("刑事案件");
				} else if (entity.getLabel().equals("MS")) {
					vo.setLabel("民事案件");
				} else if (entity.getLabel().equals("XZ")) {
					vo.setLabel("行政案件");
				}
				vo.setValue(entity.getVal());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}
}
