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
                    name="sendID"
                    action="/sendID"
                    method="post"
                    novalidate="novalidate"
                >
                    <div class="col-md-12 form-group">
                        회원정보에 등록한 휴대전화 번호와 입력한 휴대전화 번호가
                        같아야, 문자로 회원님의 아이디가 전송됩니다.
                    </div>
                    <div class="col-md-12 form-group">
                        <div class="col-md-4 label-text">이름</div>
                        <input
                            type="text"
                            class="form-control col-md-4"
                            id="findName"
                            name="memberName"
                            placeholder="이름"
                            onfocus="this.placeholder = ''"
                            onblur="this.placeholder = '이름'"
                        />
                        <div class="col-md-4 label-text"></div>
                    </div>
                    <div class="col-md-12 form-group">
                        <div class="col-md-4 label-text">전화번호</div>
                        <input
                            type="text"
                            class="form-control col-md-4"
                            id="findPhone"
                            name="memberPhone"
                            placeholder="휴대전화번호"
                            onfocus="this.placeholder = ''"
                            onblur="this.placeholder = '휴대전화번호'"
                        />
                    </div>

                    <div class="col-md-12 form-group">
                        <div class="col-md-5 label-text"></div>
                        <button
                            type="button"
                            class="button button-tracking col-md-2"
                            id="findIdBtn"
                        >
                            보내기
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <div id="modal_site">
        </div>

        <!--================ /내용 =================-->

        <common:footer />
        <script src="/resources/js/register.js"></script>
    </body>
</html>
