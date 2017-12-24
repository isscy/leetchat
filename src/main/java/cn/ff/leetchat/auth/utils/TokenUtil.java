package cn.ff.leetchat.auth.utils;

import cn.ff.leetchat.auth.entity.UserAuthentication;
import cn.ff.leetchat.sys.utils.StringUtils;
import cn.ff.leetchat.user.entity.Role;
import cn.ff.leetchat.user.entity.TokenUser;
import cn.ff.leetchat.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenUtil {

	private static final long VALIDITY_TIME_MS = 2 * 60 * 60 * 1000; // 2h   --> 432_000_000 5day
	private static final String AUTH_HEADER_NAME = "Authorization";  // 存放token的header key
	private static final String SECRET = "FengFan";  // jwt 密码

	public Optional<Authentication> verifyToken(HttpServletRequest request){

		final String token = request.getHeader(AUTH_HEADER_NAME);

		if(StringUtils.isNotBlank(token)){
			final TokenUser user = parseUserFromToken(token.replace("Bearer", "").trim());
			if(user != null){
				return Optional.of(new UserAuthentication(user));
			}
		}
		return Optional.empty();
	}

	/**
	 * 把token转为user
	 */
	public TokenUser parseUserFromToken(String token){
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		User user = new User();
		user.setId((String) claims.get("id"));
		user.setRole(Role.valueOf((String) claims.get("role")));
		return new TokenUser(user);
	}
	/**
	 * 把user转为token
	 */
	public String createTokenFromUser(User user){
		return Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
				.setSubject(user.getUsername())
				.claim("id",user.getId())
				.claim("role", user.getRole().toString())
				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();
	}

	public String createTokenFromUser(TokenUser tokenUser){
		return createTokenFromUser(tokenUser.getUser());
	}

}
