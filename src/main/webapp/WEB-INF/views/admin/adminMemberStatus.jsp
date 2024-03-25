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
    <meta name="viewport" content="width=device-width, initial-scale=1" , viewport-fit="cover" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png" />
    <title>회원현황</title>
    <!-- plugin CSS -->
    <link href="/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="/admin/dist/css/style.min.css" rel="stylesheet" />

    <style>
        .input {
            color: #000000;
            background-color: #ffffff4d;
            border: 3px solid #ffffff;
        }

        form .cur-row {
            display: flex;
            align-items: center;
            gap: 10px;
        }
    </style>

    <style>
        ::placeholder {
            color: #000000;
            font-weight: 100;
        }
    </style>

    <style>
        .table-responsive {
            overflow-x: hidden;
            overflow-y: auto;/
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

        th,
        td {
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


        form {
            width: 500px;
        }

        table {
            border-collapse: collapse;
            margin-bottom: 10px;
        }

        th,
        td {
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
    <!-- =========로딩======== -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        <common:topBar />
        <!-- ========사이드바======== -->
        <common:adminSideBar />
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                    </div>
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
                                            <div class="card-body">
                                                <div class="table-responsive">
                                                    <form>
                                                        <div class='cur-row' style="color:#000000">
                                                            <div>회원등급</div>
                                                            <div class="col-md-8">
                                                                <select class="custom-select mr-sm-2" id="gradePK"
                                                                    style="color: black">
                                                                    <option value="3">브론즈</option>
                                                                    <option value="4">실버</option>
                                                                    <option value="5">골드</option>
                                                                    <option value="1">탈퇴</option>
                                                                    <option value="2">관리자</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <br>
                                                        <div class='cur-row' style="color: #000000;">
                                                            <div>회원이름</div>
                                                            <div class="col-md-3">
                                                                <input type="text" class="form-control"
                                                                   id="memberName" name="memberName" placeholder="ex) 홍길동">
                                                            </div>
                                                            <div>회원아이디</div>
                                                            <div class="col-md-3">
                                                                <input type="text" class="form-control"
                                                                  id="memberID" name="memberID"  placeholder="ex) weaveGlow">
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">

                                        </div>

                                        <div class="row justify-content-center">
                                            <div class="col-md-6 text-center">
                                                <button type="button" class="btn btn-primary" id="search" ><i class=" fas
                                                    fa-check"></i>
                                                    검색</button>
                                            </div>
                                        </div>
                                    </div>
                                    	
											
                                        <table id="member" border="1">
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
                                            <!-- Controller에서 memberDatas로 값 받기 -->
                                               <c:forEach var="data" items="${memberDatas}">
                                               <!-- Controller로 넘어갈 때 a태그 안에 ? 뒤에 "${data.memberID}"  -->
                                                <tr style="color: #000000;" class="memberTable" id="${data.memberID}">
                                                 <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                 <!-- 회원 업데이트 컨트롤러 생기면 href 수정하기  -->
                                                    <td><a href="adminMemberUpdate.jsp">${data.memberID}</a></td>
                                                    <td><a href="adminMemberUpdate.jsp">${data.memberName}</a> </td>
                                                    <td><a href="adminMemberUpdate.jsp">${data.gradeName}</a></td>
                                                    <td><a href="adminMemberUpdate.jsp">${data.memberBirth}</a></td>
                                                    <td><a href="adminMemberUpdate.jsp">${data.memberRegdate}</a></td>
                                                    <td><a href="adminMemberUpdate.jsp">${data.memberMarketing}</a></td>
                                                </tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
                                        <div id="nav">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> <!-- 제일 큰 div-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <footer class="footer text-center text-muted">
        Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
    </footer>
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
    <script src="/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="/admin/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <!--Menu sidebar -->
    <script src="/admin/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="/admin/dist/js/custom.min.js"></script>
    <!--This page plugins -->
    <script src="/admin/assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="/admin/dist/js/pages/datatable/datatable-basic.init.js"></script>
 	<!--페이징처리 -->
    <script src="/admin/dist/js/tablePage.js"></script>
    
    <!-- 필터검색 -->
											<script>
											$("#search").on("click", function() {
												console.log('들어옴')
												var selectElement = document.getElementById("gradePK"); // select 요소 가져오기
												var gradePK = selectElement.options[selectElement.selectedIndex].value; // 선택된 option의 값 가져오기
												
												//console.log(memberGrade);
												var memberName = document.getElementById("memberName").value;
												
												console.log('로그1'+memberName);
												var memberID= document.getElementById("memberID").value;
												//memberId의 위에서 value를 가져온다.
												console.log('로그2'+memberID);
												
												$.ajax({
													
													type: "POST",
													url: "/admin/memberSearch",
													data: {
                                                        'gradePK': gradePK,
                                                        'memberName': memberName,
                                                       'memberID': memberID
                                                        
                                                    },
                                                    
                                                    dataType: 'json',
                                                    	
                                                    	success:function(datas) {
                                                    		console.log('콘솔'+datas);
                                                    	    var tableHTML = "<table id='search' border='1'>";
                                                    	    tableHTML += "<thead><tr><th>회원ID</th><th>회원이름</th><th>회원등급</th><th>회원생일</th><th>가입일</th><th>이메일수신동의</th></tr></thead>";
                                                    	    tableHTML += "<tbody>";
                                                    	    for (var i = 0; i < datas.length; i++) {
                                                    	    	 <!-- 회원 업데이트 컨트롤러 생기면 href 수정하기  -->
                                                    	        tableHTML += "<tr class='memberTable' id="+datas[i].memberID+">";
                                                    	        tableHTML += "<td><a href=adminMemberUpdate.jsp>" + datas[i].memberID + "</a></td>";
                                                    	        tableHTML += "<td><a href=adminMemberUpdate.jsp>" + datas[i].memberName + "</a></td>";
                                                    	        tableHTML += "<td><a href=adminMemberUpdate.jsp>" + datas[i].gradeName + "</a></td>";
                                                    	        tableHTML += "<td><a href=adminMemberUpdate.jsp>" + datas[i].memberBirth + "</a></td>";
                                                    	        tableHTML += "<td><a href=adminMemberUpdate.jsp>" + datas[i].memberRegdate + "</a></td>";
                                                    	        tableHTML += "<td><a href=adminMemberUpdate.jsp>" + datas[i].memberMarketing + "</a></td>";
                                                    	        tableHTML += "</tr>";
                                                    	    }
                                                    	    tableHTML += "</tbody></table>";
                                                    	    $("#memberTable").html(tableHTML);
                                                    	   
                                                    },
                                                    error: function (error) {
                                                    	
                                                        console.log('에러의 종류:' + error)
                                                    }
												});
												
											
											});
											
											
											</script>
</body>
</html>