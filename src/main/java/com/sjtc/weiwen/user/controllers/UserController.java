package com.sjtc.weiwen.user.controllers;

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
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	@RequestMapping(name = "新增/修改用户信息", value = "/save", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> save(UserVO user) {
		return new ResponseEntity<BaseResult>(this.userService.save(user), HttpStatus.OK);
	}

	@RequestMapping(name = "删除用户信息", value = "/remove", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam String oid) {
		return new ResponseEntity<BaseResult>(this.userService.delete(oid), HttpStatus.OK);
	}

	@RequestMapping(name = "修改用户状态", value = "/editState", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<BaseResult> editState(@RequestParam("oid")String oid, @RequestParam("state")String state) {
		return new ResponseEntity<BaseResult>(this.userService.updateState(oid, state), HttpStatus.OK);
	}

	@RequestMapping(name = "用户信息列表", value = "/searchUsers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<PageInfo<UserVO>> searchUsers(UserVO user,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<UserVO>>(this.userService.getUsers(user, limit, offset), HttpStatus.OK);
	}

	@RequestMapping(name = "用户信息", value = "/viewUser", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<UserVO> viewUser(@RequestParam(value = "oid") String oid) {
		return new ResponseEntity<UserVO>(this.userService.getUser(oid), HttpStatus.OK);
	}
	
	@RequestMapping(name = "获取当前行政区域用户", value = "/searchAreaUsers", method = {RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<List<UserVO>> searchAreaUsers() {
		return new ResponseEntity<List<UserVO>>(this.userService.getAreaUsers(), HttpStatus.OK);
	}
}
