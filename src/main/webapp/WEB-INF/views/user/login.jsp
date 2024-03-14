<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Login</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='회원가입/로그인' />

	<!--================ 내용 =================-->
	<section class="login_box_area section-margin">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login_box_img">
						<div class="hover">
							<h4>WeaveGlow - Register</h4>
							<p>위브글로우 회원이 되어 다양한 혜택을 누려보세요.</p>
							<a class="button button-account" href="/register">회원가입</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="login_form_inner">
						<h3>Log In</h3>
						<form name=loginForm; class="row login_form"
							action="/memberSelectOne" id="contactForm" method="POST">
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="mid" name="memberID"
									placeholder="아이디 입력" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '아이디 입력'" required>
							</div>
							<div class="col-md-12 form-group">
								<input type="password" class="form-control" id="mpw" name="memberPassword"
									placeholder="비밀번호 입력" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '비밀번호 입력'" required>
							</div>
							<div class="col-md-12 form-group">
								<div class="errorBox"></div>
							</div>
							<div class="col-md-12 form-group">
								<button type="submit" value="submit" class="button button-login w-100">로그인</button>
							</div>
							<!-- 로그인 실패 문구 -->
							<c:set var="asd" value="${msg}" />
							<p style="color: red; text-align: center;" id="message">${asd}</p>
							<!-- /로그인 실패 문구 -->
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ /내용 =================-->

	<common:footer />
</body>
</html>