package com.openresty.bbs.config;

import com.openresty.dao.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: Spring Security配置类
 * @Author: poembro
 * @Date: 2020-07-19
 */
@Configuration
// @EnableWebSecurity  // 注解启用了 Web 安全。
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	LoginServiceImpl userService;



	@Bean //  定义了密码的加密方式
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override // 还允许你定义密码的加密方式。与过去直接存储明文密码相比，现代的方法通常使用强加密算法。
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
	}


	// 注入一个 异常处理 类
	@Autowired
	MyAuthenticationEntryPoint myAuthenticationEntryPoint;

	@Override   //  可以精确地定义哪些URL可以由哪些角色访问。  其他未匹配的请求需要用户登录。
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable() //禁用 csrf 防御
		.cors()//开启跨域支持
		.and()
		//基于Token，不创建会话
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()

		// 对于登录接口 允许匿名访问
		.antMatchers("/hello").anonymous() // 匿名
		// .antMatchers("/hello").denyAll() // 拦截所有
		//任何 /v1 开头的路径下的请求都需要经过JWT验证
		//.antMatchers(HttpMethod.GET, "/v1/**").hasAnyRole("admin")
		//.antMatchers("/v1/**").hasAnyRole("admin")
		//.anyRequest().permitAll()	 //其它路径全部放行
		.anyRequest().authenticated() //  其他全部验证授权
		.and()
		// 自定义JWT过滤器
		.addFilterBefore(new JwtLoginFilter("/admin/login", authenticationManager(), userService), UsernamePasswordAuthenticationFilter.class)
		//        添加JWT过滤器，在UsernamePasswordAuthenticationFilter之前添加
		.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
		// 配置异常处理器  未登录时，返回json，在前端执行重定向
		.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
