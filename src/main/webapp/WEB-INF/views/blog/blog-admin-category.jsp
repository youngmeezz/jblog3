<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>

<head>
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>

<body>
	<div id="container">
		<div id="header">
			<h1>Spring 이야기</h1>
			<ul>
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/writeform">글작성</a></li>
				</ul>
		      	<table class="admin-cat" id="category-table">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<c:forEach items="${categorylist}" var="categoryvo">
						<tr id="cid-${categoryvo.categoryNo }">
							<td>${categoryvo.categoryNo }</td>
							<td>${categoryvo.name }</td>
							<td>${categoryvo.postCnt }</td>
							<td>${categoryvo.description }</td>
							<td><img class="category-delete" src="${pageContext.request.contextPath}/assets/images/delete.jpg" category-no="${categoryvo.categoryNo }"></td>
						</tr>  
					</c:forEach>	  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" id="category-title"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" id="category-description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가" id="category-add-btn"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>

		<!-- 카테고리  ajax 구현 -->
		<script>
		$(function() {
			$('#category-add-btn').on('click', addCategory)
	 		$('.category-delete').on('click', deleteCategory) 
			
		})
		
 		function deleteCategory(event) {
			let categoryNo = $(event.target).attr('category-no')
			
			$.ajax({
				url: '${pageContext.request.contextPath}/api/${authUser.id}/admin/categoryDelete',
				method: 'post',
				data: {
					categoryNo: categoryNo
				},
				success: function(response) {
					removeCategory(response)
				},
				error: function(error) {
					console.log('error', error)
				}
			})
		} 
		
 		function addCategory() {
			let category = {
				name: $('#category-title').val(),
				description: $('#category-description').val()
			}
			console.log(category)
			$.ajax({
				url: '${pageContext.request.contextPath}/api/${authUser.id}/admin/categoryInsert',
				method: 'post',
				data: category,
				success: function(response) {
// 					if (response.status) {
						clearInput()
						createCategory(response)	
// 					} else {
// 						console.log('response: ', response)
// 					}
				},
				error: function(error) {
					console.log('error')
				}
			})
		} 
		
	 	function clearInput() {
			$('#category-title').val('')
			$('#category-description').val('')
		} 
		
		function createCategory(category) {
			let trTag = $('<tr/>')
			trTag.attr('id', 'cid-' + category.categoryNo)
			
			let noTdTag = $('<td/>')
			noTdTag.text(category.categoryNo)
			trTag.append(noTdTag)
			
			let titleTdTag = $('<td/>')
			titleTdTag.text(category.name)
			trTag.append(titleTdTag)
			
			let postCntTdTag = $('<td/>')
			postCntTdTag.text(category.postCnt)
			trTag.append(postCntTdTag)
			
			let descriptionTdTag = $('<td/>')
			descriptionTdTag.text(category.description)
			trTag.append(descriptionTdTag)
			
			let deleteTdTag = $('<td/>')
			let deleteImgTag = $('<img/>')
			deleteImgTag.attr('src', '${pageContext.request.contextPath}/assets/images/delete.jpg')
 			deleteImgTag.attr('class', 'category-delete') 
			deleteImgTag.attr('id', 'category-' + category.categoryNo)
			deleteImgTag.attr('category-no', category.categoryNo)
 			deleteImgTag.on('click', deleteCategory) 
			deleteTdTag.append(deleteImgTag)
			trTag.append(deleteTdTag)
			
			$('#category-table').append(trTag)
		}
		
	 	function removeCategory(category) {
			$('#category-table tr').remove('#cid-' + category.categoryNo)
		} 
		
		</script>
	</div>
</body>
</html>