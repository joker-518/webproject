package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImp.ProductServiceImp;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class ProductSearchServlet extends HttpServlet {
	
	/**
	 * Constructor of the object.
	 */
	public ProductSearchServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String search =  request.getParameter("search");
		if(search.equals("阿迪")){
			search = "adidas";
		}else if(search.equals("阿迪达斯")){
			search = "adidas";
		}else if(search.equals("耐克")){
			search = "nike";
		}else if(search.equals("匡威")){
			search = "converse";
		}else if(search.equals("匡")){
			search = "converse";
		}else if(search.equals("耐")){
			search = "nike";
		}
		 //声明Connection对象
		 Connection con;
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
		//String sql = "select pname,shop_price,pimage,pid from product where pname like '%"+search+"%'";
		  String sql ="call Find('%"+search+"%')";
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		List<Product> userlist= new ArrayList<Product>();
		Product p= null;
		String pname = null;
		int shop_price = 0;
		String pimage = null;
		String pid=null;
		int count = 0;
		while(rs.next()){
		//获取stuname这列数据
		p=new Product();
		p.setPname(pname = rs.getString(1));
		p.setShop_price(shop_price = rs.getInt(2));
		p.setPimage(pimage = rs.getString(3));
		p.setPid(pid = rs.getString(4));
		count=count+1;
		userlist.add(p);
		request.setAttribute("userlist",userlist);
		}
		rs.close();
		con.close();
		request.getRequestDispatcher("/jsp/product_search.jsp").forward(request,response);
		} catch(ClassNotFoundException e) {   
		//数据库驱动类异常处理
		System.out.println("Sorry,can`t find the Driver!");   
		e.printStackTrace();   
		} catch(SQLException e) {
		//数据库连接失败异常处理
		e.printStackTrace();  
		}catch (Exception e) {
// TODO: handle exception
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
