package com.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HtmlController {
	/**
	 * 跳转页面
	 * @param mv
	 * @param html
	 * @return
	 */
	@GetMapping("/page/{html}")
	public Object toLoveTime(ModelAndView mv,@PathVariable String html){
		mv.setViewName(html);
		return mv;
	}
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@GetMapping("set/{val}")
	public Object setRedis(@PathVariable String val) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("name", val);
		return "name";
	}
}
