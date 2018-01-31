package com.wechat.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.util.RedisUtils;

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
	private RedisUtils redisUtils;
	
	@GetMapping("set/{name}")
	public Object set(@PathVariable String name){
		Map<Object,Object> map = new HashMap<>();
		User user = new User();
		user.setId(1+"");
		user.setName(name);
		map.put("user", user);
		redisUtils.set("user",map, 60L);
		return map;
	}
	
	@GetMapping("get/{name}")
	public Object get(@PathVariable String name){
		
		return redisUtils.get(name);
	}
	
	
	class User implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
}
