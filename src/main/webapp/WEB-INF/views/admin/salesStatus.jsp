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
					<div class="col-12 " name="cardOne">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">주문통계내역 조회</h4>
								<hr>
								<form name="profileChangeForm" class=" row login_form"
									action="/admin/adminProductStatus" method="POST"
									id="profileChange_form">
									<!-- <form action="#"> -->
									<div class="col-lg-12 form-body">
										<div class="form-group">
											<div class="row">
												<label class="col-lg-1 text-center"
													style="margin-top: 7px; margin-bottom: 4px;">기간설정</label>
												<div class="col-lg-10">
													<div class="row">
														<div class="col-md-4 text-center">
															<input type="date" class="form-control col-md-12"
																id="registFirstDay" name="FirstDay" placeholder="앞기간설정"
																onfocus="this.placeholder = ''"
																onblur="this.placeholder = '앞기간설정'"
																style="display: inline-block;">
														</div>
														<div class="col-sm-auto text-center">
															<i class="fas fa-window-minimize"></i>
														</div>
														<div class="col-md-4 text-center">
															<input type="date" class="form-control col-md-11"
																id="registLastDay" name="LastDay" placeholder="뒷기간설정"
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
															<!-- DB 연결 필요  -->
															<!-- 선택한 값을 DB에 보낸후 반환된값으로 2차 분류 지정가능 -->
															<select class="custom-select form-control col-md-12"
																id="inlineFormCustomSelect" name="FirstCategory"
																onchange="changeFirstCategory(this.value)">
																<option selected>Category</option>
																<option value="1">스킨케어</option>
																<option value="2">마스크</option>
																<option value="3">삼푸</option>
																<option value="4">수분크림</option>
															</select>
														</div>
														<div class="col-md-2 text-center">
															<select class="custom-select form-control col-md-12"
																id="inlineFormCustomSelect2" name="SecondCategory">
																<option selected>SubCategory</option>
																<option value="1">브론즈</option>
																<option value="2">실버</option>
																<option value="3">골드</option>
																<option value="4">탈퇴</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="form-actions">
											<div class="text-right">
												<button type="submit" class="btn btn-info">검색</button>
												<button type="reset" class="btn btn-dark">리셋</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="col-12 " name="cardTwo">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">상품조회</h4>
								<hr>
								<h6 class="card-subtitle">
									DataTables has most features enabled by default, so all you
									need to do to use it with your own tables is to call the
									construction function:
									<code> $().DataTable();</code>
									. You can refer full documentation from here <a
										href="https://datatables.net/">Datatables</a>
								</h6>
								<div class="table-responsive">
									<table id="zero_config"
										class="table table-striped table-bordered no-wrap">
										<thead>
											<tr>
												<th>PK</th>
												<th>상품명</th>
												<th>카테고리</th>
												<th>서브카테고리</th>
												<th>가격</th>
												<th>수량</th>
												<th>금액</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td>어성초</td>
												<td>스킨</td>
												<td>크림</td>
												<td>43,000</td>
												<td>100</td>
												<td>4,300,000</td>
											</tr>
											<tr>
												<td>2</td>
												<td>화장품 A</td>
												<td>스킨</td>
												<td>크림</td>
												<td>25,000</td>
												<td>50</td>
												<td>1,250,000</td>
											</tr>
											<tr>
												<td>3</td>
												<td>화장품 B</td>
												<td>파운데이션</td>
												<td>리퀴드</td>
												<td>38,000</td>
												<td>25</td>
												<td>950,000</td>
											</tr>
											<tr>
												<td>4</td>
												<td>화장품 C</td>
												<td>마스카라</td>
												<td>마스카라</td>
												<td>22,000</td>
												<td>70</td>
												<td>1,540,000</td>
											</tr>
											<tr>
												<td>5</td>
												<td>화장품 D</td>
												<td>립스틱</td>
												<td>스틱</td>
												<td>45,000</td>
												<td>30</td>
												<td>1,350,000</td>
											</tr>
											<tr>
												<td>6</td>
												<td>화장품 E</td>
												<td>파운데이션</td>
												<td>파우더</td>
												<td>30,000</td>
												<td>40</td>
												<td>1,200,000</td>
											</tr>
											<tr>
												<td>7</td>
												<td>화장품 F</td>
												<td>아이라이너</td>
												<td>펜슬</td>
												<td>18,000</td>
												<td>90</td>
												<td>1,620,000</td>
											</tr>
											<tr>
												<td>8</td>
												<td>화장품 G</td>
												<td>블러셔</td>
												<td>파우더</td>
												<td>36,000</td>
												<td>20</td>
												<td>720,000</td>
											</tr>
											<tr>
												<td>9</td>
												<td>화장품 H</td>
												<td>립글로즈</td>
												<td>틴트</td>
												<td>28,000</td>
												<td>45</td>
												<td>1,260,000</td>
											</tr>
											<tr>
												<td>10</td>
												<td>화장품 I</td>
												<td>아이섀도우</td>
												<td>파우더</td>
												<td>20,000</td>
												<td>80</td>
												<td>1,600,000</td>
											</tr>
											<tr>
												<td>11</td>
												<td>화장품 J</td>
												<td>파운데이션</td>
												<td>크림</td>
												<td>42,000</td>
												<td>30</td>
												<td>1,260,000</td>
											</tr>
											<tr>
												<td>12</td>
												<td>화장품 K</td>
												<td>마스카라</td>
												<td>립</td>
												<td>35,000</td>
												<td>40</td>
												<td>1,400,000</td>
											</tr>
											<tr>
												<td>13</td>
												<td>화장품 L</td>
												<td>아이라이너</td>
												<td>젤</td>
												<td>26,000</td>
												<td>70</td>
												<td>1,820,000</td>
											</tr>
											<tr>
												<td>14</td>
												<td>화장품 M</td>
												<td>블러셔</td>
												<td>크림</td>
												<td>32,000</td>
												<td>50</td>
												<td>1,600,000</td>
											</tr>
											<tr>
												<td>15</td>
												<td>화장품 N</td>
												<td>립스틱</td>
												<td>스틱</td>
												<td>48,000</td>
												<td>25</td>
												<td>1,200,000</td>
											</tr>
											<tr>
												<td>16</td>
												<td>화장품 O</td>
												<td>아이섀도우</td>
												<td>크림</td>
												<td>40,000</td>
												<td>20</td>
												<td>800,000</td>
											</tr>
											<tr>
												<td>17</td>
												<td>화장품 P</td>
												<td>립글로즈</td>
												<td>틴트</td>
												<td>24,000</td>
												<td>70</td>
												<td>1,680,000</td>
											</tr>
											<tr>
												<td>18</td>
												<td>화장품 Q</td>
												<td>아이라이너</td>
												<td>펜슬</td>
												<td>16,000</td>
												<td>60</td>
												<td>960,000</td>
											</tr>
											<tr>
												<td>19</td>
												<td>화장품 R</td>
												<td>로션</td>
												<td>크림</td>
												<td>20,000</td>
												<td>60</td>
												<td>1,200,000</td>
											</tr>
											<tr>
												<td>20</td>
												<td>화장품 S</td>
												<td>마스카라</td>
												<td>마스카라</td>
												<td>28,000</td>
												<td>60</td>
												<td>1,680,000</td>
											</tr>
											<tr>
												<td>21</td>
												<td>화장품 T</td>
												<td>립스틱</td>
												<td>스틱</td>
												<td>50,000</td>
												<td>20</td>
												<td>1,000,000</td>
											</tr>
											<tr>
												<td>22</td>
												<td>화장품 U</td>
												<td>파운데이션</td>
												<td>파우더</td>
												<td>22,000</td>
												<td>60</td>
												<td>1,320,000</td>
											</tr>
											<tr>
												<td>23</td>
												<td>화장품 V</td>
												<td>아이섀도우</td>
												<td>파우더</td>
												<td>28,000</td>
												<td>30</td>
												<td>840,000</td>
											</tr>
											<tr>
												<td>24</td>
												<td>화장품 W</td>
												<td>립글로즈</td>
												<td>틴트</td>
												<td>36,000</td>
												<td>40</td>
												<td>1,440,000</td>
											</tr>
											<tr>
												<td>25</td>
												<td>화장품 X</td>
												<td>아이라이너</td>
												<td>젤</td>
												<td>30,000</td>
												<td>30</td>
												<td>900,000</td>
											</tr>
											<tr>
												<td>26</td>
												<td>화장품 Y</td>
												<td>블러셔</td>
												<td>크림</td>
												<td>42,000</td>
												<td>20</td>
												<td>840,000</td>
											</tr>
											<tr>
												<td>27</td>
												<td>화장품 Z</td>
												<td>마스카라</td>
												<td>립</td>
												<td>32,000</td>
												<td>50</td>
												<td>1,600,000</td>
											</tr>
											<tr>
												<td>28</td>
												<td>화장품 AA</td>
												<td>아이라이너</td>
												<td>펜슬</td>
												<td>18,000</td>
												<td>70</td>
												<td>1,260,000</td>
											</tr>
											<tr>
												<td>29</td>
												<td>화장품 BB</td>
												<td>블러셔</td>
												<td>파우더</td>
												<td>26,000</td>
												<td>40</td>
												<td>1,040,000</td>
											</tr>
											<tr>
												<td>30</td>
												<td>화장품 CC</td>
												<td>립스틱</td>
												<td>스틱</td>
												<td>38,000</td>
												<td>35</td>
												<td>1,330,000</td>
											</tr>
											<tr>
												<td>31</td>
												<td>화장품 DD</td>
												<td>아이섀도우</td>
												<td>크림</td>
												<td>45,000</td>
												<td>30</td>
												<td>1,350,000</td>
											</tr>
											<tr>
												<td>32</td>
												<td>화장품 EE</td>
												<td>파운데이션</td>
												<td>파우더</td>
												<td>20,000</td>
												<td>70</td>
												<td>1,400,000</td>
											</tr>
											<tr>
												<td>33</td>
												<td>화장품 FF</td>
												<td>립글로즈</td>
												<td>틴트</td>
												<td>24,000</td>
												<td>50</td>
												<td>1,200,000</td>
											</tr>
											<tr>
												<td>34</td>
												<td>화장품 GG</td>
												<td>아이라이너</td>
												<td>젤</td>
												<td>32,000</td>
												<td>25</td>
												<td>800,000</td>
											</tr>
											<tr>
												<td>35</td>
												<td>화장품 HH</td>
												<td>블러셔</td>
												<td>크림</td>
												<td>28,000</td>
												<td>50</td>
												<td>1,400,000</td>
											</tr>
											<tr>
												<td>36</td>
												<td>화장품 II</td>
												<td>마스카라</td>
												<td>마스카라</td>
												<td>36,000</td>
												<td>30</td>
												<td>1,080,000</td>
											</tr>
											<tr>
												<td>37</td>
												<td>화장품 JJ</td>
												<td>립스틱</td>
												<td>스틱</td>
												<td>48,000</td>
												<td>20</td>
												<td>960,000</td>
											</tr>
											<tr>
												<td>38</td>
												<td>화장품 KK</td>
												<td>아이섀도우</td>
												<td>파우더</td>
												<td>30,000</td>
												<td>30</td>
												<td>1,500,000</td>
											</tr>
											<tr>
												<td>39</td>
												<td>화장품 LL</td>
												<td>파운데이션</td>
												<td>크림</td>
												<td>42,000</td>
												<td>20</td>
												<td>840,000</td>
											</tr>
											<tr>
												<td>40</td>
												<td>화장품 MM</td>
												<td>립글로즈</td>
												<td>틴트</td>
												<td>30,000</td>
												<td>40</td>
												<td>1,200,000</td>
											</tr>
											<tr>
												<td>41</td>
												<td>화장품 NN</td>
												<td>아이라이너</td>
												<td>펜슬</td>
												<td>25,000</td>
												<td>40</td>
												<td>1,000,000</td>
											</tr>
											<tr>
												<td>42</td>
												<td>화장품 OO</td>
												<td>블러셔</td>
												<td>파우더</td>
												<td>38,000</td>
												<td>25</td>
												<td>950,000</td>
											</tr>
											<tr>
												<td>43</td>
												<td>화장품 PP</td>
												<td>마스카라</td>
												<td>립</td>
												<td>20,000</td>
												<td>50</td>
												<td>1,000,000</td>
											</tr>
											<tr>
												<td>44</td>
												<td>화장품 QQ</td>
												<td>아이라이너</td>
												<td>젤</td>
												<td>36,000</td>
												<td>30</td>
												<td>1,080,000</td>
											</tr>
											<tr>
												<td>45</td>
												<td>화장품 RR</td>
												<td>블러셔</td>
												<td>크림</td>
												<td>24,000</td>
												<td>50</td>
												<td>1,200,000</td>
											</tr>
											<tr>
												<td>46</td>
												<td>화장품 SS</td>
												<td>립스틱</td>
												<td>스틱</td>
												<td>42,000</td>
												<td>20</td>
												<td>840,000</td>
											</tr>
											<tr>
												<td>47</td>
												<td>화장품 TT</td>
												<td>아이섀도우</td>
												<td>파우더</td>
												<td>28,000</td>
												<td>40</td>
												<td>1,120,000</td>
											</tr>
											<tr>
												<td>48</td>
												<td>화장품 UU</td>
												<td>파운데이션</td>
												<td>파우더</td>
												<td>32,000</td>
												<td>35</td>
												<td>1,120,000</td>
											</tr>
											<tr>
												<td>49</td>
												<td>화장품 VV</td>
												<td>립글로즈</td>
												<td>틴트</td>
												<td>46,000</td>
												<td>20</td>
												<td>920,000</td>
											</tr>
											<tr>
												<td>50</td>
												<td>화장품 WW</td>
												<td>아이라이너</td>
												<td>펜슬</td>
												<td>38,000</td>
												<td>25</td>
												<td>950,000</td>
											</tr>

										</tbody>
										<tfoot>
											<tr>
												<th>PK</th>
												<th>상품명</th>
												<th>카테고리</th>
												<th>서브카테고리</th>
												<th>가격</th>
												<th>수량</th>
												<th>금액</th>
											</tr>
										</tfoot>
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
</body>

</html>