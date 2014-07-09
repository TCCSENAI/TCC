package util;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mb.LoginMb;



@WebFilter(urlPatterns="/admin/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		LoginMb loginMb = (LoginMb) httpServletRequest.getSession().getAttribute("loginMb");
		
		if(loginMb == null || !loginMb.estaLogado()){
			((HttpServletResponse)servletResponse).sendRedirect(httpServletRequest.getContextPath().concat("/login.xhtml"));		
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
}
