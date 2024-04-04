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
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
<title>상품등록</title>
<!-- This page plugin CSS -->
<link
	href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
	rel="stylesheet" />
<!-- Custom CSS -->

<!-- 스윗 알랏창  -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet"
	href="/resources/admin/adminCSS/productRegistrationCSS.css">


</head>

<body>
	<!-- =======로딩========= -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- =========테마======== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- ======헤더====== -->
		<common:adminTopBar />
		<!-- ===========사이드바============ -->
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
			<div class="container-fluid" style="margin-top: -50px;">
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h1 class="card-title">상품 등록</h1>
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<div class="table-responsive">

													<form id="myForm" action="/adminProductInsert"
														method="POST" enctype="multipart/form-data"
														onsubmit="return swtBasic()">
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div style="width: 7%;">카테고리</div>
															<div style="margin-right: 1%; width: 100%;">
																<input type='radio' class='category' name='categoryName'
																	value='skin'>스킨/로션 <input type='radio'
																	class='category' name='categoryName' value='cleanging'
																	style="margin-left: 1%;">클렌징 <input
																	type='radio' class='category' name='categoryName'
																	value='mask' style="margin-left: 1%;">마스크/팩
															</div>
														</div>
														<br>
														<div>
															<div class='cur-row'
																style="color: #000000; display: flex;">
																<div style="width: 7%;">세부카테고리</div>
																<div class="sub-category"></div>
																<br>
															</div>
														</div>
														<br>
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div>상품명</div>
															<div class="col-md-5" style="margin-left: 15px;">
																<input type="text" class="form-control"
																	name="productName" placeholder="ex) 촉촉한 스킨" required>
															</div>
														</div>
														<br>
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div>판매가</div>
															<div class="col-md-5" style="margin-left: 15px;">
																<input type="number" min="1" class="form-control"
																	name="productPrice" placeholder="ex) 25,000" required>
															</div>
														</div>
														<br>
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div>판매상태</div>
															<div class="col-md-5">
																<select class="custom-select mr-sm-2"
																	id="registration-salestatus" name="productStatus"
																	style="color: black" required>
																	<option value="1">판매중</option>
																	<option value="2">판매완료</option>
																</select>
															</div>
														</div>
														<br>
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div>재고수량</div>
															<div class="col-md-5">
																<input type="number" min="0" max="9999"
																	class="form-control" name="productQuantity"
																	placeholder="ex) 100" required>
															</div>
														</div>

														<br> <br>
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div>대표이미지</div>
														</div>
														<div class="button">
															<label for="chooseFile"
																style="margin-right: auto; color: black; background-color: lightgray;">
																[이미지 업로드] </label>
														</div>
														<input type="file" id="chooseFile" name="productImage"
															accept="image/*" onchange="loadFile(this)">

														<div class="fileInput">
															<p>파일명 :</p>
															<p id="fileName"></p>
														</div>
														<div class="buttonContainer">

															<div class="image-show" id="image-show"></div>

															<!-- 이미지 삭제 버튼 추가 -->
															<div class="deleteButton" id="deleteButton"
																style="color: black;" onclick="deleteImage()">
																<label
																	style="margin-right: auto; color: black; background-color: lightgray;">[사진등록취소]</label>
															</div>
															<br>

															<div class="float-right" style="height: 50px;">
																<br>

															</div>
														</div>


														<br> <br>
														<div class='cur-row'
															style="color: #000000; display: flex;">
															<div>상세설명</div>
														</div>
														<br>

														<div class="addImage" id="show-image">
															<!-- 이미지 띄울 공간 -->
														</div>
														<input type="file" name="productDetailImage"
															accept="image/*" onchange="DetailloadFile(this)">
														<div class="detailDeleteButton" id="detailDeleteButton"
															style="color: black;" onclick="detailDeleteButton()">
															<br> <label
																style="margin-right: auto; color: black; background-color: lightgray;">[사진등록취소]</label>
														</div>
														<br>
														<div style="text-align: right;">
															<button type="submit" id="button" class="btn btn-primary">
																<i class="fas fa-check"></i> 완료
															</button>
															<button type="button" class="btn btn-primary"
																id="cancelButton">
																<i class="fas fa-check"></i> 취소
															</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- card-body-->
						</div>
						<!--  card-->
					</div>
					<!-- col-->
				</div>
			</div>
		</div>
	</div>
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
	<!--Wave Effects -->
	<!-- themejs -->
	<!--Menu sidebar -->
	<script src="/resources/admin/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="/resources/admin/dist/js/custom.min.js"></script>
	<!--This page plugins -->
	<script
		src="/resources/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="/resources/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
	<!-- 필터검색 -->
	<script src="/resources/admin/js/adminProductRegistration.js"></script>

</body>
</html>