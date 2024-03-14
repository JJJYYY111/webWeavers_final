<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>WeaveGlow - profileChange</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='개인정보수정' />

	<!-- ================ 내용 ================= -->
	<section class="login_box_area section-margin">
		<div class="container">
			<div class="row">

				<form name="profileChangeForm" class="col-lg-12 row login_form"
					action="/memberUpdate" method="POST" id="profileChange_form">
					<!-- 개인정보수정 -->
					<div class="col-lg-12">
						<div class="login_form_inner register_form_inner">
							<h3>개인정보수정</h3>
							<div class="col-md-12 form-group">
								<div class="col-md-3 label-text">이름</div>
								<input type="text" class="form-control col-md-8" id="registName"
									name="memberName" placeholder="이름 입력" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '이름 입력'"
									style="display: inline-block;" value="${name}">
							</div>
							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-3 label-text">아이디</div>
								<input type="text" class="form-control col-md-8" id="registMid"
									name="memberID" placeholder="아이디 입력" onfocus="this.placeholder = ''"
									onblur="this.placeholder = '아이디 입력'"
									style="display: inline-block;" value="${sessionMid}" disabled>
							</div>
							<div class="col-md-12" style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-3 label-text"></div>
								<span class="col-md-9" id="idCheck"></span>
							</div>
							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-3 label-text">비밀번호</div>
								<input type="password" class="form-control col-md-8"
									id="registMpw" name="memberPassword" placeholder="비밀번호 입력"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '비밀번호 입력'"
									style="display: inline-block;" value="${mpw}">
							</div>
							<div class="col-md-12" style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-9" id="pwCheck"></span>
							</div>
							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-3 label-text">비밀번호 확인</div>
								<input type="password" class="form-control col-md-8"
									id="confirmMpw" name="confirmMpw" placeholder="비밀번호 확인"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '비밀번호 확인'"
									style="display: inline-block;" value="${mpw}">
							</div>
							<div class="col-md-12" style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-9" id="confirmPwCheck"></span>
							</div>							
							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-3 label-text">닉네임</div>
								<input type="text" class="form-control col-md-8"
									id="registNickname" name="memberNickname" placeholder="닉네임 입력"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '닉네임 입력'"
									style="display: inline-block;" value="${nickname}">
							</div>
							<div class="col-md-12" style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-9" id="nickCheck"></span>
							</div>
							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-3 label-text">전화번호</div>
								<input type="text" class="form-control col-md-8"
									id="registPhone" name="memberPhone" placeholder="전화번호 입력"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '전화번호 입력'"
									style="display: inline-block;" value="${phone}">
							</div>
							<div class="col-md-12" style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-9" id="phoneCheck"></span>
							</div>
							<div class="col-md-12 form-group">
								<div class="col-md-3 label-text">생년월일</div>
								<input type="date" class="form-control col-md-8"
									id="registBirth" name="memberBirth" placeholder="생년월일"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = '생년월일'"
									style="display: inline-block;" value="${birth}" disabled>
							</div>
							<div class="col-md-12 form-group" style="margin: 0;">
								<div class="col-md-3 label-text">이메일</div>
								<input type="text" class="form-control col-md-8"
									id="registEmail" name="memberEmail" placeholder="e-mail"
									onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'e-mail'"
									style="display: inline-block;" value="${email}">
							</div>
							<div class="col-md-12" style="text-align: left; font-size: 13px; padding: 3px">
								<div class="col-md-4 label-text"></div>
								<span class="col-md-9" id="emailCheck"></span>
							</div>							
							<div class="col-md-12 form-group">
								<button type="submit" value="submit"
									class="button button-register w-100">확인</button>
							</div>
						</div>
					</div>
					<!-- 개인정보수정 -->
				</form>
				
			</div>
		</div>
	</section>
	<!-- ================ /내용 ================= -->

	<common:footer />
	<script src="js/register.js"></script>	
</body>
</html>