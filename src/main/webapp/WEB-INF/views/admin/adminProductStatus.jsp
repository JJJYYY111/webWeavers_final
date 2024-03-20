<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- ============================================================== -->
		<!-- Topbar header - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<header class="topbar" data-navbarbg="skin6">
			<nav class="navbar top-navbar navbar-expand-md">
				<div class="navbar-header" data-logobg="skin6">
					<!-- This is for the sidebar toggle which is visible on mobile only -->
					<a class="nav-toggler waves-effect waves-light d-block d-md-none"
						href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
					<!-- ============================================================== -->
					<!-- Logo -->
					<!-- ============================================================== -->
					<div class="navbar-brand">
						<!-- Logo icon -->
						<a href="index.html"> <b class="logo-icon"> <!-- Dark Logo icon -->
								<img src="/admin/assets/images/logo_small.png" width=40; alt="homepage"
								class="dark-logo" /> <!-- Light Logo icon --> <img
								src="/admin/assets/imageslogo_small.png" alt="homepage"
								class="light-logo" />
						</b> <!--End Logo icon --> <!-- Logo text --> <span class="logo-text">
								<!-- dark Logo text --> <img
								src="/admin/assets/images/weaveGlow_logo1.png" width=165;
								alt="homepage" class="dark-logo" /> <!-- Light Logo text --> <img
								src="/admin/assets/images/light-logo 1.png" class="light-logo"
								alt="homepage" />
						</span>
						</a>
					</div>
					<!-- ============================================================== -->
					<!-- End Logo -->
					<!-- ============================================================== -->
					<!-- ============================================================== -->
					<!-- Toggle which is visible on mobile only -->
					<!-- ============================================================== -->
					<a class="topbartoggler d-block d-md-none waves-effect waves-light"
						href="javascript:void(0)" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation"><i class="ti-more"></i></a>
				</div>
				<!-- ============================================================== -->
				<!-- End Logo -->
				<!-- ============================================================== -->
				<div class="navbar-collapse collapse" id="navbarSupportedContent">
					<!-- ============================================================== -->
					<!-- toggle and nav items -->
					<!-- ============================================================== -->
					<ul class="navbar-nav float-left mr-auto ml-3 pl-1">
						<!-- Notification -->
						<li class="nav-item dropdown">
							<div
								class="dropdown-menu dropdown-menu-left mailbox animated bounceInDown">
								<ul class="list-style-none">
									<li><a class="nav-link pt-3 text-center text-dark"
										href="javascript:void(0);"> <strong>Check all
												notifications</strong> <i class="fa fa-angle-right"></i>
									</a></li>
								</ul>
							</div>
						</li>
					</ul>
					<!-- ============================================================== -->
					<!-- Right side toggle and nav items -->
					<!-- ============================================================== -->
					<ul class="navbar-nav float-right">
						<!-- ============================================================== -->
						<!-- User profile and search -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="assets/images/weaveglow_logo_small1.jpg" alt="user"
								class="rounded-circle" width="40"> <span
								class="ml-2 d-none d-lg-inline-block"><span
									style="color: black;">Web</span> <span class="text-dark">weavers</span>
									<i data-feather="chevron-down" class="svg-icon"
									style="color: black;"></i></span>
						</a>
							<div class="dropdown-menu dropdown-menu-sm-right ">
								<div class="p-2">
									<a href="javascript:void(0)" class="btn btn-sm btn-info"
										style="width: 100%;"> Logout </a>
								</div>
							</div></li>
						<!-- ============================================================== -->
						<!-- User profile and search -->
						<!-- ============================================================== -->
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<aside class="left-sidebar" data-sidebarbg="skin6">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar" data-sidebarbg="skin6">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						<li class="sidebar-item"><a class="sidebar-link sidebar-link"
							href="index.html" aria-expanded="false"><i
								data-feather="home" class="feather-icon"></i><span
								class="hide-menu">메인</span></a></li>
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">Applications</span></li>

						<li class="sidebar-item"><a class="sidebar-link"
							href="ticket-list.html" aria-expanded="false"><i
								data-feather="tag" class="feather-icon"></i><span
								class="hide-menu">회원관리 </span></a></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow"
							href="javascript:void(0)" aria-expanded="false"><i
								data-feather="file-text" class="feather-icon"></i><span
								class="hide-menu">상품관리 </span></a>
							<ul aria-expanded="false"
								class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a
									href="adminProductRegistration.jsp" class="sidebar-link"><span
										class="hide-menu"> 상품등록 </span></a></li>
								<li class="sidebar-item"><a href="adminProductStatus.html"
									class="sidebar-link"><span class="hide-menu"> 상품현황 </span></a>
								</li>
							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link sidebar-link"
							href="app-chat.html" aria-expanded="false"><i
								data-feather="message-square" class="feather-icon"></i><span
								class="hide-menu">주문관리</span></a></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow"
							href="javascript:void(0)" aria-expanded="false"><i
								data-feather="file-text" class="feather-icon"></i><span
								class="hide-menu">매출관리 </span></a>
							<ul aria-expanded="false"
								class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="form-inputs.html"
									class="sidebar-link"><span class="hide-menu"> 일별매출 </span></a>
								</li>
								<li class="sidebar-item"><a href="form-input-grid.html"
									class="sidebar-link"><span class="hide-menu"> 일월매출 </span></a>
								</li>
							</ul>
						<li class="list-divider"></li>
						<li class="sidebar-item"><a class="sidebar-link sidebar-link"
							href="authentication-login1.html" aria-expanded="false"><i
								data-feather="log-out" class="feather-icon"></i><span
								class="hide-menu">로그아웃</span></a></li>
					</ul>
				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
		</aside>
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
										<tbody>
											<tr>
												<td><a href="adminProductUpdate.html?">Tiger Nixon</a></td>
												<td><a href="adminProductUpdate.html">System
														Architect</a></td>
												<td><a href="adminProductUpdate.html?">Edinburgh</a></td>
												<td>2011/04/25</td>
												<td>320,800</td>
											</tr>
										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ============================================================== -->
				<!-- End PAge Content -->
				<!-- ============================================================== -->
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
</body>

</html>