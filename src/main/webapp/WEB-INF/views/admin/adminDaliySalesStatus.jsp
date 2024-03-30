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
<title>일별 매출현황</title>
<!-- This page plugin CSS -->
<link
	href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="/resources/admin/dist/css/style.min.css" rel="stylesheet" />
<!-- This Page CSS -->
<!-- 3.17일 추가 -->
<link href="/resources/admin/assets/libs/morris.js/morris.css" rel="stylesheet">
<style>
.input {
	color: #000000;
	background-color: #ffffff4d;
	/* 원하는 배경색으로 변경 */
	border: 3px solid #ffffff;
}

form .cur-row {
	display: flex;
	align-items: center;
	gap: 10px;
}
</style>



<style>
.custom {
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	background: transparent url('path_to_arrow_icon.png') no-repeat right
		center;
	padding: 0.375rem 1.75rem 0.375rem 0.75rem;
	border: 1px solid #ced4da;
	border-radius: 0.25rem;
	line-height: 1.5;
	background: url(../../assets/images/custom-select.png) right 1.1rem
		center no-repeat;
}
</style>



<style>
.table-responsive {
	overflow-x: hidden;
	overflow-y: auto;
}
</style>

<style>
.row.justify-content-center {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>

<style>
.membertable {
	border-top: 1px solid #e8eef3;
}
</style>

<style>
table {
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
	overflow-x: auto;
	white-space: nowrap;
}

th {
	background-color: #f2f2f2;
}

@media screen and (max-width: 768px) {
	table {
		font-size: 14px;
	}
}
</style>

<style>
::-webkit-scrollbar {
	width: 5px;
}

* {
	scrollbar-width: thin;
}

*::-ms-scrollbar {
	width: 5px;
}
</style>

<style>
::placeholder {
	color: #000000;
	font-weight: 100;
}
</style>



<style>
#inlineFormCustomSelect {
	overflow-x: hidden;
	overflow-y: auto;
	width: 100%;
}
</style>

<style>
.row.justify-content-center {
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>

<style>
.membertable {
	border-top: 1px solid #e8eef3;
}

form {
	width: 500px;
}

table {
	border-collapse: collapse;
	margin-bottom: 10px;
}

th, td {
	padding: 3px 10px;
}

.off-screen {
	display: none;
}

#nav {
	width: 500px;
	text-align: center;
	margin: 0 auto;
}

#nav a {
	display: inline-block;
	padding: 3px 5px;
	margin-right: 10px;
	font-family: Tahoma;
	background: #ffffff;
	color: #000;
	text-decoration: none;
	position: relative;
}

#nav a.active {
	background: #333;
	color: #fff;
}
</style>

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
							class="page-title text-truncate text-dark font-weight-medium mb-1">일별
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
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">Line Chart</h4>
								<ul class="list-inline text-right">
									<li class="list-inline-item">
										<h5>
											<i class="fa fa-circle mr-1 text-info"></i>어제
										</h5>
									</li>
									<li class="list-inline-item">
										<h5>
											<i class="fa fa-circle mr-1 text-cyan"></i>오늘
										</h5>
									</li>
								</ul>
								<div id="morris-line-chart"></div>
							</div>
						</div>
					</div>
					<!-- column -->
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">Donut Charts</h4>
								<div class="row">
									<!-- Donut Chart 1 -->
									<div class="col-lg-6 col-md-6 col-sm-6">
										<div id="morris-donut-chart"></div>
										<p class="text-center">어제</p>
									</div>

									<!-- Donut Chart 2 -->
									<div class="col-lg-6 col-md-6 col-sm-6">
										<div id="morris-donut-chart2"></div>
										<p class="text-center">오늘</p>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="col-12 " name="daliyCardTwo">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">당일 상품 top 10 리스트</h4>
								<hr>
								<br>
								<div class='cur-row' style="color: #000000;">
									<br>
									<div class="table-wrapper" name="productList">
										<table id="products" border="1" style="table-layout:auto;">
											<thead>
												<tr style="color: #000000;">
													<th>PK</th>
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