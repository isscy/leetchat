package cn.ff.leetchat.auth.utils;

import cn.ff.leetchat.auth.entity.JSONResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * JWT 生成 验证
 * @createDate 20171219
 */
public class TokenAuthenticationService {

	private static final long EXPIRATIONTIME = 2 * 60 * 60 * 1000; // 2h   --> 432_000_000 5day
	private static final String AUTH_HEADER_NAME = "Authorization";  // 存放token的header key
	private static final String TOKEN_PREFIX = "Bearer";  // token前缀
	private static final String SECRET = "FengFan";  // jwt 密码

	/**
	 * JWT 生成方法
	 */
	public static void addAuthentication(HttpServletResponse response, String username){
		//生成jwt
			String jwt = Jwts.builder()
				.claim("authorities", "ROLE_ADMIN,ROLE_ADMIN")//保存权限（角色）
				.setSubject(username)//用户名写入标题
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)// 签名设置
				.compact();
		// 将jwt写入body
		try {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getOutputStream().println(JSONResult.fillResultString(0,"",jwt));
		}catch (IOException e){
			e.printStackTrace();
		}


	}
	/**
	 * jwt 验证方法
	 */
	public static Authentication getAuthentication(HttpServletRequest request){
		String token = request.getHeader(AUTH_HEADER_NAME);//从header中拿到token
		if(token != null){// 解析token
			Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX,""))
					.getBody();
			// 用户名 和 权限（角色）
			String user = claims.getSubject();
			List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
			return user != null ?
					new UsernamePasswordAuthenticationToken(user,null, authorities) : null;

		}
		return null;
	}


}
