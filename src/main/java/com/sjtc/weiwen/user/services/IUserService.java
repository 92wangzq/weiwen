package com.sjtc.weiwen.user.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.user.controllers.form.UserVO;

public interface IUserService {

	/**
	 * 新增或修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public BaseResult save(UserVO user);

	/**
	 * 删除用户信息
	 * 
	 * @param oid
	 * @return
	 */
	public BaseResult delete(String oid);

	/**
	 * 查询用户列表
	 * 
	 * @param user
	 * @param limit
	 * @param offset
	 * @return
	 */
	public PageInfo<UserVO> getUsers(UserVO user, Integer limit, Integer offset);

	/**
	 * 查询用户信息
	 * 
	 * @param oid
	 * @return
	 */
	public UserVO getUser(String oid);

	/**
	 * 根据帐号查询用户信息
	 * 
	 * @param account
	 * @return
	 */
	public UserVO getUserByAccount(String account);

	/**
	 * 根据行政区域查询用户
	 * 
	 * @param areaOid
	 * @return
	 */
	public List<UserVO> getUserByArea(String areaOid);

	/**
	 * 修改用户状态
	 * 
	 * @param vo
	 * @return
	 */
	public BaseResult updateState(String oid, String state);

	public List<UserVO> getAreaUsers();
}
