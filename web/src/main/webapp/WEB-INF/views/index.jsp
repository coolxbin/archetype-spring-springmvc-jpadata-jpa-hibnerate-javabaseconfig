<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<c:choose>
			<c:when test="${result != null}">添加成功！
   			</c:when>

			<c:otherwise>访问成功！
   			</c:otherwise>
		</c:choose>
	</h1>
	<a href="adduser">测试</a>
	<img alt="default-img"
		src="<%=request.getContextPath()%>/resources/imgs/def_img.png">
</body>
</html>