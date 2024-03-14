<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeaveGlow - wishList</title>
<common:head />
</head>
<body>
	<common:header />
	
	<common:banner bannerText='위시리스트' />
	
	<!--================ 내용 =================-->
		<section class="section-margin calc-60px">
			<div class="container">
				<div class="section-intro pb-60px">
					<h5>${sessionMid}님의 찜 상품</h5> 
				</div>
				<div class="row">
					
					
					<c:if test="${fn:length(wdatas) <= 0}">상품이 없습니다.</c:if> <!-- 찜목록에 상품이 0이하라면 -->
					<c:if test="${fn:length(wdatas) > 0}">							<!-- 찜목록에 상품이 1개 이상이라면 -->
						<c:forEach var='data' items='${wdatas}'> <!-- 찜목록 배열을 순회하며 있다면 출력 -->
						
							<div class="col-md-6 col-lg-4 col-xl-3" id='${data.ppk}'>
								<div class="card text-center card-product"
									data-product-pk="${data.ppk}">
									<div class="card-product__img">
										<a href="productDetail.do?ppk=${data.ppk}"><img class="card-img" 
											src="${data.img}" alt="${data.ppk}번 상품사진"></a> <!-- 상품 사진을 누르면 해당ppk번 상품 사진으로 이동 -->
										<ul class="card-product__imgOverlay">
											<li>
											<!-- 찜 버튼 -->
												<button onclick="wishClick(${data.ppk},'${sessionMid}')" class="product-btn-${data.ppk}">
													<c:if test="${data.ppk > 0}">
														<i class="ti-heart" style="color: red;"></i> <!-- 해당상품 상품번호가 0보다 크다면 빨간색 하트 -->
													</c:if>
													<c:if test="${data.ppk <= 0}">
														<i class="ti-heart" style="color: #fff;"></i> <!-- 해당상품 상품번호가 0보다 작다면 하얀색 하트 -->
													</c:if>
												</button>
											</li>
										</ul>
									</div>
									<div class="card-body">
										<h4 class="card-product__title">
											<a href="productDetail.do?ppk=${data.ppk}">${data.pname}</a> <!-- 상품 이름을 누르면 상품 상세정보 페이지로 이동 -->
										</h4>
									</div>
								</div>
							</div>
							
						</c:forEach>
					</c:if>
					
				</div>
			</div>
		</section>
	<!--================ /내용 =================-->

	<common:footer />
</body>
</html>