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
    <meta name="viewport" content="width=device-width, initial-scale=1" , viewport-fit=cover" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png" />
    <title>회원현황</title>
    <!-- This page plugin CSS -->
    <link href="assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="dist/css/style.min.css" rel="stylesheet" />

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
            overflow-y: auto;/
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

        th,
        td {
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
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <header class="topbar" data-navbarbg="skin6">
            <nav class="navbar top-navbar navbar-expand-md">
                <div class="navbar-header" data-logobg="skin6">
                    <!-- This is for the sidebar toggle which is visible on mobile only -->
                    <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                            class="ti-menu ti-close"></i></a>
                    <!-- ============================================================== -->
                    <!-- Logo -->
                    <!-- ============================================================== -->
                    <div class="navbar-brand">
                        <!-- Logo icon -->
                        <a href="index.html">
                            <b class="logo-icon">
                                <!-- Dark Logo icon -->
                                <img src="assets/images/logo_small.png" width=40; alt="homepage" class="dark-logo" />
                                <!-- Light Logo icon -->
                                <img src="assets/imageslogo_small.png" alt="homepage" class="light-logo" />
                            </b>
                            <!--End Logo icon -->
                            <!-- Logo text -->
                            <span class="logo-text">
                                <!-- dark Logo text -->
                                <img src="assets/images/weaveGlow_logo1.png" width=165; alt="homepage"
                                    class="dark-logo" />
                                <!-- Light Logo text -->
                                <img src="assets/images/light-logo 1.png" class="light-logo" alt="homepage" />
                            </span>
                        </a>
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- Toggle which is visible on mobile only -->
                    <!-- ============================================================== -->
                    <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)"
                        data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i
                            class="ti-more"></i></a>
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
                            <div class="dropdown-menu dropdown-menu-left mailbox animated bounceInDown">
                                <ul class="list-style-none">
                                    <li>
                                        <a class="nav-link pt-3 text-center text-dark" href="javascript:void(0);">
                                            <strong>Check all notifications</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </li>
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
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                                <img src="assets/images/weaveglow_logo_small1.jpg" alt="user" class="rounded-circle"
                                    width="40">
                                <span class="ml-2 d-none d-lg-inline-block"><span style="color:black;">Web</span> <span
                                        class="text-dark">weavers</span> <i data-feather="chevron-down" class="svg-icon"
                                        style="color: black;"></i></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-sm-right ">
                                <div class="p-2"> <a href="javascript:void(0)" class="btn btn-sm btn-info"
                                        style="width: 100%;"> Logout
                                    </a> </div>
                            </div>
                        </li>
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
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar" data-sidebarbg="skin6">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar" data-sidebarbg="skin6">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="adminDashboard.html"
                                aria-expanded="false"><i data-feather="home" class="feather-icon"></i><span
                                    class="hide-menu">메인</span></a></li>
                        <li class="list-divider"></li>
                        <li class="nav-small-cap"><span class="hide-menu">Applications</span></li>

                        <li class="sidebar-item"> <a class="sidebar-link" href="adminMemberStatus .html"
                                aria-expanded="false"><i data-feather="tag" class="feather-icon"></i><span
                                    class="hide-menu">회원관리
                                </span></a>
                        </li>
                        <li class="sidebar-item"> <a class="sidebar-link has-arrow" href="javascript:void(0)"
                                aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span
                                    class="hide-menu">상품관리
                                </span></a>
                            <ul aria-expanded="false" class="collapse  first-level base-level-line">
                                <li class="sidebar-item"><a href="adminProductStatus.html" class="sidebar-link">
                                        <span class="hide-menu"> 상품현황
                                        </span></a>
                                </li>
                                <li class="sidebar-item"><a href="adminProductRegistration.html" class="sidebar-link">
                                        <span class="hide-menu"> 상품등록
                                        </span></a>
                                </li>

                            </ul>
                        </li>
                        <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="adminOrderStatus.html"
                                aria-expanded="false"><i data-feather="message-square" class="feather-icon"></i><span
                                    class="hide-menu">주문관리</span></a></li>
                        <li class="sidebar-item"> <a class="sidebar-link has-arrow" href="javascript:void(0)"
                                aria-expanded="false"><i data-feather="file-text" class="feather-icon"></i><span
                                    class="hide-menu">매출관리
                                </span></a>
                            <ul aria-expanded="false" class="collapse  first-level base-level-line">
                                <li class="sidebar-item"><a href="salesStatus.html" class="sidebar-link"><span
                                            class="hide-menu"> 매출현황
                                        </span></a>
                                </li>
                                <li class="sidebar-item"><a href="daliySalesStatus.html" class="sidebar-link"><span
                                            class="hide-menu"> 일별매출
                                        </span></a>
                                </li>
                                <li class="sidebar-item"><a href="monthlySalesStatus.html" class="sidebar-link"><span
                                            class="hide-menu">
                                            월별매출
                                        </span></a>
                                </li>
                                <li class="sidebar-item"><a href="annualSalesStatus.html" class="sidebar-link"><span
                                            class="hide-menu">
                                            연간매출
                                        </span></a>
                                </li>
                            </ul>
                        <li class="list-divider"></li>
                        <li class="sidebar-item"> <a class="sidebar-link sidebar-link" href="authentication-login1.html"
                                aria-expanded="false"><i data-feather="log-out" class="feather-icon"></i><span
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
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
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
                                                                <select class="custom-select mr-sm-2" id="member-grade"
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
                                                                    placeholder="ex) 홍길동">
                                                            </div>
                                                            <div>회원아이디</div>
                                                            <div class="col-md-3">
                                                                <input type="text" class="form-control"
                                                                    placeholder="ex) weaveGlow">
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
                                                <button type="button" class="btn btn-primary" "><i class=" fas
                                                    fa-check"></i>
                                                    검색</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='cur-row' style="color: #000000;">
                                        <div>검색결과</div>
                                        <br>
                                        <table id="products" border="1">

                                            <thead>
                                                <tr>
                                                    <th> ID </th>
                                                    <th>이름</th>
                                                    <th>등급</th>
                                                    <th>생일</th>
                                                    <th>가입일</a></th>
                                                    <th>email 수신 동의</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- for문 돌리기 나중에 -->
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                                <tr style="color: #000000;">
                                                    <td>1</td>
                                                    <!-- 회원을 검색하면 그 회원 수정 페이지로 갈 수 있게 -->
                                                    <td><a href="adminMemberUpdate.html">값받기</a> </td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                    <td><a href="adminMemberUpdate.html">값받기</a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div id="nav">
                                            <!-- </table> -->
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
    </div>
    <!-- ============================================================== -->
    <!-- End Container fluid  -->
    <!-- ============================================================== -->
    <!-- footer -->
    <footer class="footer text-center text-muted">
        Web Wevers <a href="http://koreaitsecurity.net/">WebWevers</a>
    </footer>
    <!-- ============================================================== -->
    <!-- End footer -->
    </div>
    </div>
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <!-- apps -->
    <script src="dist/js/app-style-switcher.js"></script>
    <script src="dist/js/feather.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <!-- themejs -->
    <!--Menu sidebar -->
    <script src="dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="dist/js/custom.min.js"></script>
    <!--This page plugins -->
    <script src="assets/extra-libs/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="dist/js/pages/datatable/datatable-basic.init.js"></script>


    <script src="dist/js/tablePage.js"></script>
</body>

</html>