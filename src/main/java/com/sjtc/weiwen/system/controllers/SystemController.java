package com.sjtc.weiwen.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.PermissionVO;
import com.sjtc.weiwen.system.services.ISystemService;

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
}
