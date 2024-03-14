<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib prefix="star" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>WeaveGlow - review</title>
<common:head />
</head>
<body>
	<common:header />

	<common:banner bannerText='리뷰작성' />

	<!-- ================ 내용 ================= -->
	<div class="offset-lg-1 col-lg-10">

		<div class="comment-form">
		<!-- 리뷰를 작성하지 않은 상품일 경우 작성하기 버튼 -->
			<c:if test="${data.rpk == 0}"> 
				<form id="reviewForm" onsubmit="submitForm(event)"
					action="/reviewInsert" method="POST" enctype="multipart/form-data"> 								
					
			</c:if>
		<!-- 리뷰를 작성한 상품일 경우 작성하기 버튼 -->
			<c:if test="${data.rpk != 0}">
				<form id="reviewForm" onsubmit="submitForm(event)"
					action="/reviewUpdate" method="POST" enctype="multipart/form-data">								
			</c:if>
				<a href="productDetail.do?ppk=${data.ppk}"> <img
					class="card-img" src="${data.pimg}" alt="구매한 상품 이미지"></a><br> 
				<br>
				<h3>${data.pname}</h3> <!-- 리뷰 작성할 구매한 상품 이름 -->
				<br><br>				
				<input type="hidden" name="reviewPK" value="${data.rpk}">
				<input type="hidden" name="buyProductPK" value="${data.bpk}">
				<div class="form-group">
					<div class="form-group form-inline">
					
					<!-- 구매한 상품 리뷰에 등록할 이미지 -->
					<c:if test="${empty data.img}">
						<img id="preview" style="max-width: 200%; max-height: 200px;"
							class="img-fluid" src="img/default.jpg" alt="업로드할 이미지"> 
					</c:if>
					<c:if test="${not empty data.img}">
						<img id="preview" style="max-width: 200%; max-height: 200px;"
							class="img-fluid" src="uploadimg/${data.img}" alt="업로드할 이미지">
					</c:if>
							<input type="file" 
							id="uploadimg" name="file" onchange="readURL(this);" value="${data.img}" accept=".png, .jpeg, .jpg"> 
						<input type="hidden" id="prevImg" name="prevImg" value="${data.img}"> <!-- 이미지 미리보기 -->
						
						<input type="hidden" name="reviewScope" id="scope" value="">
						<c:if test="${data.scope == 0}">
						<star:star id="myStarRating" defaultRating="5" />	<!-- 별점이 등록되지 않았으면 기본값은 별5개 -->
						</c:if>
						<c:if test="${data.scope > 0}">			
						<star:star id="myStarRating" defaultRating="${data.scope}" /> <!-- 등록된 별점을 받아와서 출력 -->
						</c:if>
						<!-- 리뷰 작성내용 출력 -->
						<textarea class="form-control mb-10" rows="5" name="reviewContent"
							id="reviewMessage" placeholder="리뷰 내용"
							onfocus="this.placeholder =''"
							
						onblur="this.placeholder = 'Message'">${data.content}</textarea>
					</div>
					<div id="messageError" class="error"></div>
				</div>


				<div style="display: flex; justify-content: flex-end;">
					<button type="submit"
						class="button button-postComment button--active"	
						style="margin-right: 20px">완 료</button>		<!-- 완료 버튼을 누르면 리뷰리스트 페이지로 이동 -->
							<a href="javascript:history.back();"									
								class="button button-postComment button--active" 
								style="margin-left: 20px"> 취 소</a>			<!-- 취소 버튼을 누르면 이전페이지로 이동 -->	
				</div>
			</form>

		</div>
	</div>
	<!-- ================ 내용 ================= -->
	<common:footer />
	<script src="js/review.js"></script>
</body>
</html>