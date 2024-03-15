<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeaveGlow - PasswoardCheck</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='회원 정보 확인' />

	<!-- ================ /내용 ================= -->
	<div class="col-lg-12">
		<div class="login_form_inner">
			<h3>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 확인합니다.  </h3>
			<!-- action type : 회원정보수정/회원탈퇴 페이지 전달 -->
			<form class="row login_form" action="/${type}" id="contactForm" name="pwCheckForm" method="POST">	
				<div class="col-md-12 form-group">
					<input type="text" class="form-control" id="ConfirmMid" name="memberID" value="${sessionMid}" readonly>
				</div>
				<div class="col-md-12 form-group">
					<input type="password" class="form-control" id="ConfirmMpw" name="memberPassword" 
						placeholder="Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'">
				</div>
				<div class="col-md-12 form-group"></div>
				<div class="col-lg-12 form-group" style="display: flex;">
					<button type="button" value="submit" onclick="cancelAction()" 
						class="button button-login w-50" style="margin-right: 20px">취소</button>
					<button type="submit" value="submit" class="button button-login w-50" style="margin-left: 20px">확인</button>
				</div>
			</form>
		</div>
	</div>
	<!-- ================ /내용 ================= -->
	
	<common:footer />
	<script src="js/register.js"></script>
</body>
</html>