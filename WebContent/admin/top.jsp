<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 14px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 14px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 14px;
	COLOR: #000000
}
</style>
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css">
</HEAD>
<body>
	<table width="100%" height="70%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td><img width="100%"
				src="${pageContext.request.contextPath}/img/admin/top_01.jpg">
			</td>

			<td width="100%"
				background="${pageContext.request.contextPath}/img/admin/top_100.jpg">
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" valign="bottom"
				background="${pageContext.request.contextPath}/img/admin/mis_01.jpg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="85%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#000000">
								<script language="JavaScript">
									function realSysTime(clock) {
										var now = new Date();
										var year = now.getFullYear(); //获取年份
										var month = now.getMonth(); //获取月份
										var date = now.getDate(); //获取日期
										var day = now.getDay(); //获取星期

										month = month + 1;
										var arr_week = new Array("星期日", "星期一",
												"星期二", "星期三", "星期四", "星期五",
												"星期六");
										var week = arr_week[day];
										var time = year + "年" + month + "月"
												+ date + "日 " + week;
										clock.innerHTML = time;
									}
									function show() {
										window.setInterval(
												"realSysTime(clock)", 1000);
									}
								</script>
								<div id='clock'></div> <script>
									window.onload = show()
								</script>
						</font>
						</td>
						<td width="15%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									
									<td width="155" valign="bottom"
										>
										用户名：${loginAdmin.name}</td>
									
									
								</tr>
							</table>
						</td>
						<td align="right" width="5%"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</HTML>
