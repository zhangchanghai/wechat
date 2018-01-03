package com.wechat.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wechat.service.CoreService;
import com.wechat.util.WeChatUtil;

@Controller
public class WeChatController {
	/**
	 * 处理微信服务器发来的get请求，进行签名的验证
	 * @param signature 微信端发来的签名
	 * @param timestamp 微信端发来的时间戳
	 * @param nonce 微信端发来的随机字符串
	 * @param echostr 微信端发来的验证字符串
	 */
	@GetMapping(value = "wechat")
	public void validate(PrintWriter   print,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr) {
		try {
			if (WeChatUtil.checkSignature(signature, timestamp, nonce)) {
				print.write(echostr);
				print.flush();
				print.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 此处是处理微信服务器的消息转发的
	 * @param req
	 * @param resp
	 * @throws JAXBException
	 */

	@PostMapping(value = "wechat")
	public void processMsg(HttpServletRequest request, HttpServletResponse response)
			throws JAXBException {
		response.setCharacterEncoding("utf-8");
        
		try (PrintWriter print = response.getWriter();){			
			 // 接收消息并返回消息
	        // 调用核心服务类接收处理请求
	        String respXml = CoreService.processRequest(request);
	        System.out.println(respXml);
	        print.print(respXml);
	        print.flush();
	        print.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	
	
	@RequestMapping(value = "gettoken")
	public void getToken() {
		
	}
	
}
