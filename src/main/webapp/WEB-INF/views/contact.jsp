<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Contact</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='회사정보' />

	<!-- ================ 지도 ================= -->
	<section class="section-margin--small">
		<div class="container">
			<div class="d-none d-sm-block mb-5 pb-4">
				<div id="map" style="height: 420px;"></div>
			</div>
		</div>
	</section>
	<!-- ================ /지도 ================= -->


	<!--================ 팀 정보 =================-->
	<section style="padding-top: 0px; padding-bottom: 20px;">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-4 mb-4 mb-lg-0">
					<div class="categories_post">
						<a href="https://blog.naver.com/java_ay">
							<img class="card-img rounded-0" src="img/blog/cat-post/ju.png" alt="정주영 이미지">
							<div class="categories_details">
								<div class="categories_text">
									<h5>정주영</h5>
									<div class="border_line"></div>
									<p>VIEW</p>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-sm-6 col-lg-4 mb-4 mb-lg-0">
					<div class="categories_post">
						<a href="https://blog.naver.com/qkr_tnduss">
							<img class="card-img rounded-0" src="img/blog/cat-post/suyeon.jpg" alt="박수연 이미지">
							<div class="categories_details">
								<div class="categories_text">
									<h5>박수연</h5>
									<div class="border_line"></div>
									<p>MODEL</p>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-sm-6 col-lg-4 mb-4 mb-lg-0">
					<div class="categories_post">
						<a href="https://blog.naver.com/wgw1997">
							<img class="card-img rounded-0" src="img/blog/cat-post/jiwon.jpg" alt="우지원 이미지">
							<div class="categories_details">
								<div class="categories_text">
									<h5>우지원</h5>
									<div class="border_line"></div>
									<p>CONTROLLER</p>
								</div>
							</div>
						</a>
					</div>
				</div>
				
			</div>
		</div>
	</section>
	<section style="padding-bottom: 40px;">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-6 mb-4 mb-lg-0">
					<div class="row">
						<div class="col-sm-0 col-lg-4 mb-0 mb-lg-0"></div>
						<div class="col-sm-12 col-lg-8 mb-12 mb-lg-0">
							<div class="categories_post">
								<a href="https://onivv.tistory.com/">
								<img class="card-img rounded-0" src="img/blog/cat-post/ye.jpg" alt="장예원 이미지">
									<div class="categories_details">
										<div class="categories_text">
											<h5>장예원</h5>
											<div class="border_line"></div>
											<p>VIEW</p>
										</div>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-lg-6 mb-4 mb-lg-0">
					<div class="row">
						<div class="col-sm-12 col-lg-8 mb-12 mb-lg-0">
							<div class="categories_post">
								<a href="https://intellig2nc2.tistory.com/">
								<img class="card-img rounded-0" src="img/blog/cat-post/ji.jpg" alt="천지성 이미지">
									<div class="categories_details">
										<div class="categories_text">
											<h5>천지성</h5>
											<div class="border_line"></div>
											<p>CONTROLLER</p>
										</div>
									</div>
								</a>
							</div>
						</div>
						<div class="col-sm-0 col-lg-4 mb-0 mb-lg-0"></div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================ /팀 정보 =================-->

	<common:footer />
	<script src="js/map.js"></script>
	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=68ctjvgvxz&submodules=geocoder"></script>
</body>
</html>