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
<title>WeaveGlow - UnregisterSuccess</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='회원탈퇴 완료' />

	<!-- ================ 내용 ================= -->
	<div class="col-md-9" style="margin: 0px auto; padding: 50px 280px;">
		<div class="blog_post">
			<div style="text-align: center">
				<img src="img/WgUnregisterSuccess.png" alt="회원탈퇴 완료 이미지">
			</div>
			<div class="blog_details">
				<p style="text-align: center; font-size: 16px;">
					회원탈퇴가 완료되었습니다.<br> 그 동안 weaveGlow를 이용해주셔서 감사합니다.
				</p>
				<h2 style="text-align: center;">
					<a href="/main" class="button button-blog">메인으로</a>
				</h2>
			</div>
		</div>
	</div>
	<!-- ================ /내용 ================= -->

	<common:footer />
</body>
</html>