package com.sjtc.shiro.config;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.sjtc.weiwen.permission.controllers.form.SysPermissionVO;
import com.sjtc.weiwen.permission.services.ISysPermissionService;
import com.sjtc.weiwen.role.dao.entity.SysRoleEntity;
import com.sjtc.weiwen.role.services.ISysRoleService;
import com.sjtc.weiwen.user.controllers.form.UserVO;
import com.sjtc.weiwen.user.services.IUserService;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private ISysRoleService sysRoleService;
	@Autowired
	private ISysPermissionService sysPermissionService;
	@Autowired
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//      System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");        
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//		String string = JSON.toJSON(principals.getPrimaryPrincipal()).toString();
//		UserVO user = JSON.parseObject(string, UserVO.class);
//		try {
//			List<SysRoleEntity> roles = sysRoleService.selectRoleByUser(user);
//			for (SysRoleEntity role : roles) {
//				authorizationInfo.addRole(role.getRole());
//			}
//			List<SysPermissionVO> sysPermissions = sysPermissionService.selectPermByUser(user);
//			for (SysPermissionVO perm : sysPermissions) {
//				authorizationInfo.addStringPermission(perm.getPermission());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return authorizationInfo;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String account = (String) token.getPrincipal();
		// System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		UserVO userInfo = userService.getUserByAccount(account);
		// System.out.println("----->>userInfo="+userInfo);
		if (userInfo == null) {
			return null;
		}
		if (userInfo.getState() == 1) {
			// 账户冻结
			throw new LockedAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
				userInfo.getUserPwd(), // 密码
				ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

}
