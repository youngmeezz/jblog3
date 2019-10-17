<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
		<h1>${blogvo.title }</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/log">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postvo.title }</h4>
					<p>
						${postvo.content }
					<p>
				</div>
				 <c:import url="/WEB-INF/views/includes/blog-list.jsp"/> 
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
					<img src="${pageContext.request.contextPath}${blogvo.logo}">
			</div>
		</div>

		 <c:import url="/WEB-INF/views/includes/navigation.jsp"/> 
		 <c:import url="/WEB-INF/views/includes/footer.jsp"/> 
	</div>
</body>
</html>