package com.sjtc.weiwen.personnel.type.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.personnel.type.controllers.form.PersonnelTypeVO;

public interface IPersonnelTypeService {

	BaseResult save(PersonnelTypeVO type);

	PageInfo<PersonnelTypeVO> getTypes(Integer limit, Integer offset);

	List<PersonnelTypeVO> getTypeChildren(String pOid);

	PersonnelTypeVO getType(String oid);

}
