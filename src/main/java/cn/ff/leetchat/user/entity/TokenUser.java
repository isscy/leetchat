package cn.ff.leetchat.user.entity;

import org.springframework.security.core.authority.AuthorityUtils;

public class TokenUser extends org.springframework.security.core.userdetails.User{

	private User user;

	public TokenUser(User user) {
		super(user.getId(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}
	public User getUser(){
		return user;
	}
	public String getRole(){
		return user.getRole().toString();
	}
}
