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
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="assets/images/favicon.png" />
<title>매출 관리</title>
<!-- This page plugin CSS -->
<link
	href="/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="/admin/dist/css/style.min.css" rel="stylesheet" />
<style>
.ck.ck-editor {
	max-width: 700px;
}

.ck-editor__editable {
	min-height: 300px;
}
</style>

<style>
.input {
	color: #000000;
	background-color: #ffffff4d;
	/* 원하는 배경색으로 변경 */
	border: 3px solid #ffffff;
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
.table-responsive {
	overflow-x: hidden;
	/* 가로 스크롤 숨김 */
	overflow-y: auto;
	/
}

.card-body-select {

        border-radius: 5px; /* 테두리의 둥근 정도를 지정 */
    padding: 20px; /* 내부 여백을 추가하여 테두리 내용과의 간격을 조정 */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
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
						<!-- <h1 class="page-title text-truncate text-dark font-weight-medium mb-1" >
                상품 목록
              </h1> -->
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
					<div class="col-12 " name="cardTwo">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">주문통계내역 조회</h4>
								<hr>
									<!-- <form action="#"> -->
									<div class="col-lg-12 form-body">
										<div class="card-body-select" >
											<div class="form-group">
												<div class="row">
													<label class="col-lg-1 text-center"
														style="margin-top: 7px; margin-bottom: 4px;">기간설정</label>
													<div class="col-lg-10">
														<div class="row">
															<div class="col-md-4 text-center">
																<input type="date" class="form-control col-md-12"
																	id="registFirstDay" name="startDate"
																	placeholder="앞기간설정" onfocus="this.placeholder = ''"
																	onblur="this.placeholder = '앞기간설정'"
																	style="display: inline-block;">
															</div>
															<div class="col-sm-auto text-center">
																<i class="fas fa-window-minimize"></i>
															</div>
															<div class="col-md-4 text-center">
																<input type="date" class="form-control col-md-11"
																	id="registLastDay" name="endDate" placeholder="뒷기간설정"
																	onfocus="this.placeholder = ''"
																	onblur="this.placeholder = '뒷기간설정'"
																	style="display: inline-block;">
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="row">
													<label class="col-lg-1 text-center"
														style="margin-top: 7px; margin-bottom: 4px;">카테고리</label>
													<div class="col-lg-11">
														<div class="row">
															<div class="col-md-2 text-center">
																<select class="custom-select form-control col-md-12"
																	id="categorySelect" name="categoryName"
																	onchange="changeFirstCategory()">
																	<option selected value="0">Category</option>
																	<option value="1">스킨케어</option>
																	<option value="2">클렌징</option>
																	<option value="3">마스크팩</option>
																</select>
															</div>
															<div class="col-md-2 text-center">
																<select class="custom-select form-control col-md-12"
																	id="subCategorySelect" name="subCategoryName">
																	<option selected value="0">SubCategory</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<br><hr><br>
										<div class="form-actions">
											<div class="text-right">
												<button type="button" id="searchButton" class="btn btn-info">검색</button>
												<button type="reset" class="btn btn-dark">리셋</button>
											</div>
										</div>
									</div>
									
								<h4 class="card-title">상품조회</h4>
								<hr>
								<div class="table-responsive">
									<table id="zero_config"
										class="table table-striped table-bordered no-wrap">
										<thead>
											<tr>
												<th class="sorting_asc" style="background-color: #f2f2f2;">PK</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">상품명</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">카테고리</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">서브카테고리</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">가격</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">수량</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">금액</th>
											</tr>
										</thead>

										<script>
										/* console.log('[로그2]데이터 확인 '+ ${productSaleDatas}); */
										</script>

										<tbody id="salesSearchdatas">
											<c:forEach var="data" items="${productSaleDatas}">
												<tr>
													<!-- 상품 수정 컨트롤러 생기면 href 수정하기  -->
													<td>${data.productPK}</td>
													<td>${data.productName}</td>
													<td>${data.categoryName}</td>
													<td>${data.subCategoryName}</td>
													<td>${data.productPrice}</td>
													<td>${data.totalCnt}</td>
													<td>${data.totalPrice}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
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
				<!-- ============================================================== -->
				<footer class="footer text-center text-muted">
					Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
				</footer>
				<!-- ============================================================== -->
				<!-- End footer -->
				<!-- ============================================================== -->
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





		<!-- 카테고리 선택 임시 js 파일 -->
		<!-- <script src="/admin/js/categorySelect.js"></script> -->
		<script src="/admin/js/categorySelect2.js"></script>
</body>

</html>