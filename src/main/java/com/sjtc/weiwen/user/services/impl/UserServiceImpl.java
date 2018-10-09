package com.sjtc.weiwen.user.services.impl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;
import com.sjtc.weiwen.system.services.ISystemService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.dao.UserEntityMapper;
import com.sjtc.weiwen.user.dao.entity.UserEntity;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserEntityMapper userMapper;
	@Autowired
	private IAdministrativeAreaService administrativeAreaService;
	@Autowired
	private ISystemService systemService;

	@Override
	public UserVO getAccount(String name, String pwd) {
		return null;
	}

	@Transactional
	@Override
	public BaseResult save(UserVO user) {
		BaseResult result = new BaseResult();
		UserEntity entity = new UserEntity();
		if (StringUtil.isEmpty(user.getOid())) {
			entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			entity.setRealName(user.getRealName());
			entity.setUserName(user.getUserName());
			entity.setUserPwd(new SimpleHash("md5", user.getUserPwd(), entity.getCredentialsSalt(), 1024).toString());
			entity.setUserTypeOid(user.getUserTypeOid());
			entity.setAreaOid(user.getAreaOid());
			entity.setInsertTime(new Date());
			entity.setUpdateTime(new Date());
			this.userMapper.insert(entity);
		} else {
			entity.setRealName(user.getRealName());
			entity.setUserName(user.getUserName());
			entity.setUserTypeOid(user.getUserTypeOid());
			entity.setAreaOid(user.getAreaOid());
			entity.setUpdateTime(new Date());
			this.userMapper.updateByPrimaryKeySelective(entity);
		}
		return result;
	}

	/**
	 * EDS的加密解密代码
	 */
	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };

	@SuppressWarnings("restriction")
	public static String encryptBasedDes(String data) {
		String encryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			// log.error("加密错误，错误信息：", e);
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * 解密
	 * @param cryptData
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 解密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// 把字符串进行解码，解码为为字节数组，并解密
			decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}

	@Transactional
	@Override
	public BaseResult delete(String oid) {
		this.userMapper.deleteByPrimaryKey(oid);
		return new BaseResult();
	}

	@Override
	public PageInfo<UserVO> getUsers(UserVO user, Integer limit, Integer offset) {
		Page<UserEntity> page = PageHelper.startPage(offset, limit, true);
		List<UserEntity> entitys = this.userMapper.selectUsersByFuzz(user);
		if (entitys != null && entitys.size() > 0) {
			List<UserVO> vos = new ArrayList<>();
			for (UserEntity entity : entitys) {
				UserVO vo = new UserVO();
				vo.setOid(entity.getOid());
				vo.setRealName(entity.getRealName());
				vo.setUserName(entity.getUserName());
				vo.setUserPwd(entity.getUserPwd());
				vo.setUserTypeOid(entity.getUserTypeOid());
				vo.setAreaOid(entity.getAreaOid());
				vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
				vo.setInsertTime(entity.getInsertTime());
				vo.setUpdateTime(entity.getUpdateTime());
				vos.add(vo);
			}
			PageInfo<UserVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}

	@Override
	public BaseResult getUser(String loginName, String loginPwd, HttpServletRequest req, HttpServletResponse res) {
		UserEntity user = this.userMapper.selectByUserName(loginName);
		if (user== null) {
			return new BaseResult("-1", "登录名或密码错误", null);
		}
		if (!loginPwd.equals(UserServiceImpl.decryptBasedDes(user.getUserPwd()))) {
			return new BaseResult("-1", "登录名或密码错误", null);
		}
		HttpSession session = req.getSession();
		session.setAttribute("user", user.getOid());
		Cookie cookie = new Cookie("user",user.getRealName());
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        res.addCookie(cookie);
        Cookie cookie2 = new Cookie("userType",user.getUserTypeOid());
        cookie2.setPath("/");
        cookie2.setMaxAge(3600);
        res.addCookie(cookie2);
		return new BaseResult();
	}

	@Override
	public UserVO getUser(String oid) {
		UserEntity entity = this.userMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			UserVO vo = new UserVO();
			vo.setOid(entity.getOid());
			vo.setRealName(entity.getRealName());
			vo.setUserName(entity.getUserName());
			vo.setUserPwd(entity.getUserPwd());
			vo.setUserTypeOid(entity.getUserTypeOid());
			vo.setAreaOid(entity.getAreaOid());
			vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
			vo.setInsertTime(entity.getInsertTime());
			vo.setUpdateTime(entity.getUpdateTime());
			return vo;
		}
		return null;
	}

	@Override
	public UserVO getUserByAccount(String account) {
		UserEntity entity = this.userMapper.selectByUserName(account);
		if (entity != null) {
			UserVO vo = new UserVO();
			vo.setOid(entity.getOid());
			vo.setRealName(entity.getRealName());
			vo.setUserName(entity.getUserName());
			vo.setUserPwd(entity.getUserPwd());
			vo.setUserTypeOid(entity.getUserTypeOid());
			vo.setAreaOid(entity.getAreaOid());
			vo.setArea(this.administrativeAreaService.getArea(entity.getAreaOid()));
			vo.setSalt(entity.getSalt());
			vo.setState(entity.getState());
			vo.setInsertTime(entity.getInsertTime());
			vo.setUpdateTime(entity.getUpdateTime());
			vo.setRoles(this.systemService.getRolesByUser(entity.getOid()));
			return vo;
		}
		return null;
	}

	@Override
	public List<UserVO> getUserByArea(String areaOid) {
		if (!StringUtils.isEmpty(areaOid)) {
			List<UserEntity> list = this.userMapper.selectUsersByArea(areaOid);
			if (!CollectionUtils.isEmpty(list)) {
				List<UserVO> vos = new ArrayList<>();
				for (UserEntity entity : list) {
					UserVO vo = new UserVO();
					vo.setOid(entity.getOid());
					vo.setRealName(entity.getRealName());
					vo.setUserName(entity.getUserName());
					vo.setUserPwd(entity.getUserPwd());
					vo.setSalt(entity.getSalt());
					vo.setState(entity.getState());
					vo.setInsertTime(entity.getInsertTime());
					vo.setUpdateTime(entity.getUpdateTime());
					vo.setRoles(this.systemService.getRolesByUser(entity.getOid()));
					vos.add(vo);
				}
				return vos;
			}
		} else {
			throw new IllegalArgumentException();
		}
		return null;
	}

}