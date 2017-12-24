package cn.ff.leetchat.auth.filter;

import cn.ff.leetchat.auth.utils.TokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class VerifyTokenFilter extends GenericFilterBean{
	private final TokenUtil tokenUtil;

	private VerifyTokenFilter(TokenUtil tokenUtil){
		this.tokenUtil = tokenUtil;
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		try {
			Optional<Authentication> authentication = tokenUtil.verifyToken(request);
			if (authentication.isPresent()) {
				SecurityContextHolder.getContext().setAuthentication(authentication.get());
			} else {
				SecurityContextHolder.getContext().setAuthentication(null);
			}
			filterChain.doFilter(req, resp);
		}catch (JwtException e){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}finally {
			SecurityContextHolder.getContext().setAuthentication(null);
			return;
		}
	}
}
