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

import mb.LoginCMB;



@WebFilter(urlPatterns="/cliente/*")
public class LoginFilterC implements Filter{

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		LoginCMB loginCMb = (LoginCMB) httpServletRequest.getSession().getAttribute("loginCMB");
		
		if(loginCMb == null || !loginCMb.estaLogado()){
			((HttpServletResponse)servletResponse).sendRedirect(httpServletRequest.getContextPath().concat("/logincliente.xhtml"));		
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
}

