package com.wechat.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {
	/**
	 * 核心处理方法
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request);
	/**
	 * 获取token
	 * @return
	 */
	public String getToken();
	/**
	 * 获取用户信息
	 * @return
	 */
	public Map<String,String> getUserInfo(String openid);
}
