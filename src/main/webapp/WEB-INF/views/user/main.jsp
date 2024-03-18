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
<style>
.carousel {
	position: relative;
	overflow: hidden;
	width: 100%; 
	height: 300px; 
	margin: 0 auto; /* 수평 가운데 정렬 */
	text-align: center; 
}

.carousel img {
	width: 100%;
	height: 100%;
	object-fit: cover; /* 이미지가 영역에 맞게 잘리지 않도록 */
}

.prev, .next {
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	/*background-color: rgba(0, 0, 0, 0.5);*/
	color: white;
	padding: 10px;
	cursor: pointer;
	border: none;
}

.prev {
	left: 0;
	box-shadow: none;
}

.next {
	right: 0;
	box-shadow: none;
}

.carousel img {
	display: inline-block; 
	vertical-align: middle; 
	max-width: 100%; 
	max-height: 100%; 
}
</style>

</head>
<body>
	<common:header />
	<main class="site-main">

		<!-- 캐러쉘  -->
		<div class="carousel">
			<img src="uploadimg/carousel/slide1.jpg" alt="Image 1" style="object-fit: contain"> 
			<img src="uploadimg/carousel/slide2.jpg" alt="Image 2" style="object-fit: contain"> 
			<img src="uploadimg/carousel/slide3.jpg" alt="Image 3" style="object-fit: contain">
			<button class="prev" onclick="prevSlide()">&#10094;</button>
			<button class="next" onclick="nextSlide()">&#10095;</button>
		</div>
		<!-- ================ 추천 상품 ================= -->
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
						<%--  <c:out value="${data.productPK}" /> --%>
						<!-- <div class="row"></div> -->

						<div class="col-md-6 col-lg-4 col-xl-3">
							<div class="card text-center card-product"
								data-product-pk="${data.productPK}">
								<div class="card-product__img">
									<!-- 					<script>
  								  alert("data.ppk value: ${data.productPK}");
									</script> -->
									<a href="/productDetail?productPK=${data.productPK}"><img
										class="card-img" src="${data.productImg}" alt=""></a>

									<ul class="card-product__imgOverlay">
										<li>
											<button onclick="wishClick(${data.productPK},'${sessionMid}')"
												class="product-btn-${data.productPK}">
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
										<a href="/productDetail?ppk=${data.productPK}">${data.productName}</a>
									</h4>
									<p class="card-product__price">
										<fmt:formatNumber value="${data.productPrice}" currencyCode="KRW" />
										원
									</p>
								</div>
							</div>

						</div>
					</c:forEach>
					<!-- </div> -->
				</c:if>
			</div>
		</div>
		<!-- </section> -->
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
								data-product-pk="${data.productPK}">
								<div class="card-product__img">
									<a href="/productDetail?productPK=${data.productPK}"><img
										class="card-img" src="${data.productImg}" alt="${data.productImg}번 상품사진"></a>
									<ul class="card-product__imgOverlay">
										<li>
											<button onclick="wishClick(${data.productPK},'${sessionMid}')"
												class="product-btn-${data.productPK}">
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
										<a href="/productDetail?productPK=${data.productPK}">${data.productName}</a>
									</h4>
									<p class="card-product__price">
										<fmt:formatNumber value="${data.productPrice}" currencyCode="KRW" />
										원
									</p>
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