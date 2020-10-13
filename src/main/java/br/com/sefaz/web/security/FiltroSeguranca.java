//package br.com.sefaz.web.security;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import br.com.sefaz.web.controller.LoginController;
//
//
//
//@WebFilter(urlPatterns="/privado/*")
//public class FiltroSeguranca implements Filter {
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		HttpSession sessao = httpRequest.getSession();
//		String contextPath = httpRequest.getContextPath();
//		LoginController loguinController = (LoginController) sessao.getAttribute("loguinController");
//		if(loguinController == null|| loguinController.getUsuarioLogado() == null) {
//			httpResponse.sendRedirect(contextPath + "/loguin.xhtml");
//		}
//		chain.doFilter(httpRequest, httpResponse);
//		
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
