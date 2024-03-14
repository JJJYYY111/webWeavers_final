
var productList = [];															// 배열(상품목록)을 저장할 변수 선언 및 빈 배열 저장

//---------- 페이지 로드시 상품출력(신상순) ----------
window.onload = function() {
    getProductList('신상순');	
}
																			
//---------- 드롭다운메뉴 ----------
function proList(select) {														// 상품목록을 신상순/인기순/낮은가격순으로 출력하기 위해 타입을 매개변수로 전달
    var optionEl = select.options[select.selectedIndex];
    var optionEll = optionEl.dataset.sortType;
    document.getElementById('productListForm').innerHTML = '';
    
    getProductList(optionEll);
}																			
																		
//---------- 상품목록출력 ----------
function getProductList(type) {													// 함수선언
	$.ajax({																	// @WebServlet("/productList")로 데이터 요청
		type: "GET",
		url: 'productList.asy',
		data: { 'param': type },												// 드롭다운메뉴 type 전달
		success: function(datas) {												// 받아온 데이터(배열)을 처리
			if (datas.length > 0) {

				var pListFormEl = document.getElementById('productListForm');	// 'productListForm' 요소를 변수에 저장

				datas.forEach(data => {													// datas.forEach(data =>{}) 배열을 돌면서 상품객체(data)에 처리
					pListFormEl.insertAdjacentHTML('beforeend', productListForm(data))	// productListForm(data)함수로 각 상품에 대한 HTML 코드 생성
				})																		// 이 HTML을 pListFormEl요소 뒤(beforeend)에 추가

				productList = datas;											// 가공된 상품 목록 데이터를 'productList'에 저장
			} else {
				alert('상품 없음')
			}
		},
		error: function(error) {
			console.log('에러발생');
			console.log('에러종류: ' + error);
		}
	})
}


// 0213 sessionMid 오류 수정 완료
//---------- 상품 HTML 반환 ----------
// 각 상품 데이터를 받아와서 해당 상품에 대한 HTML코드를 생성해 반환
function productListForm(data) {
	var formattedPrice = data.price.toLocaleString();
	var product = `
	<div class="col-md-6 col-lg-4 col-xl-3">
		<div class="card text-center card-product" data-product-pk="${data.ppk}">
			<div class="card-product__img">
				<a href="productDetail.do?ppk=${data.ppk}">
					<img class="card-img" src="${data.img}" alt="${data.ppk}번 상품사진">
				</a>
				<ul class="card-product__imgOverlay">
					<li>
						<button onclick="wishClick(${data.ppk},'`+'${sessionMid}'+`')" class="product-btn-${data.ppk}">`
						if (data.wish == 1) {
							product += '<i class="ti-heart" style="color: red;"></i>'
						} else {
							product += '<i class="ti-heart" style="color: #fff;"></i>'
						}
							product += `
						</button>
					</li>
				</ul>
			</div>
			<div class="card-body">
				<h4 class="card-product__title" style="word-break: keep-all;">
					<a href="productDetail.do?ppk=${data.ppk}">${data.pname}</a>
				</h4>
				<p class="card-product__price" style="color:black;">${formattedPrice}원</p>
			</div>
		</div>
	</div>`
	return product;
}

//---------- 필터 검색(가격) ----------
function onClickFilter() {																		// 검색 버튼을 클릭하면 함수 실행
	var lowerPrice = priceStrToInt(document.getElementById('lower-value').innerText);			// id가 'lower-value'인 요소의 innerText를 변수에 저장 (최소값)
	var upperPrice = priceStrToInt(document.getElementById('upper-value').innerText);			// id가 'upper-value'인 요소의 innerText를 변수에 저장 (최대값)
	var pListFormEl = document.getElementById('productListForm');								// id가 'productListForm'인 요소 변수에 저장 (상품목록출력)
	pListFormEl.innerHTML = '';																	// 필터링된 목록을 새롭게 저장하기 위해, 상품목록을 출력하는 요소의 내용 모두 비우기

	// 'productList' 배열에서
	// 가격이 'lowerPrice'와 'upperPrice' 사이에 있는 상품들을
	// Array.prototype.filter() 메소드를 사용해 배열을 필터링해 'datas'변수에 저장
	var datas = productList.filter(data => { return lowerPrice <= data.price && data.price <= upperPrice })
	
	// datas.forEach(data =>{}) 배열을 돌면서 상품객체(data)에 처리
	datas.forEach(data => {														
		pListFormEl.insertAdjacentHTML('beforeend', productListForm(data))
	})
}