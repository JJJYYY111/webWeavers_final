<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Cart</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='장바구니' />

	<!-- ================ 내용 ================= -->
	<section class="cart_area">
		<div class="container">
			<div class="cart_inner">
				<div class="table-responsive" id="orderList">
					<table class="table" id="cart" style="text-align: right;">
						<colgroup>
							<col width="60%" />
							<col width="13.3333%" />
							<col width="13.3333%" />
							<col width="13.3333%" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" style="text-align: left;">Product</th>
								<th scope="col">Price</th>
								<th scope="col">Quantity</th>
								<th scope="col">Total</th>
							</tr>
						</thead>
						<tbody>
							<!-- 장바구니 목록 출력 -->
							<c:if test="${fn:length(cdatas) <= 0}"><br><br><h4 style="text-align:center;">장바구니가 비었습니다.</h4><br><br></c:if>
							<c:if test="${fn:length(cdatas) > 0}">
								<c:forEach var='data' items='${cdatas}'>
									<tr>
										<td style="text-align: left;">
											<div class="media">
												<div class="d-flex">
													<a href="/productDetail?ppk=${data.ppk}"><img src="${data.img}" alt="${data.ppk}번 상품사진" style="width: 150px;"></a>
												</div>
												<div class="media-body">
													<a href="/productDetail?ppk=${data.ppk}" id="cartProduct"><p>${data.pname}</p></a>
												</div>
											</div>
										</td>
										<td>
											<h5 id="cartProductPrice_${data.ppk}"><fmt:formatNumber value="${data.price}" currencyCode="KRW" />원</h5>
										</td>
										<!-- 장바구니 수량 변경 -->
										<td style="padding-left: 3rem; padding-right: 0;">
											<div class="product_count">
												<input type="text" name="cartCnt" id="qty_${data.ppk}" maxlength="12" value="${data.cnt}" title="Quantity:" class="input-text qty"
													onchange="updateQuantity('${data.ppk}', ${data.price});" readonly>
												<button onclick="updateQuantity('${data.ppk}', '1', ${data.price})" class="increase items-count" type="button">
													<i class="lnr lnr-chevron-up"></i>
												</button>
												<button onclick="updateQuantity('${data.ppk}', '0', ${data.price})" class="reduced items-count" type="button">
													<i class="lnr lnr-chevron-down"></i>
												</button>
											</div>
										</td>
										<!-- /장바구니 수량 변경 -->
										<td>
											<h5>
												<span class="productPrice" id="total_${data.ppk}"><fmt:formatNumber value="${data.price * data.cnt}" currencyCode="KRW" /></span>원
											</h5>
										</td>
										<td>
											<input type="button" name="close" id="close" style="display: none;">
											<label for="close"> 
											<a href="/cartDelete?ppk=${data.ppk}"><img src="img/close.png" alt="닫기버튼" style="width: 20px; height: 20px"></a>
											</label>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<!-- /장바구니 목록 출력 -->
							<!-- 합계 -->
							<tr class="bottom_button">
								<td></td>
								<td>
									<h5>subtotal</h5>
								</td>
								<td></td>
								<td>
									<h5>
										<span id="totalPrice"></span>원
									</h5>
								</td>
							</tr>
							<!-- /합계 -->
							<tr class="out_button_area">
								<td class="d-none-l"></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<div class="checkout_btn_inner d-flex align-items-center">
										<a class="gray_btn" href="/productList">쇼핑하기</a> 
										<a class="gray_btn ml-2" href="/checkout">구매하기</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>
	<!-- ================ /내용 ================= -->
<script src="js/cart.js"></script>
	<common:footer />
</body>
</html>
