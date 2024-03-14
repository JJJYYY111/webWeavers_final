<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%-- <%@ page import="model.*"%> --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - RegisterSuccess</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='회원가입 완료' />

	<!-- ================ 회원가입 완료시 페이지 ================= -->
	<div class="col-md-9" style="margin: 0px auto; padding: 50px 280px;">
		<div class="blog_post">
			<div style="text-align: center">
				<img src="img/WgRegisterSuccess.png" alt="회원가입완료 이미지">
			</div>
			<div class="blog_details">
				<a href="single-blog.html"></a>
				<p style="text-align: center;">
					회원가입 절차가 완료되었습니다.<br> 로그인 후 더 다양한 기능을 만나보실 수 있습니다.
				</p>
				<h2 style="text-align: center;">
					<a href="/main" class="button button-blog">메인페이지</a> 
					<a href="/login" class="button button-blog">로그인</a>
				</h2>
			</div>
		</div>
	</div>
	<!-- ================ /회원가입 완료시 페이지 ================= -->

	<common:footer />
</body>
</html>