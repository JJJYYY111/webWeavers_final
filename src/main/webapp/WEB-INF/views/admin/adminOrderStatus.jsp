<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<common:adminHead />
<title>주문현황</title>
<!-- plugin CSS -->
<link
	href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<!-- 스윗 알랏창  -->
<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>
<link rel="stylesheet"
	href="/resources/admin/adminCSS/orderStatusCSS.css">

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>

<body>
	<!-- =======로딩======== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- =========테마 색상========== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- ==========사이드바============= -->
		<common:adminTopBar />
		<!-- =======사이드바======== -->
		<common:adminSideBar />


		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-7 align-self-center"></div>
				</div>
			</div>
			<div class="container-fluid">
				<!-- 페이지 시작 -->
				<div class="row">
					<div class="col-12">
						<div class="card" style="width: 100%">
							<div class="card-body" style="width: 100%">
								<h1 class="card-title">주문관리</h1>
								<br>
								<div class="row">
									<form style="width: 100%" action="" method="POST">
										<div class="col-12">
											<div class="card">
												<div class="card-body" style="width: 100%">
													<div class="table-responsive">

														<div class='cur-row' style="color: #000000">
															<div>주문상태</div>
															<div class="col-md-8" id="hi">
																<select class="custom-select mr-sm-2" id="serialStatus"
																	name="serialStatus" style="color: black; width: 30%">
																	<option value="">전체</option>
																	<option value="receipt">주문접수</option>
																	<option value="finish">배송완료</option>
																</select>
															</div>


														</div>
														<br>
														<div class='cur-row' style="color: #000000;">
															<div>회원이름</div>
															<div class="col-md-3">
																<input type="text" class="form-control"
																	placeholder="ex) 홍길동" id="memberName" name="memberName"
																	style="width: 70%">
															</div>

															<div>주문날짜</div>
															<div class="col-md-3">
																<input type="date" class="form-control"
																	style="width: 120%" id="serialRegdate"
																	name="serialRegdate">
															</div>

														</div>
														<br>
													</div>
												</div>
											</div>
											<div class="row"></div>

											<div class="row justify-content-center">
												<div class="col-md-6 text-center">
													<button type="button" class="btn btn-primary" id="search">
														<i
															class=" fas
                                                    fa-check"></i>
														검색
													</button>
												</div>
											</div>


										</div>

										<br>
										<div class="table-wrapper"  style="overflow-x: auto;">
											<table id="products" border="1"
												style="table-layout: auto; width: 100%">

												<thead>
													<tr style="color: #000000; width: 100%">
														<th>주문번호</th>
														<th>주문날짜</th>
														<th>주문자</th>
														<th>상품명</th>
														<th>총결제금액</th>
														<th>주문상태</th>
														<th>배송지</th>
													</tr>
												</thead>
												<tbody>

													<script>
														console
																.log('[로그1] 테이블확인');
													</script>
														
													<c:forEach var="data" items="${serialDatas}">

														<tr style="color: #000000;">
															<td class="productName" id="${data.serialPK}">${data.serialPK}</td>
															<td class="productName" id="${data.serialPK}">${data.serialRegdate}</td>
															<td class="productName" id="${data.serialPK}">${data.memberName}</td>
															<td class="productName" id="${data.serialPK}">
															<c:if test="${data.buyProductCnt == 0}">
         																	   ${data.productName}
        															</c:if> 
        												<c:if test="${data.buyProductCnt >= 1}">
         														   ${data.productName} 외 ${data.buyProductCnt}개
       														 </c:if>
       														 </td>
															<td class="productName" id="${data.serialPK}"><span
																class="totalPrice">${data.totalPrice}</span></td>

															<td><select
																class="custom-select mr-sm-2 serialStatus"
																style="color: #000000; width: auto;"
																id="${data.serialPK}">
																	<option value="receipt"
																		${data.serialStatus == 'receipt' ? 'selected' : ''}>접수</option>
																	<option value="finish"
																		${data.serialStatus == 'finish' ? 'selected' : ''}>완료</option>
															</select></td>
															<td class="productName" id="${data.serialPK}">${data.serialDeliveryAddress}</td>
														</tr>
													</c:forEach>
												</tbody>

											</table>

											

											<!-- 모달 -->
											<div id="myModal" class="modal">
												<!-- 모달 내용 -->
												<div class="modal-content" style="color: black;">
													<span class="close">&times;</span>
													<p id="productNameModal">
													<div style="font-size: 30px; white-space: nowrap;">
														주문자: <span id="modalMemberName"></span>
													</div>

													<div style="font-size: 30px; white-space: nowrap;">
														주문날짜: <span id="modalMemberDate"></span>
													</div>

													<br>

													<table id="productsTable" border="1"
														style="width: 100%; table-layout: auto;">
														<tr>
														</tr>
														<tr>
														</tr>
													</table>
													</p>
													<!-- 상품명을 보여줄 요소 -->
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<common:adminMemberNameModal />

	<!-- footer -->
	<footer class="footer text-center text-muted">
		Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
	</footer>
	<!-- All Jquery -->
	<script src="/resources/admin/assets/libs/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script
		src="/resources/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script
		src="/resources/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- apps -->
	<script src="/resources/admin/dist/js/app-style-switcher.js"></script>
	<script src="/resources/admin/dist/js/feather.min.js"></script>
	<!-- slimscrollbar scrollbar JavaScript -->
	<script
		src="/resources/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
	<script src="/resources/admin/assets/extra-libs/sparkline/sparkline.js"></script>
	<script src="/resources/admin/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="/resources/admin/dist/js/custom.min.js"></script>
	<!--This page plugins -->
	<script
		src="/resources/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="/resources/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
	<!-- 페이징처리 -->
	<script src="/resources/admin/dist/js/tablePage.js"></script>
	<!-- 필터검색 -->
	<script src="/resources/admin/js/adminOrderStatus.js"></script>
	
</body>
</html>