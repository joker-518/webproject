package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Product;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DeleteRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteRecordServlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cid = request.getParameter("cid");
		Connection con;
		PreparedStatement st;
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
		String sql = "truncate  table  record;";
		//3.ResultSet类，用来存放获取的结果集！！
		st = (PreparedStatement) con.prepareStatement(sql);
		//st2.executeUpdate();
		st.executeUpdate();
		st.close();
		con.close();
		response.sendRedirect("/store_v5/ProductServlet?method=findProductsByCidWithPage&num=1&cid="+cid+"");
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
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
