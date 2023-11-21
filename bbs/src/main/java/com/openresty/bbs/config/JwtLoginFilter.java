package com.openresty.bbs.config;

import com.openresty.common.exception.BadRequestException;
import com.openresty.common.utils.JacksonUtils;
import com.openresty.common.utils.JwtUtils;
import com.openresty.common.utils.Result;

import com.openresty.dao.service.impl.AuthServiceImpl;
import com.openresty.dao.entity.User;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: JWT登录过滤器
 * @Author: poembro
 * @Date: 2020-07-21
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
	AuthServiceImpl userService;

	protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager , AuthServiceImpl userService) {
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(authenticationManager);

		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException {
		try {
			if (!"POST".equals(request.getMethod())) {
				throw new BadRequestException("请求方法错误");
			}
			User user = JacksonUtils.readValue(request.getInputStream(), User.class);

			String username = user.getUsername();
			String password =  user.getPassword();

			System.out.println("-----默认---->" + username + "          " + password);
			// 明文密码 暂时存起来
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadRequestException exception) {
			System.out.println("----attemptAuthentication ---exception---->" + exception.getMessage());
			response.setContentType("application/json;charset=utf-8");
			Result result = Result.create(400, "非法请求");
			PrintWriter out = response.getWriter();
			out.write(JacksonUtils.writeValueAsString(result));
			out.flush();
			out.close();
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	                                        FilterChain chain, Authentication authResult) throws IOException {
		String username = authResult.getName();
		User item = userService.findUserByName(username);
		String jwt = JwtUtils.generateToken(item.getId(), username, authResult.getAuthorities());

		System.out.println("-----生成token ---->" + username);

		response.setContentType("application/json;charset=utf-8");
		Map<String, Object> map = new HashMap<>(4);
		map.put("user", item);
		map.put("token", jwt);
		Result result = Result.ok("登录成功", map);
		PrintWriter out = response.getWriter();
		out.write(JacksonUtils.writeValueAsString(result));
		out.flush();
		out.close();
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	                                          AuthenticationException exception) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		String msg = exception.getMessage();
		//登录不成功时，会抛出对应的异常
		if (exception instanceof LockedException) {
			msg = "账号被锁定";
		} else if (exception instanceof CredentialsExpiredException) {
			msg = "密码过期";
		} else if (exception instanceof AccountExpiredException) {
			msg = "账号过期";
		} else if (exception instanceof DisabledException) {
			msg = "账号被禁用";
		} else if (exception instanceof BadCredentialsException) {
			msg = "用户名或密码错误";
		}
		System.out.println("----unsuccessfulAuthentication----->" + msg);
		PrintWriter out = response.getWriter();
		out.write(JacksonUtils.writeValueAsString(Result.create(401, msg)));
		out.flush();
		out.close();
	}

}