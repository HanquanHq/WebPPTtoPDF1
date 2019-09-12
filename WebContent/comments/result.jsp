<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>评论发表区</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>用户编号</th>
			<th>用户姓名</th>
			<th>评论内容</th>
			<th>发表时间</th>
		</tr>
		<c:forEach items="${Pageinfo.dataList }" var="content">
			<tr>
				<td>${content.id}</td>
				<td>${content.name}</td>
				<td>${content.cont}</td>
				<td>${content.createtime}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="ShowServlet?pageNum=${Pageinfo.pageNum-1 }&pageSize=${Pageinfo.pageSize}" <c:if test="${Pageinfo.pageNum<=1 }">  onclick="javascript:return false;" </c:if> >上一页</a>
	<a href="ShowServlet?pageNum=${Pageinfo.pageNum+1 }&pageSize=${Pageinfo.pageSize}" <c:if test="${Pageinfo.pageNum>=Pageinfo.total }">  onclick="javascript:return false;" </c:if> >下一页</a>
	
	<br><br>
	<a href="/WebPPTtoPDF1/comments/newcom.jsp">返回主页</a>
	
</body>
</html>