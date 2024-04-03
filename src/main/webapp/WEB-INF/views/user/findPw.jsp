<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="java.util.ArrayList"%> <%@ taglib
tagdir="/WEB-INF/tags" prefix="common"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <title>WeaveGlow - Login</title>
        <common:head />
    </head>
    <body>
        <common:header />
        <common:banner bannerText="비밀번호 찾기" />

        <!--================ 내용 =================-->

        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title mb-2">인증방법선택</h4>
                        <h7> 회원정보에 등록한 정보와 입력한 정보가 같아야, 회원님의 임시 비밀번호가 전송됩니다.</h7>

                        <ul class="nav nav-tabs nav-justified nav-bordered mb-3">
                            <li class="nav-item">
                                <a href="#home-b2" data-toggle="tab" class="nav-link active">
                                    <i class="mdi mdi-home-variant d-lg-none d-block mr-1"></i>
                                    <span class="d-none d-lg-block" >휴대전화</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="#profile-b2" data-toggle="tab" aria-expanded="false" class="nav-link">
                                    <i class="mdi mdi-account-circle d-lg-none d-block mr-1"></i>
                                    <span class="d-none d-lg-block">이메일</span>
                                </a>
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div class="tab-pane show active" id="home-b2">
                                <div class="col-md-12 form-group">
                                    <div class="col-md-4 label-text">아이디</div>
                                    <input type="text" class="form-control col-md-4" id="memberID-01" name="memberID"
                                        placeholder="아이디" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디'"/>
                                    <div class="col-md-4 label-text"></div>
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="col-md-4 label-text">전화번호</div>
                                    <input type="text" class="form-control col-md-4" id="memberPhone" name="memberPhone"
                                        placeholder="휴대전화번호" onfocus="this.placeholder = ''" onblur="this.placeholder = '휴대전화번호'"/>
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="col-md-5 label-text"></div>
                                    <button id="findPwPhoneBtn" class="btn btn-outline-secondary waves-effect waves-light" type="button" onclick="sendPhonePW()">
                                        <span class="btn-label"><i class="fa fa-mobile"></i></span>보내기
                                    </button>
                                </div>  
                            </div>
                            <div class="tab-pane" id="profile-b2">
                                <div class="col-md-12 form-group">
                                    <div class="col-md-4 label-text">아이디</div>
                                    <input type="text" class="form-control col-md-4" id="memberID-02" name="memberID"
                                        placeholder="아이디" onfocus="this.placeholder = ''" onblur="this.placeholder = '아이디'"/>
                                    <div class="col-md-4 label-text"></div>
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="col-md-4 label-text">이메일</div>
                                    <input type="text" class="form-control col-md-4" id="memberEmail" name="memberEmail"
                                        placeholder="이메일" onfocus="this.placeholder = ''" onblur="this.placeholder = '이메일'"/>
                                </div>
                                <div class="col-md-12 form-group">
                                    <div class="col-md-5 label-text"></div>
                                    <button id="findPwEmailBtn" class="btn btn-outline-secondary waves-effect waves-light" type="button" onclick="sendEmailPW()">
                                        <span class="btn-label"><i class="fa fa-envelope"></i></span>보내기
                                    </button>
                                </div>  
                            </div>
                        </div>
                    </div>
                    <!-- end card-body-->
                </div>
                <!-- end card-->
            </div>
            <!-- end col -->
        </div>
        <!-- end row-->  

        <div id="modal_site"><!-- 모달창 --></div>

        <!--================ /내용 =================-->

        <common:footer />
        <script src="/resources/js/findIdPw.js"></script>

        <script src="/resources/admin/assets/libs/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap tether Core JavaScript -->
        <script src="/resources/admin/assets/libs/popper.js/dist/umd/popper.min.js"></script>
        <script src="/resources/admin/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- apps -->
        <!-- apps -->
        <script src="/resources/admin/dist/js/app-style-switcher.js"></script>
        <script src="/resources/admin/dist/js/feather.min.js"></script>
        <!-- slimscrollbar scrollbar JavaScript -->
        <script src="/resources/admin/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
        <script src="/resources/admin/assets/extra-libs/sparkline/sparkline.js"></script>
        <!--Wave Effects -->
        <!-- themejs -->
        <!--Menu sidebar -->
        <script src="/resources/admin/dist/js/sidebarmenu.js"></script>
        <!--Custom JavaScript -->
        <script src="/resources/admin/dist/js/custom.min.js"></script>
        <!-- This Page JS -->
        <script src="/resources/admin/assets/extra-libs/prism/prism.js"></script>
    </body>
</html>
