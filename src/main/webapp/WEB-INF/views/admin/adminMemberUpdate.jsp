<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="/admin/assets/images/favicon.png">
    <title>Adminmart Template - The Ultimate Multipurpose admin template</title>
    <!-- Custom CSS -->
    <link href="/admin/dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
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
        <common:topBar />
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
                        <h4 class="page-title text-truncate text-dark font-weight-medium mb-1">회원 수정 페이지</h4>
                        <div class="d-flex align-items-center">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb m-0 p-0">
                                    <li class="breadcrumb-item"><a href="/admin/index.jsp" class="text-muted">Home</a></li>
                                    <li class="breadcrumb-item text-muted active" aria-current="page">Library</li>
                                </ol>
                            </nav>
                        </div>
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
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">회원정보수정</h4>
                                <hr>
                                <form name="profileChangeForm" class="col-lg-12 row login_form" action="/admin/memberUpdate"
                                    method="POST" id="profileChange_form">
                                    <!-- 개인정보수정 -->
                                    <!-- 3.12 자세한 내용 수정예정 column명  -->
                                    <div class="col-lg-12">
                                        <div class="login_form_inner register_form_inner">
                                            <h3>회원정보</h3>
                                            <hr>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">아이디</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="text" class="form-control col-md-12"
                                                                    id="registMid" name="memberId" placeholder="아이디 입력"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '아이디 입력'"
                                                                    style="display: inline-block;" value="${sessionMid}"
                                                                    disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-3 label-text"></div>
                                                <span class="col-md-9" id="idCheck"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">비밀번호</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="password" class="form-control col-md-12"
                                                                    id="registMpw" name="memberPassword"
                                                                    placeholder="비밀번호 입력"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '비밀번호 입력'"
                                                                    style="display: inline-block;"
                                                                    value="${memberPassword}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9" id="pwCheck"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">비밀번호 확인</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="password" class="form-control col-md-12"
                                                                    id="confirmMpw" name="confirmMemberpasswoed"
                                                                    placeholder="비밀번호 확인"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '비밀번호 확인'"
                                                                    style="display: inline-block;"
                                                                    value="${memberPassword}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9" id="confirmPwCheck"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">이름</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="text" class="form-control col-md-12"
                                                                    id="registName" name="name" placeholder="이름 입력"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '이름 입력'"
                                                                    style="display: inline-block;" value="${name}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">생년월일</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="date" class="form-control col-md-12"
                                                                    id="registBirth" name="birth" placeholder="생년월일"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '생년월일'"
                                                                    style="display: inline-block;" value="${birth}"
                                                                    disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">전화번호</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="text" class="form-control col-md-12"
                                                                    id="registPhone" name="phone" placeholder="전화번호 입력"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '전화번호 입력'"
                                                                    style="display: inline-block;" value="${phone}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9" id="phoneCheck"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">닉네임</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="text" class="form-control col-md-12"
                                                                    id="registNickname" name="nickname"
                                                                    placeholder="닉네임 입력" onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = '닉네임 입력'"
                                                                    style="display: inline-block;" value="${nickname}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9" id="nickCheck"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">이메일</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <input type="text" class="form-control col-md-12"
                                                                    id="registEmail" name="email" placeholder="e-mail"
                                                                    onfocus="this.placeholder = ''"
                                                                    onblur="this.placeholder = 'e-mail'"
                                                                    style="display: inline-block;" value="${email}">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-12"
                                                style="text-align: left; font-size: 13px; padding: 3px">
                                                <div class="col-md-4 label-text"></div>
                                                <span class="col-md-9" id="emailCheck"></span>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <label class="col-lg-2"
                                                        style="margin-top: 7px; margin-bottom: 4px;">회원등급</label>
                                                    <div class="col-lg-10">
                                                        <div class="row">
                                                            <div class="col-lg-10">
                                                                <select class="custom-select mr-sm-2"
                                                                    id="inlineFormCustomSelect">
                                                                    <option selected>선택</option>
                                                                    <option value="1">브론즈</option>
                                                                    <option value="2">실버</option>
                                                                    <option value="3">골드</option>
                                                                    <option value="4">탈퇴</option>
                                                                    <option value="5">관리자</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="text-right">
                                                <button type="submit" class="btn btn-info">완료</button>
                                                <button type="button" class="btn btn-dark">취소</button>
                                                <button type="reset" class="btn btn-outline-dark float-left">리셋</button>
                                            </div>                                        
                                        </div>
                                        <!-- <div class="col-md-12 form-group">
                                            <button type="submit" value="submit"
                                                class="button button-register w-100">확인</button>
                                        </div> -->
                                    </div>
                            </div>
                            <!-- 개인정보수정 -->
                            </form>
                        </div>
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
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- footer -->
        <!-- ============================================================== -->
        <footer class="footer text-center text-muted">
            All Rights Reserved by Adminmart. Designed and Developed by <a href="https://wrappixel.com">WrapPixel</a>.
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
    <script src="/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="/admin/assets/extra-libs/sparkline/sparkline.js"></script>
    <!--Wave Effects -->
    <!-- themejs -->
    <!--Menu sidebar -->
    <script src="/admin/dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="/admin/dist/js/custom.min.js"></script>
</body>

</html>