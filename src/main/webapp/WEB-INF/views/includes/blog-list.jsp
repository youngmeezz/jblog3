<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="blog-list">
		<h2>게시글</h2>
		<ul>
			<c:forEach items="${p_list}" var="postvo">
				<li><a href="${pageContext.servletContext.contextPath }/${id }/${postvo.categoryNo}/${postvo.postNo}">${postvo.title }</a><span>${postvo.regDate }</span></li>
			</c:forEach>
		</ul>		
</div>