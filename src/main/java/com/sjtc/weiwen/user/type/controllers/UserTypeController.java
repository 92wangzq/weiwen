package com.sjtc.weiwen.user.type.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.user.type.controllers.form.UserTypeVO;
import com.sjtc.weiwen.user.type.services.IUserTypeService;

@RestController
@RequestMapping(value="/userType", produces="application/json")
public class UserTypeController {

	@Autowired
	private IUserTypeService userTypeService;
	
	/**
	 * 新增/修改
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<BaseResult> save(UserTypeVO vo) {
		return new ResponseEntity<BaseResult>(this.userTypeService.save(vo), HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam String oid) {
		return new ResponseEntity<BaseResult>(this.userTypeService.delete(oid), HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchUserTypes", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<UserTypeVO>> searchUserTypes() {
		return new ResponseEntity<List<UserTypeVO>>(this.userTypeService.getUserTypes(), HttpStatus.OK);
	}
}
