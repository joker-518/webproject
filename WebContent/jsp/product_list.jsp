<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.mysql.jdbc.Connection" import="java.sql.DriverManager" import="java.sql.ResultSet" import="java.sql.SQLException" import="com.mysql.jdbc.Statement" import="cn.itcast.store.domain.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
	
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>
	
	<body>
		<%!public static int x=0; %>
			<%@ include file="/jsp/header.jsp" %>


        <c:if test="${empty page.list}">
        	<div class="row" style="width:1210px;margin:0 auto;">
        		<div class="col-md-12">
        			<h1>暂无商品信息</h1>
        		</div>
        	</div>	
        </c:if>
        
        
        <c:if test="${not empty page.list}">
	        <div class="row" style="width:1210px;margin:0 auto;">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li><a href="#">首页</a></li>
					</ol>
				</div>
	          <c:forEach items="${page.list}" var="p">
				<div class="col-md-2">
					<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}">
						<img src="${pageContext.request.contextPath}/${p.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p><a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}" style='color:green'>${p.pname}</a></p>
					<p><font color="#FF0000">商城价：&yen;${p.shop_price}</font></p>
				</div>
			  </c:forEach>
			</div>
			<%@ include file="/jsp/pageFile.jsp" %>
        </c:if>
        <br><br>
 

				
		
		
		
		
		

		<!--
       		商品浏览记录:
        -->
        <% Connection con;

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
		Statement statement = (Statement) con.createStatement();
		if(!con.isClosed())
		System.out.println("Succeeded connecting to the Database!");
		//2.创建statement类对象，用来执行SQL语句！！
		//要执行的SQL语句
		String sql = "select pid,pname,shop_price,pimage from record";
		//3.ResultSet类，用来存放获取的结果集！！
		ResultSet rs = statement.executeQuery(sql);
		List<Product> userlist= new ArrayList<Product>();
		Product p= null;
		String pid=null;
		String pname = null;
		int shop_price = 0;
		String pimage = null;
		while(rs.next()){
			p=new Product();
			p.setPid(pid = rs.getString(1));
			p.setPname(pname = rs.getString(2));
			p.setShop_price(shop_price = rs.getInt(3));
			p.setPimage(pimage = rs.getString(4));
			userlist.add(p);
		}
		request.setAttribute("userlist",userlist);
		//3.ResultSet类，用来存放获取的结果集！！
		rs.close();
		con.close();
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
		}%>
		<%
		ArrayList userlist = (ArrayList) request.getAttribute("userlist");
		String cid =(String)request.getAttribute("cid");
	%>
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: auto;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录 </h4>
			<div style="width: 50%;float: right;text-align: right;">
			<form action="DeleteRecordServlet" method="post" onsubmit="return sumbit_sure()">
				<input type="text" style="display:none" value="<%=cid %>" name="cid"/>
				<input type="submit" value="删除浏览记录"/>
				</form>
				</div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<%
				for (int i = 0; i < userlist.size(); i++) {
					Product p = (Product) userlist.get(i);
			%>
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=<%=p.getPid()%>">
				<img src="<%=p.getPimage()%>" width="80" height="80" style="display: inline-block;">
				</a>
				<p><font size="1px"><%=p.getPname()%></font></p>
				<p>
					<font size="2px" color="#FF0000">商城价：<%=p.getShop_price()%></font>
				</p>
			</div>

			<%
				}
			%>
				</ul>

			</div>
		</div>
		
		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="${pageContext.request.contextPath}/jsp/info.jsp">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2019六组 版权所有
		</div>

	</body>
	<script language="javascript">
		function sumbit_sure(){
		var gnl=confirm("确定要删除浏览记录？");
		if (gnl==true){
		return true;
		}else{
		return false;
		}
		}
	</script> 

</html>