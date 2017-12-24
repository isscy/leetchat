package cn.ff.leetchat.user.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

	@GetMapping("userInfo")
	public String user(){
		return "ffff";
	}
}
