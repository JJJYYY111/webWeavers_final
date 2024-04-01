<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - Login</title>
<common:head />
</head>
<body>
	<common:header />
	<common:banner bannerText='아이디 찾기' />

	<!--================ 내용 =================-->
              <div class="col-lg-4">
                          <div class="form-group d-flex flex-row">
                              <div class="input-group">
                                  <div class="input-group-prepend">
                                      <div class="input-group-text">
                                          <i class="fa fa-envelope" aria-hidden="true"></i>
                                      </div>
                                  </div>
                                  <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="휴대전화번호" onfocus="this.placeholder = ''"
                                      onblur="this.placeholder = '휴대전화번호'">
                              </div>
                              <a href="#" class="bbtns">인증번호 받기</a>
                          </div>
                          <p class="text-bottom">You can unsubscribe at any time</p>
                          <div class="br"></div>
</div>	

	<!--================ /내용 =================-->

	<common:footer />
</body>
</html>