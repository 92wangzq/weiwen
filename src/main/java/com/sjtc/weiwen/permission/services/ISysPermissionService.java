package com.sjtc.weiwen.permission.services;

import java.util.List;

import com.sjtc.weiwen.permission.controllers.form.SysPermissionVO;
import com.sjtc.weiwen.user.controllers.form.UserVO;

public interface ISysPermissionService {

	List<SysPermissionVO> selectPermByUser(UserVO userInfo);

}
