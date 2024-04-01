<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1"
	, viewport-fit=cover " />
<meta name="description" content="" />
<meta name="author" content="" />
<!-- Favicon icon -->
<link rel="icon" type="/resources/image/png" sizes="16x16"
	href="/resources/admin/assets/images/favicon.png" />
<title>월별 매출현황</title>
<!-- This page plugin CSS -->
<link
	href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="/resources/admin/dist/css/style.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="/resources/admin/adminCSS/monthlySalesCSS.css">	

</head>

<body>
	<!-- ============================================================== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- ============================================================== -->
		<common:adminTopBar />
		<!-- ============================================================== -->
		<common:adminSideBar />
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-7 align-self-center">
						<h4
							class="page-title text-truncate text-dark font-weight-medium mb-1">월별
							매출 현황</h4>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb m-0 p-0">
									<li class="breadcrumb-item"><a href="/admin/index.jsp"
										class="text-muted">Home</a></li>
									<li class="breadcrumb-item text-muted active"
										aria-current="page">Library</li>
								</ol>
							</nav>
						</div>
					</div>
				</div>
			</div>
			<!-- ============================================================== -->
			<div class="container-fluid">
				<div class="row">
					<!-- column -->
					<div class="col-lg-12 col-md-12 col-sm-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">Product line Chart</h4>
								<ul class="list-inline text-right">
									<li class="list-inline-item">
										<h5>
											<i class="fa fa-circle mr-1 text-info"></i> 매출(원) 
										</h5>
									</li>
								</ul>
								<div id="morris-area-chart"></div>
							</div>
						</div>
					</div>
					<div class="col-12 " name="monthCardTwo">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">당월 상품 top 10 리스트</h4>
								<hr>
								<br>
								<div class='cur-row' style="color: #000000;">
									<br>
									<div class="table-wrapper" name="productList">
										<table id="products" border="1" style="table-layout:auto;">
											<thead>
												<tr style="color: #000000;">
													<th >PK</th>
													<th>상품명</th>
													<th>카테고리</th>
													<th>가격</th>
													<th>수량</th>
													<th>금액</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="data" items="${productDatas}">
													<tr>
														<td>${data.productPK}</td>
														<td>${data.productName}</td>
														<td>${data.categoryName}</td>
														<td>${data.productPrice}</td>
														<td>${data.totalCnt}</td>
														<td>${data.totalPrice}</td>
													</tr>
													<c:set var="totalPrice" value="${totalPrice + data.totalPrice}" />
												</c:forEach>
												<tr style="color: #000000;">
													<th colspan="5">총금액</th>
													<td>${totalPrice}</td>
												</tr>
											</tbody>
										</table>
										<div id="nav"></div>
									</div>
								</div>
							</div>
						</div>
</div>
					</div>
				</div>
				<!-- footer -->
				<footer class="footer text-center text-muted">
					Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
				</footer>
			</div>
		</div>
		<!-- All Jquery -->
		<script src="/resources/admin/assets/libs/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap tether Core JavaScript -->
		<script src="/resources/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
		<script src="/resources/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- apps -->
		<!-- apps -->
		<script src="/resources/admin/dist/js/app-style-switcher.js"></script>
		<script src="/resources/admin/dist/js/feather.min.js"></script>
		<!-- slimscrollbar scrollbar JavaScript -->
		<script
			src="/resources/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
		<script src="/resources/admin/assets/extra-libs/sparkline/sparkline.js"></script>
		<!--Menu sidebar -->
		<script src="/resources/admin/dist/js/sidebarmenu.js"></script>
		<!--Custom JavaScript -->
		<script src="/resources/admin/dist/js/custom.min.js"></script>
		<!--This page plugins -->
		<script
			src="/resources/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
		<script src="/resources/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
		<!--Morris JavaScript -->
		<script src="/resources/admin/assets/libs/raphael/raphael.min.js"></script>
		<script src="/resources/admin/assets/libs/morris.js/morris.min.js"></script>
		<script src="/resources/admin/dist/js/pages/morris/morris-data copy.js"></script>
</body>

</html>