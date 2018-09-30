package com.sjtc.weiwen.user.type.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.user.type.controllers.form.UserTypeVO;

public interface IUserTypeService {

	BaseResult save(UserTypeVO vo);

	BaseResult delete(String oid);

	List<UserTypeVO> getUserTypes();

}
