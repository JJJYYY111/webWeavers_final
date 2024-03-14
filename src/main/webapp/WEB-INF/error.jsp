<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> WeaveGlow - error </title>
<common:head />
</head>
<body>
	<common:header />
	
	<!-- ================ 내용 ================= -->	
	<br></br>
	 <div class="col-md-9" style="margin: 0px auto; padding: 50px 280px;">
        <div class="blog_post">
        	<div style ="text-align: center"><img src="img/WgError.png" alt="에러 이미지"></div>
            <div class="blog_details">
                <p style = "text-align: center; font-size:20px; font-weight : bold;"> 죄송합니다.
                현재 찾을 수 없는 페이지를 요청 하셨습니다.</p>
                <br></br>
                <p style = "text-align: center; font-size:16px;"> 존재하지 않는 주소를 입력하셨거나,<br> 
                요청하신 페이지의 주소가 변경, 삭제되어 찾을 수 없습니다..</p>
                <br></br>
                <h2 style = "text-align: center;">
                <a onclick='history.go(-1)' class="button button-blog">이전으로</a>
                <a href="main.do" class="button button-blog">메인으로</a>
                </h2>
            </div>
        </div>
    </div>
	<!-- ================ /내용 ================= -->	 
	 
</body>
</html>