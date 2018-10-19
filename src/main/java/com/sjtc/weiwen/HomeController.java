package com.sjtc.weiwen;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.user.controllers.form.UserVO;

@RestController
@RequestMapping(value="/", produces="application/json")
public class HomeController {

	/**
	 * 登录
	 * 
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> login(@RequestParam String loginName, @RequestParam String loginPwd,
			HttpServletRequest req, HttpServletResponse res) {
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);
		Subject currentUser = SecurityUtils.getSubject();
		BaseResult baseResult = new BaseResult();
		try {
			currentUser.login(token);
			String string = JSON.toJSON(currentUser.getPrincipal()).toString();
			System.out.println(string);
			UserVO user = JSON.parseObject(string, UserVO.class);
			System.out.println(currentUser.getSession().getId());
			currentUser.getSession().setAttribute("user", user.getOid());
			Cookie userName = new Cookie("userName", user.getRealName());
			userName.setMaxAge(900);   //存活期为一个月 30*24*60*60
			userName.setPath("/");
            res.addCookie(userName);
            Cookie userOid = new Cookie("userOid", user.getOid());
            userOid.setMaxAge(900);
            userOid.setPath("/");
            res.addCookie(userOid);
            
			baseResult.setCode("00000");
			baseResult.setMessage("登录成功");
			baseResult.setToken(currentUser.getSession().getId());
			System.out.println(baseResult.getToken());
		} catch (IncorrectCredentialsException e) {
			baseResult.setCode("-10000");
			baseResult.setMessage("密码错误");
		} catch (LockedAccountException e) {
			baseResult.setCode("-10001");
			baseResult.setMessage("账户被冻结");
		} catch (AuthenticationException e) {
			baseResult.setCode("-10002");
			baseResult.setMessage("账户不存在");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<BaseResult>(baseResult, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<BaseResult> logout(HttpServletRequest req, HttpServletResponse res) {
		if (SecurityUtils.getSubject().getPrincipal() != null) {
			SecurityUtils.getSubject().logout();
		}
		Cookie userName = new Cookie("userName", null);
		userName.setMaxAge(0);
		userName.setPath("/");
		res.addCookie(userName);
		Cookie userOid = new Cookie("userOid", null);
		userOid.setMaxAge(0);
		userOid.setPath("/");
		res.addCookie(userOid);
		return new ResponseEntity<BaseResult>(new BaseResult(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<BaseResult> unauth() {
		return new ResponseEntity<BaseResult>(new BaseResult("1000000", "未登录", null), HttpStatus.OK);
	}
}
