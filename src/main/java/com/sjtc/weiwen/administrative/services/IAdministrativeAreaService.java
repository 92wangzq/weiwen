package com.sjtc.weiwen.administrative.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;

public interface IAdministrativeAreaService {

	BaseResult save(AdministrativeAreaVO vo);

	BaseResult delete(String oid);

	PageInfo<AdministrativeAreaVO> getAreas(AdministrativeAreaVO vo, Integer limit, Integer offset);

	List<AdministrativeAreaVO> getChildrens(String oid);
	
	AdministrativeAreaVO getArea(String oid);

	List<AdministrativeAreaVO> getAreaParent(String pOid);

	List<String> getAreaOids(String oid);
	
	/**
	 * 根据父级oid获取所有子级oid
	 * @return
	 */
	List<String> getChildOids(String parentID);
}
