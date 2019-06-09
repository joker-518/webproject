<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="cn.itcast.store.domain.Category" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/Style1.css"
	type="text/css" rel="stylesheet">
<script>
	function formReset() {
		document.getElementById("frm1").reset();
	}
</script>
</HEAD>

<body>
	<form id="frm1" name="Form1"
		action="${pageContext.request.contextPath}/AdminCategoryServlet"
		method="post">
		<input type="hidden" name="method" value="updateCategory">
        <input type="hidden" name="cid" value="${category.cid }"/>
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><STRONG>编辑分类</STRONG></td>
			</tr>
			
			
				<tr>
					<td  align="center" bgColor="#f5fafe" class="ta_01">
						分类名称：</td>
					<td class="ta_01" bgColor="#ffffff" ><input
						type="text" name="cname" value="${category.cname }" class="bg" /></td>
				</tr>
				

				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定"
							class="button_ok">&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" onclick="formReset()" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)"
						value="返回" /> <span id="Label1"></span>
					</td>
				</tr>
			
		</table>
		
		
	</form>
</body>
</HTML>