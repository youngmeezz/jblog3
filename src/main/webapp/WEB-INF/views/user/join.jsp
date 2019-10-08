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
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>
$(function(){
		 	$("#id").change(function(){
			$("#btn-checkid").show();
			$("#img-id").hide();
		}); 
		
		$("#btn-checkid").click(function(){
			var id = $("#id").val();
			if(id == ""){
				return;
			}
		
			
			// ajax 통신
			$.ajax({
				url:"/jblog3/api/user/checkid?id=" + id,
				type:"get",
	 			dataType:"json",
	 			data:"",
				
	 			success: function(response) {
					if(response.result == "fail"){
						console.error(response.message);
						return;
					}
					
					if(response.data == true){
						alert("이미 존재하는 아이디입니다.");
						$("#id").val("");
						$("#id").focus();
						return;
					}
					
					$("#btn-checkid").hide();
					$("#img-id").show();
				},
				
				error: function(xhr, error){
					console.error("error:" + error);
				}
				
			});
		});
	});

</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/blog/blog-main">내블로그</a></li>
		</ul>
		<form class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath }/">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="id" name="id" type="text"> 
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-id" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
