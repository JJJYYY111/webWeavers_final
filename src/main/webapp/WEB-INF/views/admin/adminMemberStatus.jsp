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

<title>회원현황</title>
<!-- plugin CSS -->
<link
	href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link rel="stylesheet"
	href="/resources/admin/adminCSS/memberStatusCSS.css">

</head>

<body>
	<!-- =========로딩======== -->
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
		<common:adminTopBar />
		<!-- ========사이드바======== -->
		<common:adminSideBar />
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-7 align-self-center"></div>
				</div>
			</div>
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- basic table -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h1 class="card-title">회원 현황</h1>
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body" style="width: 100%">
												<div class="table-responsive">
													<form style="width: 100%">
														<div class='cur-row' style="color: #000000"
															style="width: 100%">
															<div>회원등급</div>
															<div class="col-md-8">
																<select class="custom-select mr-sm-2" id="gradeName"
																	style="color: black">
																	<option selected>선택</option>
																	<option value="3">브론즈</option>
																	<option value="4">실버</option>
																	<option value="5">골드</option>
																	<option value="1">탈퇴</option>
																	<option value="2">관리자</option>
																</select>
															</div>
														</div>
														<br>
														<div class='cur-row' style="color: #000000;"
															style="width: 100%">
															<div>회원이름</div>
															<div class="col-md-3">
																<input type="text" class="form-control"
																	style="width: 100%" id="memberName" name="memberName"
																	placeholder="ex) 홍길동">
															</div>

															<div>회원아이디</div>
															<div class="col-md-3">
																<input type="text" class="form-control"
																	placeholder="ex) weaveGlow" id="memberID"
																	name="memberID" style="width: 100%">
															</div>

														</div>
													</form>
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


									<table id="memberTable" border="1" style="color: black;">
										<thead>
											<tr>
												<th>ID</th>
												<th>이름</th>
												<th>등급</th>
												<th>생일</th>
												<th>가입일</th>
												<th>email 수신 동의</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="data" items="${memberDatas}">
												<tr
													onclick="location.href='adminMemberStatusChange?memberID=${data.memberID}'"
													style="color: #000000; cursor: pointer;"
													class="memberTable" id="${data.memberID}">
													<td>${data.memberID}</td>
													<td>${data.memberName}</td>
													<td>${data.gradeName}</td>
													<td>${data.memberBirth}</td>
													<td>${data.memberRegdate}</td>
													<td>${data.memberMarketing}</td>
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
			<!-- 필터 검색 회원아이디 입력 유효성 모달 창  -->
			<div class="modal fade" id="alertModal" tabindex="-1" role="dialog"
				aria-labelledby="alertModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="alertModalLabel">경고</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<!-- 경고 메시지를 표시할 곳 -->
							<p id="alertMessage"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 필터 검색 회원 이름 유효성 모달 창  -->
			<div class="modal fade" id="alertModalMemberName" tabindex="-1"
				role="dialog" aria-labelledby="alertModalMemberNameLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="alertModalMemberNameLabel">경고</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<!-- 경고 메시지를 표시할 곳 -->
							<p id="alertMessageMemberName"></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">확인</button>
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
	<!--Wave Effects -->
	<!--Menu sidebar -->
	<script src="/resources/admin/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="/resources/admin/dist/js/custom.min.js"></script>
	<!--This page plugins -->
	<script
		src="/resources/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="/resources/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
	<!--페이징처리 -->
	<script src="/resources/admin/dist/js/tablePage.js"></script>
	<!-- 필터검색 -->
	<script src="/resources/admin/js/adminMemberStatus.js"></script>
</body>
</html>