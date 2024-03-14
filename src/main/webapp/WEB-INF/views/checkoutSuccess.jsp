<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>WeaveGlow - CheckoutSuccess</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='주문내역' />

	<!--================ 내용 =================-->
	<section class="order_details section-margin--small">
		<div class="container">
			<p class="text-center billing-alert">상품을 구매해주셔서 감사합니다.</p>
			<div class="row mb-5">
				<div class="col-md-6 col-xl-6 mb-6 mb-xl-0">
					<div class="confirmation-card">
						<h3 class="billing-title">주문 정보</h3>
						<table class="order-rable">
							<tr>
								<td>주문 번호</td>
								<td>: &nbsp; ${bdatas[0].spk}</td>
							</tr>
							<tr>
								<td>주문 날짜</td>
								<td>: &nbsp; ${bdatas[0].regdate}</td>
							</tr>
							<tr>
								<td>합계</td>
								<td>: &nbsp; <span id="totalPrice2"></span>원</td>
							</tr>
							<tr>
								<td>결제 방식</td>
								<td>: &nbsp; 체크 카드</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="col-md-6 col-xl-6 mb-6 mb-xl-0">
					<div class="confirmation-card" style="height: 100%;">
						<h3 class="billing-title">상품 배송주소</h3>
						<table class="order-rable">
							<colgroup>
								<col width="18.8888%" />
								<col width="81.1112%" />
							</colgroup>
							<tr>
								<td>우편번호</td>
								<td>: <span>${aDTO.zonecode} </span></td>
							</tr>
							<tr>
								<td>도로명주소</td>
								<td>: <span>${aDTO.roadaddress} </span></td>
							</tr>
							<tr>
								<td>상세주소</td>
								<td>: <span>${aDTO.detail} </span></td>
							</tr>
							<tr>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="order_details_table">
				<h2>상품 상세정보</h2>
				<div class="table-responsive" id="orderList">
					<table class="table">
						<colgroup>
							<col width="40%" />
							<col width="20%" />
							<col width="20%" />
							<col width="20%" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col">상품명</th>
								<th scope="col">상품가격</th>
								<th scope="col">상품수량</th>
								<th scope="col">합계</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(bdatas) <= 0}">결제한 상품이 없습니다.</c:if>
							<c:if test="${fn:length(bdatas) > 0}">
								<c:forEach var='data' items='${bdatas}'>
									<tr>
										<td>
											<p>${data.pname}</p>
										</td>
										<td>
											<p><fmt:formatNumber value="${data.price}" currencyCode="KRW" />원</p>
										</td>
										<td>
											<p>${data.cnt}</p>
										</td>
										<td>
											<p><span class="productPrice"><fmt:formatNumber value="${data.price*data.cnt}" currencyCode="KRW" /></span>원</p>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td>
									<h4>총 결제 금액</h4>
								</td>
								<td>
									<h5></h5>
								</td>
								<td>
									<h5></h5>
								</td>
								<td>
									<h4><span id="totalPrice"></span>원</h4>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="bottom-right box" style="display: flex; justify-content: flex-end; margin-top: 30px;">
					<a href="/checkoutList" class="button button-blog">구매내역</a>
					<a href="/main" class="button button-blog">메인으로</a>
				</div>
			</div>
		</div>
	</section>
	<div class="container h-50"></div>
	<!--================ /내용 =================-->

	<common:footer />
</body>
</html>