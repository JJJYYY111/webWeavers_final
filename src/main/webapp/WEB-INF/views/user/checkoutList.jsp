<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WeaveGlow - CheckoutList</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='구매내역' />

	<!-- ================ 내용 ================= -->
	<div class="comments-area" style="margin-bottom: 50px;">
	
	<c:if test="${fn:length(mapData) <= 0}">구매내역이 없습니다.</c:if>
	<c:if test="${fn:length(mapData) > 0}">
		<c:forEach var='entry' items='${mapData}'>
		
		<div style="border: 5px #7eb1ed6b solid; padding: 20px; margin: 20px 0px 20px 0px" class="comment-list">
		<!-- 주문 기본 정보 -->
		<h4 style="display:inline-block; text-align:left; margin-bottom: 10px;">주문번호: ${entry.key}</h4><br>
		<span>주문일: ${entry.value[0].regdate}</span><br>
		<span style="display:inline-block; margin-bottom: 30px;">배송지: ${entry.value[0].deliveryAddress}</span><br>
		<!-- /주문 기본 정보 -->
		
			<!-- 주문번호별 상품 -->
			<c:forEach var='data' items='${entry.value}'>
			<div class="single-comment justify-content-between d-flex">
				<div class="user justify-content-between d-flex">
					<div class="thumb">
						<a href="/productDetail?ppk=${data.ppk}"><img src="${data.img}" alt="${data.ppk}번 상품사진" style="width: 60px;"></a>
					</div>
					<div class="desc">
						<h5><a href="/productDetail?ppk=${data.ppk}">${data.pname}</a></h5>
						<p class="comment">상품가격: <fmt:formatNumber value="${data.price}" currencyCode="KRW" />원</p>
						<p class="comment">구매수량: ${data.cnt}</p>
					</div>
				</div>
				<div class="reply-btn">
				<c:if test="${data.reviewCheck <= 0}">
					<a style="display:inline;" href="/review?bpk=${data.bpk}" class="btn-reply text-uppercase">리뷰작성</a>
				</c:if>
				<c:if test="${data.reviewCheck > 0}">
					<a style="display:inline;" href="/review?bpk=${data.bpk}" class="btn-reply text-uppercase">리뷰수정</a>
				</c:if>
				</div>
			</div>
			<br><br>
			</c:forEach>
			<!-- /주문번호별 상품 -->
			
		</div>
		</c:forEach>
	</c:if>
	</div>
	<!-- ================ /내용 ================= -->
	
	<common:footer />
</body>
</html>