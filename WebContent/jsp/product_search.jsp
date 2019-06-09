<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,cn.itcast.store.domain.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品列表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

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

	<%@ include file="/jsp/header.jsp"%>
	<%
		ArrayList userlist = (ArrayList) request.getAttribute("userlist");
	%>
	<div class="row" style="width:1210px;margin:0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
			
				<li><a href="#">搜索结果 </a></li>
			</ol>

			<%
				int j = 0;
			%>
			<%
				for (int i = 0; i < userlist.size(); i++) {
					Product p = (Product) userlist.get(i);
			%>
			<div class="col-md-2">
			<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=<%=p.getPid()%>">
				<img src="<%=p.getPimage()%>" width="170" height="170"
					style="display: inline-block;"></a>
				<a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=<%=p.getPid()%>">
				<p><%=p.getPname()%></p></a>
				<p>
					<font color="#FF0000">商城价：<%=p.getShop_price()%></font>
				</p>
			</div>

			<%
				}
			%>
		</div>
	</div>


	<div style="margin-top:50px;">
		<img src="${pageContext.request.contextPath}/img/footer.jpg"
			width="100%" height="78" alt="我们的优势" title="我们的优势" />
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
		Copyright &copy; 2005-2016 传智商城 版权所有</div>

</body>

</html>