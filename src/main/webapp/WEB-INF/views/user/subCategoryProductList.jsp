<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>WeaveGlow - ProductList</title>
<common:head />


</head>
<body>
	<common:header />
	<common:banner bannerText='스킨케어' />

	<!-- ================ 내용 ================= -->
	<br><br>
	<section class="product-depth p_ver">
		<input type="hidden" id="checkSubCategoryPK" value="${subCategoryPK}" />
		<input type="hidden" id="checkCategoryPK" value="${categoryPK}" />
		<div class="depth-list-wrap">
			<c:if test="${categoryPK == 1}">
				<div class="list-area">
					<ul class="grid-contain">
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							href="subCategoryProductList?categoryPK=1"
							class="ctgr_box active">전체</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S01"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=1"
							class="ctgr_box ">스킨</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S02"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=2"
							class="ctgr_box ">로션/에멀젼</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S03"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=3"
							class="ctgr_box ">에센스/세럼/앰플</a></li>
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S04"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=4"
							class="ctgr_box ">크림</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S05"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=5"
							class="ctgr_box ">아이케어</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S07"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=6"
							class="ctgr_box ">미스트/부스터</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li style="border-right: 1px solid rgba(0, 0, 0, 0.1)"><a
							id="L01M01S08"
							href="subCategoryProductList?categoryPK=1&subCategoryPK=7"
							class="ctgr_box ">세트</a></li>
					</ul>
				</div>

			</c:if>
			<c:if test="${categoryPK == 2}">
				<div class="list-area">
					<ul class="grid-contain">
						<li><a href="subCategoryProductList?categoryPK=2" id=""
							class="ctgr_box active"
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">전체</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li><a id="L01M01S01"
							href="subCategoryProductList?categoryPK=2&subCategoryPK=8"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">클렌징폼</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li><a id="L01M01S02"
							href="subCategoryProductList?categoryPK=2&subCategoryPK=9"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">클렌징크림/로션/워터</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li><a id="L01M01S03"
							href="subCategoryProductList?categoryPK=2&subCategoryPK=10"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">클렌징젤/오일/티슈</a></li>
						<li><a id="L01M01S04"
							href="subCategoryProductList?categoryPK=2&subCategoryPK=11"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">립&아이 리무버</a></li>
					</ul>
				</div>
				<br>

			</c:if>
			<c:if test="${categoryPK == 3}">
				<div class="list-area">
					<ul class="grid-contain">
						<li><a href="subCategoryProductList?categoryPK=3" id=""
							class="ctgr_box active"
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">전체</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li><a id="L01M01S01"
							href="subCategoryProductList?categoryPK=3&subCategoryPK=12"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">마사지/워시오프 팩</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li><a id="L01M01S02"
							href="subCategoryProductList?categoryPK=3&subCategoryPK=13"
							class="ctgr_box " v>필링/스크럽</a></li>
						<!-- 선택된 카테고리의 경우 class 에 active 추가 -->
						<li><a id="L01M01S03"
							href="subCategoryProductList?categoryPK=3&subCategoryPK=14"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">시트마스크</a></li>
						<li><a id="L01M01S04"
							href="subCategoryProductList?categoryPK=3&subCategoryPK=15"
							class="ctgr_box "
							style="border-right: 1px solid rgba(0, 0, 0, 0.1)">코팩</a></li>
					</ul>
				</div>
				<br>
			</c:if>
		</div>
	</section>
	<section class="section-margin--small mb-5">
		<div class="container">
			<div class="row">
				<!-- 필터 -->
				<div class="col-xl-3 col-lg-4 col-md-5">
					<div class="sidebar-filter">
						<div class="top-filter-head">Product Filters</div>
						<div class="common-filter" style="padding-bottom: 15px">
							<div class="head">가격</div>
							<div class="price-range-area">
								<div id="price-range"></div>
								<div class="value-wrapper d-flex">
									<div class="price">가격:</div>
									<div id="lower-value"></div>
									<span>&#8361;</span>
									<!-- 원화표시 -->
									<div class="to">to</div>
									<div id="upper-value"></div>
									<span>&#8361;</span>
									<!-- 원화표시 -->
								</div>
							</div>
							<div class="custom-button" tabindex="0"
								style="text-align: right; margin-top: 5px">
								<button onclick="onClickFilter()"
									style="background-color: #384aeb; color: white; border: none; margin-right: 15px;">검색</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /필터 -->
				<div class="col-xl-9 col-lg-8 col-md-7">
					<!-- 목록 순서 -->
					<div class="filter-bar d-flex flex-wrap align-items-center">
						<div class="sorting">
							<select id="shit" onchange="proList(this)">
								<option value="1" data-sort-type="신상순">신상순</option>
								<option value="2" data-sort-type="인기순">인기순</option>
								<option value="3" data-sort-type="낮은가격순">낮은가격순</option>
								<!--  <option value="1">추천순</option> 찜개수 -->
							</select>
						</div>
						<span id=productCount></span>

					</div>
					<!-- 목록 순서 -->
					<!-- 상품 -->
					<section class="lattest-product-area pb-40 category-list">
						<div class="row" id="productListForm">
							<!-- JS: 각 상품 데이터를 받아와서 해당 상품에 대한 HTML코드를 생성해 반환 -->
						</div>
						<div id='paging'></div>
					</section>
					<!-- /상품 -->
				</div>
			</div>
		</div>
	</section>
	<!-- ================ /내용 ================= -->

	<common:footer />

	<script src="/resources/js/productList.js"></script>
	<link rel="stylesheet" href="/resources/weaveGlowCSS/subCategoryProduceList.css">
</body>
</html>