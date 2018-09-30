package com.sjtc.shiro.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import com.sjtc.weiwen.user.controllers.form.UserVO;

@Configuration
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		//获取已登录的用户信息
        UserVO userVO = (UserVO) subject.getPrincipal();
        //获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        session.setAttribute("user", userVO);
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	

}
