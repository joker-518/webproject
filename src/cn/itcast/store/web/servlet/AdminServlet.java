package cn.itcast.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.store.domain.Admin;
import cn.itcast.store.service.AdminService;
import cn.itcast.store.service.serviceImp.AdminServiceImp;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends BaseServlet {
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "/admin/index.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		String username = request.getParameter("name");
		String password	= request.getParameter("password");
		
		//2.调用service完成登录 返回值:user
		AdminService us = new AdminServiceImp();
		Admin admin=new Admin();
		MyBeanUtils.populate(admin, request.getParameterMap());
		admin=us.login(admin);
		
		request.getSession().setAttribute("loginAdmin", admin);
		
		response.sendRedirect("/store_v5/admin/home.jsp");


		//3.判断user 根据结果生成提示
		if(admin == null){
			//用户名和密码不匹配
			request.setAttribute("msg", "用户名和密码不匹配");;
			return "/admin/index.jsp";
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("msg", "登录失败");
		return "/admin/index.jsp";
	}
	
	return null;
}

	
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  //
			request.getSession().invalidate();
		  //
		  response.sendRedirect("/store_v5/admin/index.jsp");
		  return null;	
	}
   

}
