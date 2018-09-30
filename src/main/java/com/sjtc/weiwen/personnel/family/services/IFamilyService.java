package com.sjtc.weiwen.personnel.family.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.personnel.family.controllers.form.FamilyVO;

public interface IFamilyService {

	BaseResult save(FamilyVO family);

	BaseResult delete(String oid);

	List<FamilyVO> getFamilys(String personnelOid);

}
