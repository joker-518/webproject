package cn.itcast.store.web.servlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImp;
import cn.itcast.store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取商品pid
		String pid=request.getParameter("pid");
		//int p=Integer.valueOf(pid).intValue();
		//根据商品pid查询商品信息
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);
		//将获取到的商品放入request
		request.setAttribute("product", product);
		System.out.println(pid);
		//转发到/jsp/product_info.jsp
		Connection con;
		PreparedStatement st2;
		PreparedStatement st3;
		 //驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		//URL指向要访问的数据库名mydata
		String url = "jdbc:mysql://localhost:3306/store_40?useSSL=false";
		//MySQL配置时的用户名
		String user = "root";
		//MySQL配置时的密码
		String password = "123456";
		//遍历查询结果集
		try {
		//加载驱动程序
		Class.forName(driver);
		//1.getConnection()方法，连接MySQL数据库！！
		con = (Connection) DriverManager.getConnection(url,user,password);
		if(!con.isClosed())
		System.out.println("Succeeded connecting to the Database!");
		//2.创建statement类对象，用来执行SQL语句！！
		Statement statement = (Statement) con.createStatement();
		//要执行的SQL语句
		String sql = "select pname,shop_price,pimage from product where pid='"+pid+"'";
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		String pname = null;
		int shop_price = 0;
		String pimage = null;
		while(rs.next()){
			pname = rs.getString(1);
			shop_price = rs.getInt(2);
			pimage = rs.getString(3);
		}
		//System.out.println(pname);
		//System.out.println(shop_price);
		//System.out.println(pimage);
		//String sql2 = "create table record(pid int,pname varchar(50),shop_price int,pimage varchar(50))";
		String sql3 = "INSERT INTO record VALUES ("+pid+",'"+pname+"',"+shop_price+",'"+pimage+"');";
		//st2 = (PreparedStatement) con.prepareStatement(sql2);
		st3 = (PreparedStatement) con.prepareStatement(sql3);
		//st2.executeUpdate();
		st3.executeUpdate();
		//3.ResultSet类，用来存放获取的结果集！！
		rs.close();
		con.close();
		//st2.close();
		st3.close();
		} catch(ClassNotFoundException e) {   
		//数据库驱动类异常处理
		System.out.println("Sorry,can`t find the Driver!");   
		e.printStackTrace();   
		} catch(SQLException e) {
		//数据库连接失败异常处理
		e.printStackTrace();  
		}catch (Exception e) {
//TODO: handle exception
		e.printStackTrace();
		}finally{
		System.out.println("数据库数据成功获取！！");
		}
		
		return "/jsp/product_info.jsp";
	}
	

public String findProductsByPidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=(String) request.getAttribute("pid");
		//获取pid

		int curNum=1;
		//调用业务层功能:以分页形式查询当前类别下商品信息
		//返回PageModel对象(1_当前页商品信息2_分页3_url)
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(pid,curNum);
		//将PageModel对象放入request
		request.setAttribute("page", pm);
		//转发到/jsp/product_list.jsp
		return  "/jsp/product_search.jsp";
	}
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取cid,num
		String cid=request.getParameter("cid"); 
        request.setAttribute( "cid ",cid); 
		int curNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能:以分页形式查询当前类别下商品信息
		//返回PageModel对象(1_当前页商品信息2_分页3_url)
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		//将PageModel对象放入request
		request.setAttribute("page",pm);
		request.setAttribute("cid",cid);
		//转发到/jsp/product_list.jsp
		return  "/jsp/product_list.jsp";
	}
	
}
