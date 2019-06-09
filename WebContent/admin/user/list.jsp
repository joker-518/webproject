<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
	<script type="text/javascript">
		function updateUser(){
			window.location.href = "${pageContext.request.contextPath}/AdminUserServlet?method=updateUserUI";
			}
		</script>

</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>用户列表</strong>
					</TD>
				</tr>
				<tr>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="15%">序号</td>
								<td align="center" width="10%">用户名称</td>
								<td align="center" width="10%">真实姓名</td>
								<td align="center" width="10%">手机</td>
								<td align="center" width="10%">性别</td>
								<td align="center" width="15%">邮箱</td>
								<td width="10%" align="center">编辑</td>
								<td width="10%" align="center">删除</td>
							</tr>
							<c:forEach items="${allUsers}" var="c" varStatus="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="15%">${status.count}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${c.username}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${c.name}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${c.telephone}</td>	
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${c.sex}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="15%">${c.email}</td>
									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/AdminUserServlet?method=updateUserUI&uid=${c.uid}">
											<img
											src="${pageContext.request.contextPath}/img/admin/i_edit.gif"
											border="0" style="CURSOR: hand">
									</a></td>

									<td align="center" style="HEIGHT: 22px"><a href="${pageContext.request.contextPath}/AdminUserServlet?method=delete&uid=${c.uid}" onclick="return confirm('您确定要删除该用户吗?');">
											<img
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
		
      <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</form>

</body>
</HTML>

