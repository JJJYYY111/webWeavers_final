<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib prefix="star" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>WeaveGlow - ReviewList</title>
<common:head />
</head>
<body>
	<common:header />

	<common:banner bannerText='리뷰목록' />

	<!-- ================ 내용 ================= -->
	<c:if test="${fn:length(rdatas) <= 0}"><br><br><h4 style="text-align:center;">리뷰가 없습니다.</h4><br><br></c:if> <!-- 리뷰가 없을 경우 -->
	<c:if test="${fn:length(rdatas) > 0}">							<!-- 리뷰가 있는 경우 -->
		<c:forEach var='data' items='${rdatas}'> <!-- 리뷰가 있을 경우 각 리뷰를 돌면서 내용을 표시함 -->
		
			<div class="offset-lg-1 col-lg-10">
			
			<!-- 구해한 상품 주문번호, 이미지, 이름, 작성일  -->
				<div class="comment-form"><a href="productDetail.do?ppk=${data.ppk}"><img style="max-width: 200px; max-height: 200px;"
					src="${data.pimg}" alt="상품이미지"><br><br>
					<h3 style="text-align: center; margin-bottom: 20px;">${data.pname}</h3></a> 
					<h4 style="text-align: right;">작성일: ${data.regdate}</h4>
					<form>
						<div class="form-group form-inline">
						<c:if test="${data.img == null}">
						</c:if>
						<c:if test="${data.img != null}">
							<div class="feature-img">
								<img style="max-width: 200%; max-height: 200px;"
							class="img-fluid" src="uploadimg/${data.img}" alt=""> <!-- 업로드한 이미지 -->
							</div>
						</c:if>
						</div>
						<input type="hidden" name="reviewScope" id="scope" value="${data.scope}"> 
						<star:star id="${data.bpk}" defaultRating="${data.scope}" />		<!-- 기존에 별점이 입력 되있었으면 그 별점값을 불러옴 -->
						<div class="form-group">
							<textarea class="form-control mb-10" rows="5" name="message"
								placeholder="리뷰 내용" onfocus="this.placeholder = ''"
								onblur="this.placeholder = 'Messege'" disabled>${data.content}
							</textarea>														<!-- 상품에 작성 되어있던 리뷰내용-->
						</div>
						<div style="display: flex; justify-content: flex-end;">
							<a href="review.do?bpk=${data.bpk}"
								class="button button-postComment button--active">수정하기</a>
						</div>
					</form>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<!-- ================ /내용 ================= -->

	<common:footer />
</body>
</html>