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
<link rel="icon" type="image/png" sizes="16x16"
	href="/admin/assets/images/favicon.png" />
<title>월별 매출현황</title>
<!-- This page plugin CSS -->
<link
	href="/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="/admin/dist/css/style.min.css" rel="stylesheet" />
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
	/* 웹킷 브라우저에서 기본 스타일 제거 */
	-webkit-appearance: none;
	-moz-appearance: none;
	background: transparent url('path_to_arrow_icon.png') no-repeat right
		center;
	/* 드롭다운 아이콘 설정 */
	padding: 0.375rem 1.75rem 0.375rem 0.75rem;
	/* 내부 여백 설정 */
	border: 1px solid #ced4da;
	/* 테두리 스타일 설정 */
	border-radius: 0.25rem;
	/* 테두리 반경 설정 */
	line-height: 1.5;
	/* 줄 높이 설정 */
	background: url(../../assets/images/custom-select.png) right 1.1rem
		center no-repeat;
}
</style>



<style>
.table-responsive {
	overflow-x: hidden;
	/* 가로 스크롤 숨김 */
	overflow-y: auto;
	/
}
</style>

<style>
.row.justify-content-center {
	display: flex;
	justify-content: center;
	align-items: center;
	/* 가운데 정렬 */
}
</style>

<style>
.membertable {
	border-top: 1px solid #e8eef3;
	/* 위쪽 테두리에 스타일 줌 */
}
</style>

<style>
table {
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed;
	/* 테이블의 너비가 유지되도록 설정 */
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
	overflow-x: auto;
	/* 가로 오버플로우 스크롤 생성 */
	/* max-width: 200px; */
	/* 테이블 셀 내부의 최대 너비 설정 */
	/* overflow: hidden; */
	white-space: nowrap;
}

th {
	background-color: #f2f2f2;
	/* 테이블 헤더의 배경색 설정 */
}

@media screen and (max-width: 768px) {
	/* 화면 너비가 768px 이하일 때 스타일 적용 */
	table {
		font-size: 14px;
		/* 작은 화면에서 글꼴 크기 조정 */
	}
}
</style>

<style>
::-webkit-scrollbar {
	width: 5px;
	/* 스크롤 바의 가로 너비 */
}

/* Firefox 브라우저에 적용되는 스크롤 바 스타일 */
/* Firefox에서는 스크롤 바의 가로 너비만 제어할 수 있습니다. */
/* 세로 스크롤 바는 브라우저의 기본 스타일이 적용됩니다. */
* {
	scrollbar-width: thin;
}

/* IE 및 Edge 브라우저(이전 버전)에 적용되는 스크롤 바 스타일 */
/* Microsoft Edge(Chromium)에서는 사용되지 않습니다. */
*::-ms-scrollbar {
	width: 5px;
	/* 스크롤 바의 가로 너비 */
}
</style>

<style>
::placeholder {
	color: #000000;
	/* Placeholder 색상 변경 */
	font-weight: 100;
}
</style>



<style>
#inlineFormCustomSelect {
	overflow-x: hidden;
	/* 가로 스크롤 숨김 */
	overflow-y: auto;
	/* 세로 스크롤 표시 */
	width: 100%;
	/* 선택 요소의 가로 너비 설정 */
}
</style>

<style>
.row.justify-content-center {
	display: flex;
	justify-content: center;
	align-items: center;
	/* 가운데 정렬 */
}
</style>

<style>
.membertable {
	border-top: 1px solid #e8eef3;
	/* 위쪽 테두리에 스타일 줌 */
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
	/* 상대 위치 지정 */
}

#nav a.active {
	background: #333;
	color: #fff;
}
</style>

</head>

<body>
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- ============================================================== -->
		<common:adminTopBar />
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<common:adminSideBar />
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
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
			<!-- End Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<!-- basic table -->
				<div class="row">
					<!-- column -->
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">Product line Chart</h4>
								<ul class="list-inline text-right">
									<li class="list-inline-item">
										<h5>
											<i class="fa fa-circle mr-1 text-info"></i>저번 달
										</h5>
									</li>
									<li class="list-inline-item">
										<h5>
											<i class="fa fa-circle mr-1 text-cyan"></i>이번 달
										</h5>
									</li>
								</ul>
								<div id="morris-area-chart"></div>
							</div>
						</div>
					</div>
					<!-- column -->

					<!-- column -->
					<div class="col-lg-6 col-md-6 col-sm-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">Donut Charts</h4>
								<div class="row">
									<!-- Donut Chart 1 -->
									<div class="col-lg-6 col-md-6 col-sm-6">
										<div id="morris-donut-chart"></div>
										<p class="text-center">저번 달</p>
									</div>

									<!-- Donut Chart 2 -->
									<div class="col-lg-6 col-md-6 col-sm-6">
										<div id="morris-donut-chart2"></div>
										<p class="text-center">이번 달</p>
									</div>
								</div>
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
										<table id="products" border="1">
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
												<tr style="color: #000000;">
													<td>1</td>
													<td>John Doe</td>
													<td>12</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>2</td>
													<td>Jane Smith</td>
													<td>25</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>3</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>4</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>5</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>6</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>7</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>8</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>9</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>
												<tr style="color: #000000;">
													<td>10</td>
													<td>Michael Johnson</td>
													<td>35</td>
													<td>ddddd</td>
													<td> 10000</td>
													<td>1</td>
												</tr>

											</tbody>
											<tfoot>
												<tr style="color: #000000;">
													<th colspan="5">총금액</th>
													<td>10</td>
												</tr>
											</tfoot>
										</table>
										<div id="nav"></div>
									</div>
								</div>
							</div>
						</div>

						<!-- ============================================================== -->
						<!-- End PAge Content -->
						<!-- ============================================================== -->
						<!-- ============================================================== -->
						<!-- Right sidebar -->
						<!-- ============================================================== -->
						<!-- .right-sidebar -->
						<!-- ============================================================== -->
						<!-- End Right sidebar -->
						<!-- ============================================================== -->
					</div>
				</div>
				<!-- ============================================================== -->
				<!-- End Container fluid  -->
				<!-- ============================================================== -->
				<!-- ============================================================== -->
				<!-- footer -->
				<footer class="footer text-center text-muted">
					Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
				</footer>
				<!-- ============================================================== -->
				<!-- End footer -->
			</div>
			<!-- ============================================================== -->
			<!-- End Page wrapper  -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Wrapper -->
		<!-- ============================================================== -->
		<!-- End Wrapper -->
		<!-- ============================================================== -->
		<!-- All Jquery -->
		<!-- ============================================================== -->
		<script src="/admin/assets/libs/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap tether Core JavaScript -->
		<script src="/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
		<script src="/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- apps -->
		<!-- apps -->
		<script src="/admin/dist/js/app-style-switcher.js"></script>
		<script src="/admin/dist/js/feather.min.js"></script>
		<!-- slimscrollbar scrollbar JavaScript -->
		<script
			src="/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
		<script src="/admin/assets/extra-libs/sparkline/sparkline.js"></script>
		<!--Wave Effects -->
		<!-- themejs -->
		<!--Menu sidebar -->
		<script src="/admin/dist/js/sidebarmenu.js"></script>
		<!--Custom JavaScript -->
		<script src="/admin/dist/js/custom.min.js"></script>
		<!--This page plugins -->
		<script
			src="/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
		<script src="/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
		<!--Morris JavaScript -->
		<script src="/admin/assets/libs/raphael/raphael.min.js"></script>
		<script src="/admin/assets/libs/morris.js/morris.min.js"></script>
		<!-- <script src="dist/js/pages/morris/morris-data.js"></script> -->
		<script src="/admin/dist/js/pages/morris/morris-data copy.js"></script>
</body>

</html>