<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="navigation">
		<h2>카테고리</h2>
		<ul>
			<c:forEach items="${list}" var="categoryvo">
				<li><a href="${pageContext.servletContext.contextPath }/${id }/${categoryvo.id }/${categoryvo.categoryNo}">${categoryvo.name}</a></li>
			</c:forEach>
		</ul>
		<!-- <ul>
			<li><a href="">닥치고 스프링</a></li>
			<li><a href="">스프링 스터디</a></li>
			<li><a href="">스프링 프로젝트</a></li>
			<li><a href="">기타</a></li>
		</ul> -->
</div>