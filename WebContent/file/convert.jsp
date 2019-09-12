<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="cn.hanquan.utils.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>转换结果</title>
	<!-- Bootstrap -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<script src="../css/jquery.min.js"></script>
	<script src="../css/bootstrap.js"></script>
</head>

<body>
<div class="container">
	<h1>转换结果</h1>
	<h3 style="color:#990066">文件信息</h3>

	<font size='4' color='green'>转换成功！</font>

	<br>
	<br>文件名：${fileName}
	<br>排列方式：${num}
	<br>PPT画幅比例：${scale}
	<br><br>
	
	<form>
		<input type="button" class="btn btn-warning"
			onclick="window.location.href = 'dodownload.jsp?path=<%=PathManager.PATH%>${shortFileName}/${shortFileName}.pdf'"
			value="点此下载PDF文档">
	</form>
</div>
</body>
</html>
