<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
	function addCategory() {
		window.location.href = "${pageContext.request.contextPath}/AdminCategoryServlet?method=addCategoryUI";
	}
	function updateCategory() {
		window.location.href = "${pageContext.request.contextPath}/AdminCategoryServlet?method=updateCategoryUI";
	}
</script>
</HEAD>
<body>
	<br>
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
		bgColor="#f5fafe" border="0" >
		<TBODY>
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3"><strong>分类列表</strong>
				</TD>
			</tr>
			<tr>
			
				<td class="ta_01" align="right">
					<button type="button" id="add" name="add" value="添加"
					 class="btn btn-info" onclick="addCategory()">&#28155;&#21152;</button>
				</td>
				
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">
					<table cellspacing="0" cellpadding="1" rules="all"
						bordercolor="gray" border="1" id="DataGrid1"
						style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
						<tr
							style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

							<td align="center" width="18%">序号</td>
							<td align="center" width="17%">分类名称</td>
							<td width="7%" align="center">编辑</td>
							<td width="7%" align="center">删除</td>
						</tr>
						<!--
									varStatus:代表循环过程中存储临时状态值
									 status.count:当前输出元素个数  
								 -->
						<c:forEach items="${allCats}" var="c" varStatus="status">
							<tr onmouseover="this.style.backgroundColor = 'white'"
								onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="18%">${status.count}</td>
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
									width="17%">${c.cname}</td>
								<td align="center" style="HEIGHT: 22px"><a
									href="${pageContext.request.contextPath}/AdminCategoryServlet?method=updateCategoryUI&cid=${c.cid}">
										<img
										src="${pageContext.request.contextPath}/img/admin/i_edit.gif"
										border="0" style="CURSOR: hand">
								</a></td>

								<td align="center" style="HEIGHT: 22px"><a href="${pageContext.request.contextPath}/AdminCategoryServlet?method=delete&cid=${c.cid}" onclick="return confirm('您确定要删除该种类吗?');"> <img
										src="${pageContext.request.contextPath}/img/admin/i_del.gif"
										width="16" height="16" border="0" style="CURSOR: hand">
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</TBODY>
	</table>
</body>
</HTML>

