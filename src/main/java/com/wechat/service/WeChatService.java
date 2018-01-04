package com.wechat.service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {
	public String processRequest(HttpServletRequest request);
}
