package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.Product;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.service.serviceImp.UserServiceImp;
import cn.itcast.store.web.base.BaseServlet;


public class AdminUserServlet extends BaseServlet {
	//findAllUsers
		public String findUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取全部用户信息
			UserService UserService=new UserServiceImp();
			List<User> list=UserService.getAllUsers();
			//全部分类信息放入request
			req.setAttribute("allUsers", list);
			//转发到/admin/user/list.jsp
			return "/admin/user/list.jsp";
		}
		
		public String updateUserUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			String uid=req.getParameter("uid");
			UserService userService=new UserServiceImp();
			User user=userService.findById(uid);
			//显示编辑菜单
			req.setAttribute("user", user);
			return "/admin/user/edit.jsp";
		}
		
		
		public String updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			//获取用户信息
			String uid=req.getParameter("uid");
			String username=req.getParameter("username");
			String pwd=req.getParameter("password");
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String phone=req.getParameter("phone");
			//String addr=req.getParameter("addr");
			
			//调用业务层添加分类功能
			UserService userService=new UserServiceImp();
			User u=userService.findById(uid);
			u.setUsername(username);
			u.setEmail(email);
			u.setPassword(pwd);
			u.setName(name);
			u.setTelephone(phone);
			userService.updateUser(u);
			//重定向到查询全部分类信息
			resp.sendRedirect(req.getContextPath()+"/AdminUserServlet?method=findUsers");
			//resp.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllCats");
			return null;
			
		}
		
		public String delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {

			String uid=req.getParameter("uid");
			
			//调用业务层添加分类功能
			UserService userSeuvice=new UserServiceImp();
			userSeuvice.delete(uid);
			resp.sendRedirect(req.getContextPath()+"/AdminUserServlet?method=findUsers");
			return null;
		}
		
		
		
}
