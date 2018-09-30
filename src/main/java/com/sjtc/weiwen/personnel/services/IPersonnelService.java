package com.sjtc.weiwen.personnel.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.personnel.controllers.form.PersonnelVO;

public interface IPersonnelService {

	BaseResult save(PersonnelVO personnel, HttpServletRequest req);

	PageInfo<PersonnelVO> getPersonnels(PersonnelVO personnel, Integer limit, Integer offset, HttpServletRequest req);

	BaseResult delete(String oid);

	void downExcel(PersonnelVO personnel, HttpServletResponse response);

	PersonnelVO getPersonnel(String oid);
	
}
