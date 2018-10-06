package com.sjtc.weiwen.system.controllers;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.services.ISystemService;
import com.sjtc.weiwen.user.controllers.form.UserVO;

@RestController
@RequestMapping(value = "/system", produces = "application/json")
public class SystemController {
	
	@Autowired
	private ISystemService systemService;

	@RequestMapping(value="/permissionList", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PageInfo<PermissionVO>> permissionList(PermissionVO vo, @RequestParam Integer limit,
			@RequestParam Integer offset) {
		return new ResponseEntity<PageInfo<PermissionVO>>(this.systemService.getPermissions(vo, limit, offset), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/menus", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<List<PermissionVO>> menus() {
		Subject currentUser = SecurityUtils.getSubject();
		String userJson = JSON.toJSON(currentUser.getPrincipal()).toString();
		UserVO user = JSON.parseObject(userJson, UserVO.class);
		
		return new ResponseEntity<List<PermissionVO>>(this.systemService.getMenus(user.getOid(), null), HttpStatus.OK);
	}
}
