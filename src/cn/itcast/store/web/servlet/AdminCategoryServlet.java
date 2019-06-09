package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.dao.daoImp.CategoryDaoImp;
import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;

import cn.itcast.store.service.serviceImp.CategoryServiceImp;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	//findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取全部分类信息
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//全部分类信息放入request
		req.setAttribute("allCats", list);
		//转发到/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	
	
	
	//addCategoryUI
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return "/admin/category/add.jsp";
	}
	
	public String updateCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String cid=req.getParameter("cid");
		CategoryService categoryService=new CategoryServiceImp();
		Category category=categoryService.findById(cid);
		//显示编辑菜单
		req.setAttribute("category", category);
		return "/admin/category/edit.jsp";
	}
	
	//addCategory
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类名称
		String cname=req.getParameter("cname");
		//创建分类ID
		String id=UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		CategoryService.addCategory(c);
		//重定向到查询全部分类信息
		resp.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllCats");
		return null;
	}
	
	public String updateCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取分类编号
		//String cname=req.getParameter("cname");
		//String cid=req.getParameter("cid");
		Category category=MyBeanUtils.populate(Category.class, req.getParameterMap());
		//调用业务层添加分类功能
		CategoryService categoryService=new CategoryServiceImp();
		categoryService.updateCategory(category);
		//重定向到查询全部分类信息
		resp.sendRedirect(req.getContextPath()+"/AdminCategoryServlet?method=findAllCats");
		return null;
	}
	
	public String delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String cid=req.getParameter("cid");
		
		//调用业务层添加分类功能
		CategoryService categoryService=new CategoryServiceImp();
		categoryService.delete(cid);
		resp.sendRedirect(req.getContextPath()+"/AdminCategoryServlet?method=findAllCats");
		return null;
	}
	
	
}
