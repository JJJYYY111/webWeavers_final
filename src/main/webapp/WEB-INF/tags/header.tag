<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/admin/adminCSS/headerCSS.css">
<title>Your Title Here</title>


</head>

<body>
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<a class="navbar-brand logo_h" href="main"><img
						src="/resources/img/WgLogo.png" alt="" style="width: 200px;"></a>
					<div class="collapse navbar-collapse offset"
						id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav justify-content-center">

							<li class="nav-item" onmouseleave="hideDropdownMenu()"><a
								class="nav-link" href="/productList"
								onmouseover="showDropdownMenu()">상품목록</a>
								<div class="dropdown-menu popover" id="productDropdownMenu">
									<a href="productList" class="dropdown-item">전체</a>
									<div class="dropdown-divider"></div>
									<!-- 선 추가 -->
									<a href="/subCategoryProductList?categoryPK=1"
										class="dropdown-item">스킨케어</a> <a
										href="/subCategoryProductList?categoryPK=2"
										class="dropdown-item">클렌징</a> <a
										href="/subCategoryProductList?categoryPK=3"
										class="dropdown-item">마스크 팩</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="/contact">회사정보</a></li>
						</ul>
					</div>
					<ul class="nav-shop justify-content-center justify-content-md-end">
						<li class="nav-item">
							<div id="searchName">
								<form action="searchProductName" id="insertForm" method="GET"
									onsubmit="removeSpace()">
									<input id="searchNameText" type="text" name="content" />
									<button type="submit">
										<i class="ti-search"></i>
									</button>
								</form>
							</div>
						</li>
						<c:if
							test="${ empty sessionMid || ( grade != 2 && not empty sessionMid)}">
							<li class="nav-item"><a href="cart"><i
									class="ti-shopping-cart"></i></a></li>
						</c:if>
						<c:if test="${not empty sessionMid && grade == 2}">
							<li class="nav-item"><a href="adminDashboard"><i
									class="ti-user"></i></a></li>
						</c:if>
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

			</nav>
		</div>
	</header>

	<!-- Bootstrap JS (popper.js is required) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<script>
		function showDropdownMenu() {
			adjustPopoverPosition();
			var dropdownMenu = document.getElementById("productDropdownMenu");
			dropdownMenu.classList.add("show");
		}

		function hideDropdownMenu() {
			var dropdownMenu = document.getElementById("productDropdownMenu");
			dropdownMenu.classList.remove("show");
		}

		function adjustPopoverPosition() {
			var dropdownMenu = document.getElementById("productDropdownMenu");
			var productListLink = document
					.querySelector('.nav-link[href="/productList"]');

			dropdownMenu.style.top = productListLink.offsetHeight + "px";
		}
	</script>


</body>
</html>