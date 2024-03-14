<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html lang="ko">
<head>
<title>WeaveGlow - Checkout</title>
<common:head />
</head>
<body>
	<!-- 모달 주소 출력-->
	<div class="modal fade" id="modalboxSelectAll" tabindex="-1"
		aria-labelledby="modalSelectAllTitle" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalSelectAllTitle">배송지 변경</h5>
				</div>
				<div class="modal-body" id="addressList">
					<div>
						<button class="btn btn-primary" 
							data-bs-toggle="modal" data-bs-target="#modalboxInsert"
							data-bs-toggle="modal" data-bs-dismiss="modal" onclick="modalInsertEmpty()">배송지 추가</button>
						<br>
						<br>
					</div>
					<table class="table" id="cart" style="text-align: right;">
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" onclick="selectAllSubmit()" data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 모달 주소 추가 -->

	<div class="modal fade" id="modalboxInsert" tabindex="-1"
		aria-labelledby="modalInsertTitle" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalInsertTitle">배송지 추가</h5>
				</div>
				<div class="modal-body" id="abc">
					<br>
					<button class="btn btn-primary" onclick="addAddressInsert()">주소 찾기</button>
					<br>
					<div>
						<br> 배송지명 &nbsp;<input type="text" class="addressModalInput" id="modalInsert_aname"> <br>
						<br> 우편번호 &nbsp;<input type="text" class="addressModalInput" id="modalInsert_zonecode" readonly> <br>
						<br> 지번주소 &nbsp;<input type="text" class="addressModalInput" id="modalInsert_jibunAddress"> <br>
						<br> 도로명주소 &nbsp;<input type="text" class="addressModalInput" id="modalInsert_roadAddress" readonly> <br>
						<br> 상세주소 &nbsp;<input type="text" class="addressModalInput" id="modalInsert_detail"> <br>
						<br>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-target="#modalboxSelectAll" data-bs-toggle="modal"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" onclick="return addSubmit()"
						data-bs-target="#modalboxSelectAll" data-bs-toggle="modal"
						data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 모달 주소 변경 -->

	<div class="modal fade" id="modalboxUpdate" tabindex="-1"
		aria-labelledby="modalUpdateTitle" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalUpdateTitle">배송지 수정</h5>
				</div>
				<div class="modal-body" id="zzz">
					<br>
					<button class="btn btn-primary" onclick="addAddressUpdate()">주소 찾기</button>
					<br>
					<div>
						<input type="hidden" id="modalUpdate_apk">
						<br>
						배송지명 &nbsp;<input type="text" class="addressModalInput" id="modalUpdate_aname"> <br><br> 
						우편번호 &nbsp;<input type="text" class="addressModalInput" id="modalUpdate_zonecode" readonly> <br><br> 
						지번주소 &nbsp;<input type="text" class="addressModalInput" id="modalUpdate_jibunAddress"> <br><br> 
						도로명주소 &nbsp;<input type="text" class="addressModalInput" id="modalUpdate_roadAddress" readonly> <br><br> 
						상세주소 &nbsp;<input type="text" class="addressModalInput" id="modalUpdate_detail"> <br><br>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-target="#modalboxSelectAll" data-bs-toggle="modal"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary"
						onclick="return changeSubmit()" data-bs-target="#modalboxSelectAll"
						data-bs-toggle="modal" data-bs-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 모달 주소 삭제  -->
	
	<div class="modal fade" id="modalboxDelete" tabindex="-1"
		aria-labelledby="modalDeleteTitle" aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalDeleteTitle">배송지 삭제</h5>
				</div>
				<div class="modal-body">
					<h2>정말 삭제하시겠습니까?</h2><br>
					<h3 class="billing-title">
						배송지 : <span id="modalDelete_aname"></span>
					</h3>
					<input type="hidden" id="modalDelete_apk">
					<br>
					<button type="button" class="btn btn-secondary"
						data-bs-target="#modalboxSelectAll" data-bs-toggle="modal"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary"
						onclick="deleteSubmit()" data-bs-target="#modalboxSelectAll"
						data-bs-toggle="modal" data-bs-dismiss="modal">삭제</button>
				</div>
			</div>
		</div>
	</div>

	<common:header />
	<common:banner bannerText='상품결제' />



	<!-- ================ 내용 ================= -->
	<section class="checkout_area section-margin--small">
		<div class="container">
			<div class="billing_details">
				<div class="row">
					<form class="row contact_form" id="checkoutForm" onsubmit="return checkoutSubmit()" action="/checkoutSuccess"
						method="post" style="margin: auto;">
						<!-- 주문자 정보 -->
						<div class="col-lg-7">
							<h3>Billing Details</h3>
							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control checkoutInfo" id="checkoutName"
									name="memberName" placeholder="이름" value="${name}" required readonly>
							</div>

							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control checkoutInfo" id="checkoutPhone"
									name="memberPhone" placeholder="연락처" value="${phone}" required readonly>
							</div>

							<div class="col-md-12 form-group p_star">
								<input type="text" class="form-control checkoutInfo" id="checkoutEmail"
									name="memberEmail" placeholder="이메일" value="${email}" required readonly>
							</div>

							<div class="col-md-12 form-group">
								<div class="creat_account">
								<c:if test="${marketing > 0}">
									<input type="checkbox" id="f-option3" name="memberMarketing" checked>
									<label for="f-option3">이메일로 영수증을 받으시려면 체크 해주세요</label>
								</c:if>
								<c:if test="${marketing == 0}">
									<input type="checkbox" id="f-option3" name="memberMarketing">
									<label for="f-option3">이메일로 영수증을 받으시려면 체크 해주세요</label>
								</c:if>
								</div>
							</div>
							
							<!-- 주소 검색 -->
							<div class="col-md-10 form-group">
								<div class="input-group">
									<input type="text" class="form-control" id="checkout_zonecode"
										name="checkout_zonecode" placeholder="우편번호"
										style="display: inline-block; background-color: #fff" readonly>
									<div class="input-group-append">
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal" data-bs-target="#modalboxSelectAll"
											onclick="deleteList(); getAddressList();">배송지 선택</button>
									</div>
								</div>
							</div>
							<div class="col-md-10 form-group">
								<input type="text" class="form-control"
									id="checkout_roadAddress" name="addressZonecode"
									placeholder="도로명주소" 
									style="display: inline-block; background-color: #fff" readonly>
							</div>
							<div class="col-md-10 form-group">
								<input type="text" class="form-control"
									id="checkout_jibunAddress" name="addressJibun"
									placeholder="지번주소" 
									style="display: inline-block; background-color: #fff" readonly>
							</div>
							<div class="col-md-10 form-group">
								<input type="text" class="form-control" id="checkout_detail"
									name="addressDetail" placeholder="상세주소" style="display: inline-block; background-color: #fff" readonly>
							</div>
							<input type="hidden" class="form-control" id="apk" name="addressPK">
							<input type="hidden" class="form-control" id="aname" name="addressName">
							<!-- /주소 검색 -->
						</div>
						<!-- /주문자 정보 -->
						<!-- 결제 정보 -->
						<div class="col-lg-5">
							<div class="order_box">
								<h2>주문 내역</h2>
								<ul class="list" id="orderList">
									<li style="font-weight: bold;">상품<span class="last" style="float: right;">합계</span></li>
									<c:if test="${fn:length(cdatas) <= 0}">상품이 없습니다.</c:if>
									<c:if test="${fn:length(cdatas) > 0}">
										<c:forEach var='data' items='${cdatas}'>
											<li>
											<span style="width: 70%; display: inline-block;">${data.pname} x ${data.cnt}</span> 
											<span class="last" style="float: right;"><span class="productPrice"><fmt:formatNumber value="${data.price*data.cnt}" currencyCode="KRW" /></span>원</span>
											</li>
										</c:forEach>
									</c:if>
								</ul>
								<ul class="list list_2">
									<li style="font-weight: bold;">총 금액 <span class="last" style="float: right;"><span id="totalPrice"></span>원</span></li>
								</ul>
								<!-- 추후에 구현할 예정부분 -->
								<div class="payment_item">
									<div class="radion_btn">
										<input type="radio" id="f-option5" name="selector"><label for="f-option5">Check payments</label>
										<div class="check"></div>
									</div>
									<p>Please send a check to Store Name, Store Street, Store
										Town, Store State / County, Store Postcode.</p>
								</div>
								<div class="payment_item active">
									<div class="radion_btn">
										<input type="radio" id="f-option6" name="selector">
										<label for="f-option6">Paypal</label>
										<img src="img/product/card.jpg" alt="결제카드 이미지">
										<div class="check"></div>
									</div>
									<p>Pay via PayPal; you can pay with your credit card if you
										don’t have a PayPal account.</p>
								</div>
								<div class="creat_account">
									<input type="checkbox" id="f-option4" name="selector" required>
									<label for="f-option4">결제 정보 확인</label>
								</div>
								<div class="text-center">
									<button type="submit" class="button button-paypal">Proceed to Paypal</button>
								</div>
							</div>
						</div>
						<!-- 결제 정보 -->
					</form>
					
				</div>
			</div>
		</div>
	</section>
	<!-- ================ /내용 ================= -->
	<common:footer />
	<script src="js/addressModal.js"></script>
</body>
</html>