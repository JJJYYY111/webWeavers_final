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
    <title>메인페이지</title>
    <!-- This page plugin CSS -->
    <link href="/resources/admin/assets/extra-libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link rel="stylesheet" href="/resources/admin/adminCSS/dashBoardCSS.css">	
</head>

<body>
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>;
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- ============================================================== -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        <!-- ============================================================== -->
        <common:adminTopBar />
        <!-- ========사이드바========== -->
        <common:adminSideBar />
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-7 align-self-center">
                        <h3 class="page-title text-truncate text-dark font-weight-medium mb-1">안녕하세요! 관리자님</h3>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item"><a href="adminDashboard">좋은하루입니다.</a>
                                    </li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                    <div class="col-5 align-self-center">
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <div class="container-fluid">
                <div class="ml-4 card-group" style="max-width: 96%;">
                    <div class="card border-right">
                        <div class="card-body" style="max-width: 96%;">
                            <div class=" d-flex d-lg-flex d-md-block align-items-center justify-content-center">
                                <div style="text-align: center">
                                    <h6 class="text-muted font-weight-normal mb-0 w-100 text-truncate"
                                        style="line-height: 2.5">
                                        이번달 총 매출액
                                    </h6>

                                    <h2 class="text-dark mb-1 w-100 text-truncate font-weight-medium"><sup
                                            class="set-doller">&nbsp;₩</sup>&nbsp;18,306</h2>
                                </div>
                                <div class="ml-auto mt-md-3 mt-lg-0">
                                    <span class="opacity-7 text-muted"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card border-right">
                        <div class="card-body">
                            <div class="d-flex d-lg-flex d-md-block align-items-center justify-content-center">
                                <div style="text-align: center">
                                    <h6 class="text-muted font-weight-normal mb-0 w-100 text-truncate"
                                        style="line-height: 2.5">
                                        일일 총 매출액
                                    </h6>

                                    <h2 class="text-dark mb-1 w-100 text-truncate font-weight-medium"><sup
                                            class="set-doller">&nbsp;₩</sup>&nbsp;18,306</h2>
                                </div>
                                <div class="ml-auto mt-md-3 mt-lg-0">
                                    <span class="opacity-7 text-muted"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card border-right">
                        <div class="card-body">
                            <div class="d-flex d-lg-flex d-md-block align-items-center justify-content-center">
                                <div style="text-align: center">
                                    <h6 class="text-muted font-weight-normal mb-0 w-100 text-truncate"
                                        style="line-height: 2.5">
                                        일일 방문자수
                                    </h6>

                                    <h2 class="text-dark mb-1 w-100 text-truncate font-weight-medium"><sup
                                            class="set-doller"></sup>&nbsp;1000명</h2>
                                </div>
                                <div class="ml-auto mt-md-3 mt-lg-0">
                                    <span class="opacity-7 text-muted"></span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <!-- column -->
                    <div class="col-lg-6">
                        <div class="card h-90">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">매출현황</h4>
                                    <div>
                                        <canvas id="bar-chart" height="150"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card h-90">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">기간별 매출현황</h4>
                                    <ul class="list-inline text-right">
                                        <li class="list-inline-item">
                                            <h5>
                                                <button
                                                    style="border: 0; border-color: paleturquoise; background-color: rgb(255, 255, 255); outline: none; text-decoration: none;"
                                                    onclick="this.style.textDecoration = 'underline'">
                                                    <i class="fa fa-circle mr-1 text-info"></i>월 매출
                                                </button>
                                            </h5>
                                        </li>
                                        <li class="list-inline-item">
                                            <h5>
                                                <button
                                                    style="border: 0; border-color: paleturquoise; background-color: rgb(255, 255, 255); outline: none; text-decoration: none;"
                                                    onclick="this.style.textDecoration = 'underline'">
                                                    <i class="fa fa-circle mr-1 text-cyan"></i>일 매출
                                                </button>
                                            </h5>
                                        </li>
                                    </ul>
                                    <div id="morris-area-chart" style="width: 100%; height: auto;"></div>
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
    <script src="/resources/admin/dist/js/pages/morris/morris-data.js"></script>
    <script src="/resources/admin/assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="/resources/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="/resources/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <!-- apps -->
    <script src="/resources/admin/dist/js/app-style-switcher.js"></script>
    <script src="/resources/admin/dist/js/feather.min.js"></script>
    <script src="/resources/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="/resources/admin/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="/resources/admin/dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <script src="/resources/admin/assets/extra-libs/c3/d3.min.js"></script>
    <script src="/resources/admin/assets/extra-libs/c3/c3.min.js"></script>
    <script src="/resources/admin/assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="/resources/admin/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="/resources/admin/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="/resources/admin/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="/resources/admin/dist/js/pages/dashboards/dashboard1.min.js"></script>
    <!-- Chart JS -->
    <script src="/resources/admin/dist/js/pages/chartjs/chartjs.init copy.js"></script>
    <script src="/resources/admin/assets/libs/chart.js/dist/Chart.min.js"></script>
    <!--Morris JavaScript -->
    <script src="/resources/admin/assets/libs/raphael/raphael.min.js"></script>
    <script src="/resources/admin/assets/libs/morris.js/morris.min.js"></script>
    <script src="/resources/admin/dist/js/pages/morris/morris-data copy.js"></script>

</body>

</html>