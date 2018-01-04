package com.wechat.controller;

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
}
