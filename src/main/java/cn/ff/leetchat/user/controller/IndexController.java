package cn.ff.leetchat.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(){
		return "login/login";
	}

	@GetMapping("/hello")
	public String hello(){
		return "socket/hello";
	}
}
