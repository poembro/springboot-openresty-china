package com.openresty.bbs.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 未登录 拒绝访问
 * @Author: poembro
 * @Date: 2020-07-21
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		System.out.println("----访问此资源需要完全身份验证----" + exception.getMessage());

			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("请登录");
			out.flush();
			out.close();

	}
}
