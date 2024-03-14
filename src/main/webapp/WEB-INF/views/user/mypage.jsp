<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - MyPage</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='마이페이지' />

	<!--================ 내용 =================-->
	<section class="checkout_area section-margin--large">
		<div class="container">
			<!-- 회원 기본정보 및 회원탈퇴 버튼 -->
			<div class="single_sidebar_widget author_widget profil-card">
				<img class="author_img rounded-circle" src="img/blog/author.png" alt="회원사진">
				<div class="profil-contant">
					<h4>${data.nickname}</h4>
					<p>${data.email}</p>
				</div>
				<div class="custom-button"><a class="mypage-menu" href="/passwordCheck?type=unregister" style="color: black !important;">회원탈퇴</a></div>
			</div>	
			<!-- /회원 기본정보 및 회원탈퇴 버튼-->
			<div class="row" style="margin-bottom: 100px">
				<div class="col-sm-6 col-lg-4 mb-4 mb-lg-0">
					<div class="mypage-button"><a class="mypage-menu" href="/passwordCheck?type=profileChange" style="color: black !important;">개인정보수정</a></div>
				</div>
				<div class="col-sm-6 col-lg-4 mb-4 mb-lg-0">
					<div class="mypage-button"><a class="mypage-menu" href="/checkoutList" style="color: black !important;">구매내역</a></div>
				</div>
				<div class="col-sm-6 col-lg-4 mb-4 mb-lg-0">
					<div class="mypage-button"><a class="mypage-menu" href="/cart" style="color: black !important;">장바구니</a></div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-lg-6 mb-4 mb-lg-0">
					<div class="row" style="margin-bottom: 100px">
						<div class="col-sm-0 col-lg-4 mb-0 mb-lg-0"></div>
						<div class="col-sm-12 col-lg-8 mb-12 mb-lg-0">
							<div class="mypage-button"><a class="mypage-menu" href="/reviewList" style="color: black !important;">리뷰내역</a></div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-lg-6 mb-4 mb-lg-0">
					<div class="row">
						<div class="col-sm-12 col-lg-8 mb-12 mb-lg-0">
							<div class="mypage-button"><a class="mypage-menu" href="/wishList" style="color: black !important;">찜목록</a></div>
						</div>
						<div class="col-sm-0 col-lg-4 mb-0 mb-lg-0"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ /내용 =================-->

	<common:footer />
</body>
</html>