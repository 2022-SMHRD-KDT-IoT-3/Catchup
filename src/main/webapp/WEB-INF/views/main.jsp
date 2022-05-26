<%@page import="kr.catchup.mapper.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title> 캐치업 메인</title>
</head>
<body>

	<div class="container">
		<h2>with 캐치업</h2>
		<div class="panel panel-default">
			<div class="panel-heading">메인화면 입니다</div>
			<div class="panel-body">

				<form class="form-horizontal" method="post" action="joinInsert.do">


					<table style="width: 400px; text-align: center; margin: auto;"
						class="table table-hover table-bordered">
						<tr>
						<c:choose>
						<c:when test="${not empty info}">
							${info.user_nick} 님 환영합니다!
							<a href="logout.do"><button class="btn btn-info btn-sm">로그아웃</button></a>
							<br>
							<a href="update.do"><button class="btn btn-info btn-sm">회원정보수정</button></a>
							<c:if test="${info.user_id eq 'admin'}">
								<!--  <a href="memberList.do"><button class="btn btn-info btn-sm">회원정보보기</button> </a> -->
								<button style="width: 100px;" id="userList"
									class="btn btn-info btn-sm">회원정보보기</button>
							</c:if>
						</c:when>
						<c:otherwise>
							<a href="login.do"><button class="btn btn-info btn-sm">로그인</button>
							</a>
							<!--  <a href="join.do"><button class="btn btn-info btn-sm">회원가입</button>  
							</a>-->
						</c:otherwise>

					</c:choose>
						</tr>
						

					</table>


				</form>

			</div>
			<div class="panel-body">캐치업</div>
		</div>
	</div>


	

</body>
</html>