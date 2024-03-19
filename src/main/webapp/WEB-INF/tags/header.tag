<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Title Here</title>

<style>
/* Additional CSS for dropdown menu */
.dropdown-menu.popover {
	position: absolute;
	z-index: 1060;
	display: none;
	min-width: 10rem;
	padding: 0.5rem 0;
	margin: 0.125rem 0 0;
	font-size: 1rem;
	color: #212529;
	text-align: left;
	list-style: none;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, 0.15);
	border-radius: 0.25rem;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.175);
}

.dropdown-menu.popover.show {
	display: block;
}

.dropdown-item {
	display: block;
	width: 100%;
	padding: 0.25rem 1.5rem;
	clear: both;
	font-weight: 400;
	color: #212529;
	text-align: inherit;
	white-space: nowrap;
	background-color: transparent;
	border: 0;
}

.dropdown-item:hover, .dropdown-item:focus {
	color: #16181b;
	text-decoration: none;
	background-color: #f8f9fa;
}

/* Additional CSS to position popover under nav-item */
.nav-item {
	position: relative;
}

.dropdown-menu.popover {
	top: 100%;
	left: 0;
}
</style>
</head>

<body>
	<header class="header_area">
		<div class="main_menu">
			<nav class="navbar navbar-expand-lg navbar-light">
				<div class="container">
					<a class="navbar-brand logo_h" href="main.do"><img
						src="img/WgLogo.png" alt="" style="width: 200px;"></a>
					<div class="collapse navbar-collapse offset"
						id="navbarSupportedContent">
						<ul class="nav navbar-nav menu_nav ml-auto mr-auto">
							<li class="nav-item"><a class="nav-link"
								href="productList.do" onmouseover="showDropdownMenu()">상품목록</a>
								<div class="dropdown-menu popover" id="productDropdownMenu"
									onmouseleave="hideDropdownMenu()">
									<a href="productList.do" class="dropdown-item">전체</a>
									<div class="dropdown-divider"></div>
									<!-- 선 추가 -->
									<a href="skincareProductList.do" class="dropdown-item">스킨케어</a>
									<a href="cleangingProductList.do" class="dropdown-item">클렌징</a>
									<a href="packProductList.jsp" class="dropdown-item">마스크 팩</a>
								</div></li>
							<li class="nav-item"><a class="nav-link" href="contact.do">회사정보</a></li>
						</ul>
						<ul class="nav-shop">
							<li class="nav-item">
								<div id="searchName">
									<form action="searchProductName.do" id="insertForm"
										method="GET" onsubmit="removeSpace()">
										<input id="searchNameText" type="text" name="content" />
										<button type="submit">
											<i class="ti-search"></i>
										</button>
									</form>
								</div>
							</li>
							<c:if test="${not empty sessionMid && admin==2} ">
								<!-- 관리자이며 로그인이 되어 있는 경우 -->
								<li class="nav-item"><a href="/adminDashboard"><i
										class="ti-user"></i></a></li>
							</c:if>
							<c:if test="${empty sessionMid || not empty sessionMid}">
								<li class="nav-item"><a href="cart.do"><i
										class="ti-shopping-cart"></i></a></li>
							</c:if>
							<c:if test="${not empty sessionMid}">
								<li class="nav-item"><a class="button button-header"
									href="logout.do" id="logout">로그아웃</a></li>
								<li class="nav-item"><a class="button button-header"
									href="mypage.do" id="mypage">마이페이지</a></li>
							</c:if>
							<c:if test="${empty sessionMid}">
								<li class="nav-item"><a class="button button-header"
									href="login.do" id="login">로그인</a></li>
								<li class="nav-item"><a class="button button-header"
									href="register.do" id="register">회원가입</a></li>
							</c:if>
						</ul>
					</div>
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
					.querySelector('.nav-link[href="productList.do"]');

			dropdownMenu.style.top = productListLink.offsetHeight + "px";
		}
	</script>
</body>

</html>

