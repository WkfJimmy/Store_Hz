package cn.wkf.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("LoginInterceptor的afterCompletion方法");
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor的postHandle方法");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("LoginInterceptor的preHandle方法");
		// 获取Session
		HttpSession session = request.getSession();
		// 判断session中是否有登录信息
		if (session.getAttribute("username") == null) {
			// 没有登录信息，则：重定向到登录页
			response.sendRedirect("../user/login.do");
			System.out.println("666666拦截器的错");
			// 执行拦截
			return false;
		} else {
			// 有登录信息，则：允许正常访问，直接放行
			return true;
		}
	}

}
