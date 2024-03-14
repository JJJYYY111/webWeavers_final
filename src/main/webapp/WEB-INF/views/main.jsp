<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Main</title>
<common:head />
</head>
<body>
	<common:header />
	<main class="site-main">
		<!-- ================ 추천 상품 ================= -->
		<section class="section-margin calc-60px">
			<div class="container">
				<div class="section-intro pb-60px">
					<p>Best 찜 상품</p>
					<h2>
						<span class="section-intro__style">추천 상품</span>
					</h2>
				</div>
				<div class="row">
					<c:if test="${fn:length(wdatas) <= 0}">
						상품이 없습니다.
					</c:if>
					<c:if test="${fn:length(wdatas) > 0}">
						<!-- <div class="row"> -->
						<c:forEach var='data' items='${wdatas}'>
							<%--  <c:out value="${data.ppk}" /> --%>
							<!-- <div class="row"></div> -->

							<div class="col-md-6 col-lg-4 col-xl-3">
								<div class="card text-center card-product"
									data-product-pk="${data.ppk}">
									<div class="card-product__img">
										<!-- 					<script>
  								  alert("data.ppk value: ${data.ppk}");
									</script> -->
										<a href="/productDetail?ppk=${data.ppk}"><img
											class="card-img" src="${data.img}" alt=""></a>

										<ul class="card-product__imgOverlay">
											<li>
												<button onclick="wishClick(${data.ppk},'${sessionMid}')"
													class="product-btn-${data.ppk}">
													<c:if test="${data.wish == 1}">
														<i class="ti-heart" style="color: red;"></i>
													</c:if>
													<c:if test="${data.wish == 0}">
														<i class="ti-heart" style="color: #fff;"></i>
													</c:if>
												</button>
											</li>
										</ul>
									</div>
									<div class="card-body">
										<h4 class="card-product__title">
											<a href="/productDetail?ppk=${data.ppk}">${data.pname}</a>
										</h4>
										<p class="card-product__price"><fmt:formatNumber value="${data.price}" currencyCode="KRW" />원</p>
									</div>
								</div>

							</div>
						</c:forEach>
						<!-- </div> -->
					</c:if>
				</div>
			</div>
		</section>
		<!-- ================ /추천 상품 ================= -->

		<!-- ================ 인기 상품 ================= -->
		<section class="section-margin calc-60px">
			<div class="container">
				<div class="section-intro pb-60px">
					<p>Best 판매 상품</p>
					<h2>
						<span class="section-intro__style">인기 상품</span>
					</h2>
				</div>
				<div class="owl-carousel owl-theme" id="bestSellerCarousel">

					<c:if test="${fn:length(sdatas) <= 0}">
						상품이 없습니다.
					</c:if>
					<c:if test="${fn:length(sdatas) > 0}">
						<!-- <div class="row"> -->
						<c:forEach var="data" items="${sdatas}">
							<div class="card text-center card-product"
								data-product-pk="${data.ppk}">
								<div class="card-product__img">
									<a href="/productDetail?ppk=${data.ppk}"><img
										class="card-img" src="${data.img}" alt="${data.img}번 상품사진"></a>
									<ul class="card-product__imgOverlay">
										<li>
											<button onclick="wishClick(${data.ppk},'${sessionMid}')"
												class="product-btn-${data.ppk}">
												<c:if test="${data.wish == 1}">
													<i class="ti-heart" style="color: red;"></i>
												</c:if>
												<c:if test="${data.wish == 0}">
													<i class="ti-heart" style="color: #fff;"></i>
												</c:if>
											</button>
										</li>
									</ul>
								</div>
								<div class="card-body">
									<h4 class="card-product__title">
										<a href="/productDetail?ppk=${data.ppk}">${data.pname}</a>
									</h4>
									<p class="card-product__price"><fmt:formatNumber value="${data.price}" currencyCode="KRW" />원</p>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</section>
		<!-- ================ /인기 상품 ================= -->
	</main>

	<common:footer />
</body>
</html>