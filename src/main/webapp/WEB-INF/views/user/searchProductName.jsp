<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - SearchProductName</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='"${keyword}"검색결과' /> <!-- 상품을 검색했을때 검색결과 -->

	<!-- ================ 내용 ================= -->
	<section class="section-margin--small mb-5">
		<div class="container">
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12">
					
					<!-- 상품 -->
					<section class="lattest-product-area pb-40 category-list">
							<c:if test="${fn:length(datas) <= 0}"><br><h4 style="text-align:center;">상품이 없습니다.</h4><br><br></c:if> <!-- 검색할 때 상품 배열이 비어있는 경우 -->
						<div class="row">
							<c:if test="${fn:length(datas) > 0}">						  <!-- 검색할 때 상품 배열이 있는 경우 -->
								<c:forEach var='data' items='${datas}'>	<!-- 데이터를 순회하며 상품을 가져옴 -->
									<div class="col-md-6 col-lg-4 col-xl-3">
										<div class="card text-center card-product"
											data-product-pk="${data.ppk}">	
											<div class="card-product__img">
												<a href="productDetail.do?ppk=${data.ppk}"><img 
													class="card-img" src="${data.img}" alt="${data.ppk}번 상품사진"></a> <!-- 상품 사진을 누를경우 상품 상세 페이지로 이동 -->
												<ul class="card-product__imgOverlay">
													<li>
													
													<!-- 상품 찜하기 기능 -->
														<button onclick="wishClick(${data.ppk})" class="product-btn-${data.ppk}"> 
															<c:if test="${data.wish == 1}"><i class="ti-heart" style="color: red;"></i></c:if>
															<c:if test="${data.wish == 0}"><i class="ti-heart" style="color: #fff;"></i></c:if>
														</button>
													</li>
												</ul>
											</div>
											<div class="card-body">
												<h4 class="card-product__title">
													<a href="productDetail.do?ppk=${data.ppk}">${data.pname}</a> <!-- 상품 이름을 클릭시 상세정보 페이지로 이동 -->
												</h4>
												<p class="card-product__price"><fmt:formatNumber value="${data.price}" currencyCode="KRW" />원</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</section>
					<!-- /상품 -->
				</div>
			</div>
		</div>
	</section>
	<!-- ================ /내용 ================= -->

	<common:footer />
</body>
</html>