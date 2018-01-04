package com.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import com.wechat.util.FeignUtil;

@SpringBootApplication
@EnableFeignClients(clients = {FeignUtil.class})
@EnableCaching
public class WeChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeChatApplication.class, args);
		System.out.println("====启动成功====");
	}

}
