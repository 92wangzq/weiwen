package com.sjtc.shiro.config;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

/**
 * 
 * @author wangzq
 * 2018-09-28
 * 自定义sessionId获取
 */
public class MySessionManager extends DefaultWebSessionManager {

	private static final String AUTHORIZATION = "Authorization";
	
	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
	
	public MySessionManager() {
		super();
	}

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		String sessionID = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
		//如果请求头中有AUTHORIZATION，则其值为sessionID
		if (!StringUtils.isEmpty(sessionID)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, REFERENCED_SESSION_ID_SOURCE);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionID);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sessionID;
		} else {
			//否则按默认规则从cookie取sessionID
			return super.getSessionId(request, response);
		}
	}
}
