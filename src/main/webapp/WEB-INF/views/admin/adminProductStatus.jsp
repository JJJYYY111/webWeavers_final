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
<title>상품목록현황</title>
<!-- This page plugin CSS -->
<link
	href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link rel="stylesheet" href="/resources/admin/adminCSS/productStatusCSS.css">    

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
		<common:adminTopBar />
		<!-- =======사이드바 ====== -->
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
												<th class="sorting_asc" style="background-color: #f2f2f2;">상품번호</th>
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
										
												<td>${data.productPK}</td>
												<td>${data.productName}</td>
												
												  <td><fmt:formatNumber type="currency" value="${data.productPrice}" /></td>
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
	<script src="/resources/admin/assets/libs/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script src="/resources/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script src="/resources/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- apps -->
	<script src="/resources/admin/dist/js/app-style-switcher.js"></script>
	<script src="/resources/admin/dist/js/feather.min.js"></script>
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
</body>
</html>