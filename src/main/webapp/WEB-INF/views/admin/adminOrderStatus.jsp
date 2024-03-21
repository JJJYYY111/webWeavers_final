<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="icon" type="/admin/image/png" sizes="16x16"
	href="/admin/assets/images/favicon.png" />
<title>주문현황</title>
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

#myModal {
	position: fixed;
	/* 고정 위치 설정 */
	top: 50%;
	/* 화면 상단에서 절반 위치에 배치 */
	left: 50%;
	/* 화면 왼쪽에서 절반 위치에 배치 */
	transform: translate(-50%, -50%);
	/* 절반씩 이동해서 가운데로 정렬 */
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

<style>
/* 모달 스타일 */
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(157, 157, 157, 0.5);
	/* 배경색을 반투명하게 설정 */
}

/* 모달 내용 스타일 */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 5%;
	border: 1px solid #888;
	width: 60%;
	margin-left: 20%;
	/* 왼쪽 여백을 추가하여 오른쪽으로 이동 */
	box-shadow: 0 4px 8px 0 rgb(146, 145, 145), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	animation-name: animatetop;
	animation-duration: 0.4s;
}

/* 애니메이션 효과 */
@keyframes animatetop {from { top:-300px;
	opacity: 0
}

to {
	top: 0;
	opacity: 1
}

}

/* 닫기 버튼 스타일 */
.close {
	color: #000000;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.close {
	color: #000000;
	font-size: 28px;
	font-weight: bold;
	position: absolute;
	top: 0;
	right: 0;
	padding: 10px;
}

.productName:hover {
	cursor: pointer;
	text-decoration: underline;
	color: rgb(4, 184, 255);
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>

<body>
	<!-- =======로딩======== -->
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
	<!-- =========테마 색상========== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<!-- ==========사이드바============= -->
		<header class="topbar" data-navbarbg="skin6">
			<nav class="navbar top-navbar navbar-expand-md">
				<div class="navbar-header" data-logobg="skin6">
					<!-- This is for the sidebar toggle which is visible on mobile only -->
					<a class="nav-toggler waves-effect waves-light d-block d-md-none"
						href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
					<!-- ========로고========== -->
					<div class="navbar-brand">
						<!-- Logo icon -->
						<a href="index.html"> <b class="logo-icon"> <!-- Dark Logo icon -->
								<img src="/admin/assets/images/logo_small.png" width=40;
								alt="homepage" class="dark-logo" /> <!-- Light Logo icon --> <img
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
					<!-- =============사이드바아이콘==================== -->
					<a class="topbartoggler d-block d-md-none waves-effect waves-light"
						href="javascript:void(0)" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation"><i class="ti-more"></i></a>
				</div>
				<!-- ============================================================== -->
				<div class="navbar-collapse collapse" id="navbarSupportedContent">
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
					<ul class="navbar-nav float-right">
						<!-- =============오른쪽 프로필================== -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="javascript:void(0)"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<img src="/admin/assets/images/weaveglow_logo_small1.jpg" alt="user"
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
					</ul>
				</div>
			</nav>
		</header>
		<!-- =======사이드바======== -->
		<aside class="left-sidebar" data-sidebarbg="skin6">
			<div class="scroll-sidebar" data-sidebarbg="skin6">
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						<li class="sidebar-item"><a class="sidebar-link sidebar-link"
							href="adminDashboard.html" aria-expanded="false"><i
								data-feather="home" class="feather-icon"></i><span
								class="hide-menu">메인</span></a></li>
						<li class="list-divider"></li>
						<li class="nav-small-cap"><span class="hide-menu">Applications</span></li>

						<li class="sidebar-item"><a class="sidebar-link"
							href="adminMemberStatus .html" aria-expanded="false"><i
								data-feather="tag" class="feather-icon"></i><span
								class="hide-menu">회원관리 </span></a></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow"
							href="javascript:void(0)" aria-expanded="false"><i
								data-feather="file-text" class="feather-icon"></i><span
								class="hide-menu">상품관리 </span></a>
							<ul aria-expanded="false"
								class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="adminProductStatus.html"
									class="sidebar-link"> <span class="hide-menu"> 상품현황
									</span></a></li>
								<li class="sidebar-item"><a
									href="adminProductRegistration.html" class="sidebar-link">
										<span class="hide-menu"> 상품등록 </span>
								</a></li>

							</ul></li>
						<li class="sidebar-item"><a class="sidebar-link sidebar-link"
							href="adminOrderStatus.html" aria-expanded="false"><i
								data-feather="message-square" class="feather-icon"></i><span
								class="hide-menu">주문관리</span></a></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow"
							href="javascript:void(0)" aria-expanded="false"><i
								data-feather="file-text" class="feather-icon"></i><span
								class="hide-menu">매출관리 </span></a>
							<ul aria-expanded="false"
								class="collapse  first-level base-level-line">
								<li class="sidebar-item"><a href="salesStatus.html"
									class="sidebar-link"><span class="hide-menu"> 매출현황 </span></a>
								</li>
								<li class="sidebar-item"><a href="daliySalesStatus.html"
									class="sidebar-link"><span class="hide-menu"> 일별매출 </span></a>
								</li>
								<li class="sidebar-item"><a href="monthlySalesStatus.html"
									class="sidebar-link"><span class="hide-menu"> 월별매출 </span></a>
								</li>
							</ul>
						<li class="list-divider"></li>
						<li class="sidebar-item"><a class="sidebar-link sidebar-link"
							href="authentication-login1.html" aria-expanded="false"><i
								data-feather="log-out" class="feather-icon"></i><span
								class="hide-menu">로그아웃</span></a></li>
					</ul>
				</nav>
				<!-- 사이드바 -->
			</div>
		</aside>
		<div class="page-wrapper">
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-7 align-self-center"></div>
				</div>
			</div>
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- 페이지 시작 -->
				<!-- ============================================================== -->
				<div class="row">
					<div class="col-12" >
						<div class="card" style="width: 100%">
							<div class="card-body" style="width: 100%">
								<h1 class="card-title">주문관리</h1>
								<br>
								<div class="row">
									<form style="width: 100%">
										<div class="col-12">
											<div class="card">
												<div class="card-body">
													<div class="table-responsive">
											
														<div class='cur-row' style="color: #000000">
															<div>주문상태</div>
															<div class="col-md-8" id="hi">
																<select class="custom-select mr-sm-2" id="buyProductStatus" name="buyProductStatus"
																	style="color: black; width: 30%">
																	<option value="receipt" >주문접수</option>
																	<option value="finish">배송완료</option>
																</select>
															</div>
														</div>
														<br>
														<div class='cur-row' style="color: #000000;">
															<div>회원이름</div>
															<div class="col-md-3">
																<input type="text" class="form-control"
																	placeholder="ex) 홍길동" id="memberName" name="memberName" style="width: 70%">
															</div>
															<div>회원아이디</div>
															<div class="col-md-3">
																<input type="text" class="form-control"
																	placeholder="ex) weaveGlow" id="memberID" name="memberID" style="width: 70%">
															</div>
														</div>
														<br>
													</div>
												</div>
											</div>
											<div class="row"></div>

											<div class="row justify-content-center">
												<div class="col-md-6 text-center">
													<button type="button" class="btn btn-primary" id="search">
														<i class=" fas
                                                    fa-check"></i>
														검색
													</button>
												</div>
											</div>
											
											
											<!-- 필터검색 -->
											<script>
											$("#search").on("click", function() {
												console.log('들어옴')
												var selectElement = document.getElementById("buyProductStatus"); // select 요소 가져오기
												var buyProductStatus = selectElement.options[selectElement.selectedIndex].value; // 선택된 option의 값 가져오기
												
												console.log(buyProductStatus);
												//var productStatus = document.getElementById("productStatus").value;
												var memberName = document.getElementById("memberName").value;
												
												console.log('로그1'+memberName);
												var memberID= document.getElementById("memberID").value;
												//memberId의 위에서 value를 가져온다.
												console.log('로그2'+memberID);
												
												$.ajax({
													
													type: "POST",
													url: "/admin/searchSerial",
													data: {
                                                        'buyProductStatus': buyProductStatus,
                                                        'memberName': memberName,
                                                       'memberID': memberID
                                                        
                                                    },
                                                    
                                                    dataType: 'json',
                                                    	
                                                    	success:function(datas) {
                                                    		console.log('콘솔'+datas);
                                                    	    var tableHTML = "<table id='products' border='1'>";
                                                    	    tableHTML += "<thead><tr><th>주문번호</th><th>주문날짜</th><th>주문자</th><th>상품명</th><th>총결제금액</th><th>주문상태</th><th>배송지</th></tr></thead>";
                                                    	    tableHTML += "<tbody>";
                                                    	    for (var i = 0; i < datas.length; i++) {
                                                    	        tableHTML += "<tr class='productName' id="+datas[i].serialPK+">";
                                                    	        tableHTML += "<td>" + datas[i].serialPK + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].serialRegdate + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].memberName + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].productName + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].totalPrice + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].buyProductStatus + "</td>";
                                                    	        tableHTML += "<td>" + datas[i].serialDeliveryAddress + "</td>";
                                                    	        tableHTML += "</tr>";
                                                    	    }
                                                    	    tableHTML += "</tbody></table>";
                                                    	    $("#products").html(tableHTML);
                                                    	   
                                                    },
                                                    error: function (error) {
                                                    	
                                                        console.log('에러의 종류:' + error)
                                                    }
												});
												
											
											});
											
											
											</script>
										</div>
									
									<div class='cur-row' style="color: #000000;">
                                        <br>
                                        <div class="table-wrapper">
                                            <table id="products" border="1">
                                                <thead>
                                                    <tr style="color: #000000;">
                                                        <th>주문번호</th>
                                                        <th>주문날짜</th>
                                                        <th>주문자</th>
                                                        <th>상품명</th>
                                                        <th>총결제금액</th>
                                                        <th>주문상태</th>
                                                        <th>배송지</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                               
                                                    <!-- tr이 반복됨-그 tr에 id=${productpd} -->
                                                    <script>console.log('들어옴');</script>
                                                    <script>console.log(`${serialDatas}`);</script>
                                                    <c:forEach var="data" items="${serialDatas}">
                                                    
                                                    <tr style="color: #000000;" class="productName" id="${serialPK}">
                                                        <td>${data.serialPK}</td>
                                                        <td>${data.serialRegdate}</td>
                                                        <td>${data.memberName}</td>
                                                        <td>${data.productName}외
                                                           <c:if test="${fn:length(data.productName) > 1}">${fn:length(data.productName) - 1}</c:if>개</td>
                                                        <td>${data.totalPrice}</td>
                                                        <td>
                                                            <select class="custom-select mr-sm-2"
                                                                id="inlineFormCustomSelect" style="color: #000000;">
                                                                <option value="receipt">접수</option>
                                                                <option value="finish">완료</option>
                                                            </select>
                                                        </td>
                                                        <td>${data.serialDeliveryAddress}</td>
                                                    </tr>
                                                     </c:forEach> 
                                                </tbody>
                                                
                                            </table>
                                            <div id="nav">
                                            </div>
                                          

                                            <!-- 모달 -->
                                            <div id="myModal" class="modal">
                                                <!-- 모달 내용 -->
                                                <div class="modal-content">
                                                    <span class="close">&times;</span>
                                                    <p id="productNameModal">
                                                    
                                                    <table id="products" border="1" style="width: 100%;">
                                                                    <tr style="color: #000000;">
                                                                        <th style="width: 30%;">주문날짜</th>
                                                                        <td style="width: 70%;" id="regdate"></td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                        <th style="width: 30%;">주문자</th>
                                                                        <td id="member"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <th style="width: 30%;">상품명</th>
                                                                        <td id="productName"></td>
                                                                    </tr>                                                  
                                                                    <tr>
                                                                        <th style="width: 30%;">총결제금액</th>
                                                                        <td id="totalprice"></td>
                                                                    </tr>
                                                                </table>
                                                    </p> <!-- 상품명을 보여줄 요소 -->
                                                </div>
                                            </div>

                                            <script>
                                                // 상품명이 클릭되었을 때 모달을 표시하는 함수
                                                
                                                    $(document).on('click','.productName', function () {
                                                        var serialPK = $(this).prop('id');
                                                        var modal = document.getElementById("myModal");
                                                        var productNameModal = document.getElementById("productNameModal");
                                                        console.log('들어옴 :'+serialPK);
                                                        
                                                        $.ajax({
                                                            type: "POST",
                                                            url: "/admin/getOrderList",
                                                            data: {
                                                                'serialPK': serialPK
                                                            },
                                                            dataType: 'json',
                                                            success: function(data) {
                                                                console.log(data);
                                                                $("#regdate").text(data[0].serialRegdate);
                                                                $("#member").text(data[0].memberName);
                                                                for (var i = 0; i < data.length; i++) {
                                                                    var productName = data[i].productName;
                                                                    $("#productName").append("<li>" + productName + "</li>");
                                                                }
                                                                $("#totalprice").text(data[0].totalPrice);
                                                                
                                                                modal.style.display = "block"; // 모달 표시
                                                            },

                                                            error: function (error) {
                                                            	
                                                                console.log('에러의 종류:' + error)
                                                            }


                                                        });
                                                        
                                                      
                                                    });
                                                


                                                // 모달 닫기 버튼에 이벤트 바인딩
                                                document.querySelector('.close').addEventListener('click', function () {
                                                    var modal = document.getElementById("myModal");
                                                    modal.style.display = "none"; // 모달 숨김
                                                });

                                                // 모달 영역 밖을 클릭하면 모달 닫기
                                                window.onclick = function (event) {
                                                    var modal = document.getElementById("myModal");
                                                    if (event.target === modal) {
                                                        modal.style.display = "none"; // 모달 숨김
                                                    }
                                                }
                                            </script>
                                        </div>
                                        </form>
                                    </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
	<!-- ============================================================== -->
	<!-- footer -->
	<footer class="footer text-center text-muted">
		Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
	</footer>
	<!-- End footer -->
	<!-- ============================================================== -->
	<!-- All Jquery -->
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
	<!-- 페이징처리 -->
	<script src="/admin/dist/js/tablePage.js"></script>
</body>
</html>