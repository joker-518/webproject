<%@page import="cn.itcast.store.domain.Product"%>
<%@page import="cn.itcast.store.domain.Category"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/Style1.css"
	type="text/css" rel="stylesheet">
</HEAD>

<body>
	<jsp:useBean id="productDao"
		class="cn.itcast.store.dao.daoImp.ProductDaoImp" scope="session"></jsp:useBean>
	<jsp:useBean id="categoryDao"
		class="cn.itcast.store.dao.daoImp.CategoryDaoImp" scope="session"></jsp:useBean>
	<%
		String pid = request.getParameter("pid");
		Product p = productDao.findProductByPid(pid);
	%>
	<!--  -->
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath}/AdminProductServlet?method=updateProduct"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="method" value="updateProduct">
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><STRONG>更新商品</STRONG></td>
			</tr>


			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品名称：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pname" value="${product.pname }"
					id="userAction_save_do_logonName" class="bg" /></td>

				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					是否热门：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="is_hot"
					id="is_hot">
						<option value="1" <%if (p.getIs_hot() == 1) {%> selected <%}%>>是</option>
						<option value="0" <%if (p.getIs_hot() == 0) {%> selected <%}%>>否</option>
				</select></td>

			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					市场价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="market_price" value="${product.market_price }"
					id="userAction_save_do_logonName" class="bg" /></td>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商城价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="shop_price" value="${product.shop_price }"
					id="userAction_save_do_logonName" class="bg" /></td>
			</tr>
			

			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					所属的分类：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><select
					name="cid">
						<%
							List<Category> categorys = categoryDao.getAllCats();
							for (Category category : categorys) {
						%>

						<option value="<%=category.getCid()%>"
							<%=category.getCid() == p.getCid() ? "selected" : ""%>><%=category.getCname()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					商品描述：</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3"><textarea
						name="pdesc" rows="5" cols="30">
						${product.pdesc}</textarea></td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<button type="submit" id="userAction_save_do_submit" value="确定"
						class="button_ok">&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"></span>
				</td>
			</tr>
		</table>
		<input type="hidden" name="pid" value="${product.pid}" />
	</form>
</body>
</HTML>