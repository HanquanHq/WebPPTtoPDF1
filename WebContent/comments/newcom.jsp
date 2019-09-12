<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<META http-equiv=Content-Type content="text/html; charset=utf-8">  
<title>评论区</title>
</head>
<body>
<h1>欢迎留下您的评论！</h1>

	<form action="/WebPPTtoPDF1/PublishServlet"  method="post">
		用户名：&nbsp;&nbsp;&nbsp;
		<input type="text" name="name"><br><br><br>
		
		评论内容：<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		
		<textarea type="text" name="cont" style="height:150px;width:500px;"></textarea><br><br>
		<input type="submit" value="发表" onclick="getTime()">
		<input type="button" value="查看他人评论" onclick="window.location.href='/WebPPTtoPDF1/ShowServlet'">
		<input type="button" value="返回网站首页" onclick="window.location.href='/WebPPTtoPDF1/index.html'">
		<input type="hidden" value="" id="hideTime" name="hideTime">		<!-- 隐藏的时间 -->
		<br>
	</form>
	<script type="text/javascript">
	function getTime() {
		var curTime=getNowFormatDate();
		document.getElementById("hideTime").value = curTime;
		var hide=document.getElementById("hideTime").value;
	}
	function getNowFormatDate() {//获取当前时间
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1<10? "0"+(date.getMonth() + 1):date.getMonth() + 1;
		var strDate = date.getDate()<10? "0" + date.getDate():date.getDate();
		var currentdate = date.getFullYear() + seperator1  + month  + seperator1  + strDate
				+ " "  + date.getHours()  + seperator2  + date.getMinutes()
				+ seperator2 + date.getSeconds();
		return currentdate;
	}
	</script>

</body>
</html>