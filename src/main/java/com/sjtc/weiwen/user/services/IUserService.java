 package com.sjtc.weiwen.user.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.user.controllers.form.UserVO;

public interface IUserService {

	public UserVO getAccount(String name, String pwd);

	public BaseResult save(UserVO user);

	public BaseResult delete(String oid);

	public PageInfo<UserVO> getUsers(UserVO user, Integer limit, Integer offset);

	public BaseResult getUser(String loginName, String loginPwd, HttpServletRequest req, HttpServletResponse res);

	public UserVO getUser(String oid);

	public UserVO getUserByAccount(String account);
}
