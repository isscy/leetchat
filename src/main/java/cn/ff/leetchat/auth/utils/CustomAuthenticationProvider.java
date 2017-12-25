package cn.ff.leetchat.auth.utils;

import cn.ff.leetchat.auth.entity.GrantedAuthorityImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

/**
 * 密码验证
 */
public class CustomAuthenticationProvider implements AuthenticationProvider{
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 获取用户名密码
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		//认证逻辑
		if(name.equals("admin") && password.equals("123456")){
			//设置权限和角色
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			authorities.add(new GrantedAuthorityImpl("AUTH_WRITE"));
			// 生成令牌
			Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
			return auth;
		} else {
			throw new BadCredentialsException("密码错误啊");
		}
	}

	/**
	 * 是否可以提供输入类型的认证服务
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
