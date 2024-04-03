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

<form id=joinForm action="/user/joinForm" method="POST">
<input type="hidden" value="${memberDTO.memberID}" name="memberID"> 
<input type="hidden" value="${memberDTO.memberPassword}" name="memberPassword"> 
<input type="hidden" value="${memberDTO.memberName}" name="memberName"> 
<input type="hidden" value="${memberDTO.memberBirth}" name="memberBirth"> 
<input type="hidden" value="${memberDTO.memberPhone}" name="memberPhone"> 
<input type="hidden" value="${memberDTO.memberNickname}" name="memberNickname"> 
<input type="hidden" value="${memberDTO.memberEmail}" name="memberEmail"> 


  </form>


<script>
window.onload = function() {
    document.getElementById("joinForm").submit();
};
		    	
console.log('콘솔창');
</script>
</body>
</html>