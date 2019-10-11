<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="blog-list">
		<h2>게시글</h2>
		<ul>
			<c:forEach items="${p_list}" var="postvo">
				<%-- <li><a href="${pageContext.servletContext.contextPath }/${categoryvo.id }/${categoryvo.categoryNo}">${categoryvo.name}</a></li> --%>
				<li><a href="${pageContext.servletContext.contextPath }/${id }/${postvo.categoryNo}/${postvo.postNo}">${postvo.title }</a><span>${postvo.regDate }</span></li>
			</c:forEach>
		</ul>

		<!-- 			<li><a href="">Spring Camp 2016 참여기</a> <span>2015/05/02</span>	</li>
					<li><a href="">Spring Boot 사용법 정리</a> <span>2015/05/02</span>	</li>
					<li><a href="">Spring Security 설정법</a> <span>2015/05/02</span>	</li>
					<li><a href="">JPA + Hinernate</a> <span>2015/05/02</span>	</li>
					<li><a href="">AOP 활용하기 - DAO 실행시간 측정하기</a> <span>2015/05/02</span>	</li> -->
			
</div>