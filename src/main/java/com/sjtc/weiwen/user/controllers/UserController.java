package com.sjtc.weiwen.user.controllers;

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
	
	/**
	 * 新增/修改用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> save(UserVO user) {
		return new ResponseEntity<BaseResult>(this.userService.save(user), HttpStatus.OK);
	}

	/**
	 * 删除用户
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> remove(@RequestParam String oid) {
		return new ResponseEntity<BaseResult>(this.userService.delete(oid), HttpStatus.OK);
	}

	/**
	 * 修改密码
	 * 
	 * @param oid
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequestMapping(value = "/editPwd", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> editPwd(@RequestParam String oid, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		return null;
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<PageInfo<UserVO>> searchUsers(UserVO user, @RequestParam(value="limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset) {
		return new ResponseEntity<PageInfo<UserVO>>(this.userService.getUsers(user, limit, offset), HttpStatus.OK);
	}
}
