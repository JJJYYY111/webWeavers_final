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
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/admin/assets/images/favicon.png" />
<title>상품목록현황</title>
<!-- This page plugin CSS -->
<link
	href="/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="/admin/dist/css/style.min.css" rel="stylesheet" />

<style>
#zero_config .sorting_1 {
	background-color: rgb(255, 255, 255) !important;
	color: black !important;
}

.odd {
	background-color: rgb(255, 255, 255) !important;
}
</style>
</head>

<body>
	<!-- ======로딩========= -->
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
		<common:topBar />
		<!-- =======사이드바 ======-->
		<common:adminSideBar />
		
		
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-7 align-self-center">
              </h1> 
					</div>
				</div>
			</div>
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h1 class="card-title">상품 현황</h1>
								<br>
								
								<div class="table-responsive">
									<table id="zero_config"
										class="table table-striped table-bordered no-wrap"
										style="background-color: rgb(255, 255, 255) !important; color: black !important;">
										<thead>
											<tr>
												<th class="sorting_asc" style="background-color: #f2f2f2;">상품코드</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">상품명</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">상품가격</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">수량</th>
												<th class="sorting_asc" style="background-color: #f2f2f2;">판매상태</th>
											</tr>
										</thead>
										
										<script>
										console.log('[로그1] 들어오는지 확인');
										</script>
										
										<tbody>
										
										<script>
										console.log('[로그2]데이터 확인 '+ `${productDatas}`);
										</script>
										
										<c:forEach var="data" items="${productDatas}">
										
											<tr onclick="location.href='adminProductStatusChange?productPK=${data.productPK}'" style="cursor: pointer;">
											<script>
										console.log('[로그3] forEach문 들어갔는지 확인');
										</script>
										
											<!-- 상품 수정 컨트롤러 생기면 href 수정하기  -->
												<td>${data.productPK}</td>
												<td>${data.productName}</td>
												<td>${data.productPrice}</td>
												<td>${data.productQuantity}</td>
												
												<c:choose>
												<c:when test="${data.productStatus==1}">
												<td>판매중</td>
													
													</c:when>
													<c:when test="${data.productStatus==0}">
													<td>판매중단</td>
													</c:when>
												</c:choose> 
												
											</tr>
											</c:forEach>
										</tbody>
									</table>
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
	<script src="/admin/assets/libs/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script src="/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- apps -->
	<script src="/admin/dist/js/app-style-switcher.js"></script>
	<script src="/admin/dist/js/feather.min.js"></script>
	<!-- slimscrollbar scrollbar JavaScript -->
	<script
		src="/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
	<script src="/admin/assets/extra-libs/sparkline/sparkline.js"></script>
	<!-- themejs -->
	<!--Menu sidebar -->
	<script src="/admin/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="/admin/dist/js/custom.min.js"></script>
	<!--This page plugins -->
	<script
		src="/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
	<script src="/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
</body>
</html>