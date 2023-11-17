package com.openresty.bbs.config;

import com.openresty.dao.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Description: JWT请求过滤器
 * @Author: poembro
 * @Date: 2020-07-21
 */
public class JwtFilter extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//后台管理路径外的请求直接跳过
		if (!request.getRequestURI().startsWith(request.getContextPath() + "/v1")) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		String jwt = request.getHeader("Authorization");
		if (JwtUtils.judgeTokenIsExist(jwt)) {
			try {
				Claims claims = JwtUtils.getTokenBody(jwt);
				String username = claims.getSubject(); // 从jwt中获取用户
				String role = (String) claims.get("authorities");

				// 将用户和角色 存入SecurityContextHolder
				List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(token);

				filterChain.doFilter(servletRequest, servletResponse);

				System.out.println("---验证token---username--"+username + "-------" + role);

				return;
			} catch (Exception e) {
				e.printStackTrace();
				response.setContentType("application/json;charset=utf-8");
 				PrintWriter out = response.getWriter();
				out.write("凭证已失效，请重新登录！");
				out.flush();
				out.close();
				return;
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}