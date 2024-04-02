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
        <common:banner bannerText="아이디 찾기" />

        <!--================ 내용 =================-->
        <div class="container">
            <div class="tracking_box_inner">
                <form
                    class="row tracking_form"
                    action="/findIDService"
                    method="post"
                    novalidate="novalidate"
                >
                    <div class="col-md-8form-group">
                        회원정보에 등록한 휴대전화 번호와 입력한 휴대전화 번호가
                        같아야, 인증번호를 받을 수 있습니다.
                    </div>
                    <div class="col-md-8 form-group">
                        <div class="col-md-4 label-text">이름</div>
                        <input
                            type="text"
                            class="form-control col-md-8"
                            id="findName"
                            name="memberName"
                            placeholder="이름"
                            onfocus="this.placeholder = ''"
                            onblur="this.placeholder = '이름'"
                        />
                    </div>
                    <div class="col-md-8 form-group">
                        <div class="col-md-4 label-text">전화번호</div>
                        <input
                            type="text"
                            class="form-control col-md-6"
                            id="findPhone"
                            name="memberPhone"
                            placeholder="휴대전화번호"
                            onfocus="this.placeholder = ''"
                            onblur="this.placeholder = '휴대전화번호'"
                        />
                        <a
                            class="col-md-4"
                            style="float: right"
                            onclick="smsService('findPhone')"
                            value="본인인증서비스요청"
                        >
                            <i class="ti-mobile"></i>
                        </a>
                    </div>
                    <div class="col-md-8 form-group" style="margin: 0">
                        <div class="col-md-4 label-text">인증번호 확인</div>
                        <input
                            type="text"
                            class="form-control col-md-8"
                            id="certificationNum"
                            name="certificationNum"
                            placeholder="인증번호 입력"
                            onfocus="this.placeholder = ''"
                            onblur="this.placeholder = '인증번호 입력'"
                            style="display: inline-block"
                            required
                        />
                    </div>
                    <div
                        class="col-md-8"
                        style="text-align: left; font-size: 13px; padding: 3px"
                    >
                        <div class="col-md-4 label-text"></div>
                        <span class="col-md-8" id="confirmSmsNumCheck">
                            <!-- 유효성 확인 문구 innerText -->
                        </span>
                    </div>

                    <div class="col-md-4 form-group">
                        <button
                            type="submit"
                            value="submit"
                            class="button button-tracking"
                        >
                            다음
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <!--================ /내용 =================-->

        <common:footer />
        <script src="/resources/js/register.js"></script>
    </body>
</html>
