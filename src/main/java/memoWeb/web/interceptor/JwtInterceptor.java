package memoWeb.web.interceptor;

import io.jsonwebtoken.JwtException;
import memoWeb.common.constant.CommonConstants;
import memoWeb.web.main.domain.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		UserDTO userInfo = (UserDTO) session.getAttribute(CommonConstants.SESSION);
//		String token = request.getHeader("token");
//		if (userInfo == null || token == null || !token.equals(userInfo.getToken())) {
//			throw new JwtException("로그인 정보가 없습니다.");
//		}
		return true;
	}
}
