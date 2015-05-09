package org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.web;

import javax.annotation.Resource;

import org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.business.UserService;
import org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/adduser")
	public ModelAndView addUser(){
		ModelAndView result = new ModelAndView();
		User u = new User("mallen", "123456");
		userService.add(u);
		
		result.addObject("result", "添加成功!");
		result.setViewName("index");
		return result;
	}
}
