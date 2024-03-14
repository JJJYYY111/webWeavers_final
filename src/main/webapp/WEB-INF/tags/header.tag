<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!--================ 상단 메뉴 =================-->
<header class="header_area">
	<div class="main_menu">
		<nav class="navbar navbar-expand-lg navbar-light">
			<div class="container">
				<a class="navbar-brand logo_h" href="/main"><img
					src="img/WgLogo.png" alt="" style="width: 200px;"></a>
				<div class="collapse navbar-collapse offset"
					id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto mr-auto">
						<li class="nav-item"><a class="nav-link"
							href="/productList">상품목록</a></li>
						<li class="nav-item"><a class="nav-link" href="/contact">회사정보</a></li>
					</ul>
					<ul class="nav-shop">
						<li class="nav-item">
							<div id="searchName">
								<form action="/searchProductName" id="insertForm" method="GET" onsubmit="removeSpace()">
									<input id="searchNameText" type="text" name="content" />
									<button type="submit">
										<i class="ti-search"></i>
									</button>
								</form>

							</div>
						</li>
						<li class="nav-item"><a href="/cart">
								<i class="ti-shopping-cart"></i></a></li>
						
						<c:if test="${not empty sessionMid}">
							<li class="nav-item"><a class="button button-header"
								href="/logout" id="logout">로그아웃</a></li>
							<li class="nav-item"><a class="button button-header"
								href="/mypage" id="mypage">마이페이지</a></li>
						</c:if>
						<c:if test="${empty sessionMid}">
							<li class="nav-item"><a class="button button-header"
								href="/login" id="login">로그인</a></li>
							<li class="nav-item"><a class="button button-header"
								href="/register" id="register">회원가입</a></li>
						</c:if>
						
					</ul>
				</div>
			</div>
		</nav>
	</div>
</header>
<!--================ /상단 메뉴 =================-->