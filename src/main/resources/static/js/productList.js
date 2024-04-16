var productList = []; // 배열(상품목록)을 저장할 변수 선언 및 빈 배열 저장
var subCategoryPK;
var categoryPK;	

var totalPages = 0; // 전체 페이지 수를 저장할 변수 선언
var currentPage = 1; // 현재 페이지 번호
var pageSize = 20; // 페이지당 상품 수를 설정

//---------- 페이지 로드시 상품출력(신상순) ----------
window.onload = function() {
	subCategoryPK = document.getElementById('checkSubCategoryPK').value;
	subCategoryPK == null || subCategoryPK == ""? subCategoryPK = 0 : subCategoryPK;
	categoryPK = document.getElementById('checkCategoryPK').value;
	categoryPK == null || categoryPK==""? categoryPK = 0 : categoryPK;
    getProductList('신상순'); 
    setFocusOnButton();   
}

//---------- 드롭다운메뉴 ----------
function proList(select) {
    var optionEl = select.options[select.selectedIndex];
    var optionEll = optionEl.dataset.sortType;
    document.getElementById('productListForm').innerHTML = '';
    
    getProductList(optionEll);
} 

//---------- 상품목록출력 ----------
function getProductList(type) {
    $.ajax({
        type: "GET",
        url: 'async/productList',
        data: { 'type': type,
        		'categoryPK' : categoryPK,
				'subCategoryPK' :  subCategoryPK},
        success: function(datas) {
            if (datas.length > 0) {
                datas = JSON.parse(datas);
                var pListFormEl = document.getElementById('productListForm');

                datas.forEach(data => {
                    pListFormEl.insertAdjacentHTML('beforeend', productListForm(data));
                });
                
                productList = datas;
                totalProducts = productList.length; // 총 상품 수 변경

                // 페이지네이션 추가
                renderPagination(totalProducts, pageSize);
                // 페이지 이동
                goToPage(currentPage);
            } else {
                alert('상품 없음');
            }
        },
        error: function(error) {
            console.log('에러발생');
            console.log('에러종류: ' + error);
        }
    });
}

//---------- 상품 HTML 반환 ----------
function productListForm(data) {
    var formattedPrice = data.productPrice.toLocaleString();
    var product = `
    <div class="col-md-6 col-lg-4 col-xl-3">
        <div class="card text-center card-product" data-product-pk="${data.productPK}">
            <div class="card-product__img">
                <a href="productDetail?productPK=${data.productPK}">
                    <img class="card-img" src="${data.productImg}" alt="${data.productPK}번 상품사진">
                </a>
                <ul class="card-product__imgOverlay">
                    <li>
                        <button onclick="wishClick(${data.productPK},'`+'${sessionMid}'+`')" class="product-btn-${data.productPK}">
                        ${data.wish == 1 ? '<i class="ti-heart" style="color: red;"></i>' : '<i class="ti-heart" style="color: #fff;"></i>'}
                        </button>
                    </li>
                </ul>
            </div>
            <div class="card-body">
                <h4 class="card-product__title" style="word-break: keep-all;">
                    <a href="productDetail?productPK=${data.productPK}">${data.productName}</a>
                </h4>
                <p class="card-product__price" style="color:black;">${formattedPrice}원</p>
            </div>
        </div>
    </div>`;
    return product;
}

// 필터 검색(가격)
function onClickFilter() {
    var lowerPrice = priceStrToInt(document.getElementById('lower-value').innerText);
    var upperPrice = priceStrToInt(document.getElementById('upper-value').innerText);

    // productList를 필터링하여 새로운 배열 생성
    var filteredProducts = productList.filter(data => {
        return lowerPrice <= data.productPrice && data.productPrice <= upperPrice;
    });

    // 필터링된 상품 개수를 기준으로 페이지네이션을 다시 렌더링함
    renderPagination(filteredProducts.length, pageSize);

    // 필터링된 상품 목록을 전역 변수 productList에 업데이트함
    productList = filteredProducts;

    // 첫 번째 페이지로 이동
    goToPage(1);
}




//------------포커스 지정-----------
function setFocusOnButton() {
   var elements = document.querySelectorAll('.custom-button'); // 클래스가 ctgr_box인 모든 요소를 선택합니다.
    if (elements.length > 0) { // 선택된 요소가 있는 경우에만 포커스를 설정합니다.
        elements[0].focus(); // 첫 번째로 일치하는 요소에 포커스를 설정합니다.
    }
}

// 페이지 이동 함수
function goToPage(pageNumber) {
    currentPage = pageNumber;
    var startIdx = (currentPage - 1) * pageSize;
    var endIdx = Math.min(startIdx + pageSize, totalProducts);

    // 페이지에 표시할 상품들만 추출하여 출력함
    var currentPageProducts = productList.slice(startIdx, endIdx);
    displayProducts(currentPageProducts);

    renderFilterPagination(totalProducts, pageSize);

    // 페이지 이동 후에 포커스를 설정함
    setFocusOnButton();
}


//---------- 페이지네이션 렌더링 ----------
function renderPagination(totalProducts, pageSize) {
    totalPages = Math.ceil(totalProducts / pageSize); // 페이지 수를 올림하여 계산 , 전역 변수 totalPages를 업데이트

    var paginationContainer = document.getElementById('paging');
    paginationContainer.innerHTML = ''; // 이전 페이지네이션을 초기화

    var paginationHTML = '<ul>';

    // 이전 페이지로 이동 버튼
    paginationHTML += '<li><a href="javascript:void(0);" onclick="firstPage();"> &lt;&lt; </a></li>';
    paginationHTML += '<li><a href="javascript:void(0);" onclick="prevPage();">&lt;</a></li>';

    // 페이지 번호 링크 생성
    var startPage = Math.max(currentPage - 2, 1); // 현재 페이지를 중심으로 좌측으로 최대 2페이지까지 표시하도록 설정함
    var endPage = Math.min(startPage + 4, totalPages); // 우측으로 최대 4페이지까지 표시하도록 설정
    if (endPage - startPage < 4) { // 오른쪽으로 표시할 페이지가 부족한 경우 좌측에 추가 페이지 버튼을 표시함
        startPage = Math.max(startPage - (4 - (endPage - startPage)), 1);
    }
    for (var i = startPage; i <= endPage; i++) {
        paginationHTML += '<li ' + (currentPage === i ? 'class="active"' : '') + '><a href="javascript:void(0);" onclick="goToPage(' + i + ');">' + i + '</a></li>';
    }

    // 다음 페이지로 이동 버튼
    paginationHTML += '<li><a href="javascript:void(0);" onclick="nextPage();">&gt;</a></li>';
    paginationHTML += '<li><a href="javascript:void(0);" onclick="lastPage();"> &gt;&gt; </a></li>';

    paginationHTML += '</ul>';

    paginationContainer.innerHTML = paginationHTML;
}


function renderFilterPagination(filteredProductsCount, pageSize) {
    var totalPages = Math.ceil(filteredProductsCount / pageSize); // 필터링된 상품목록을 pageSize로 나눈 후 올림하여 총 페이지 수를 계산함

    var paginationContainer = document.getElementById('paging');
    paginationContainer.innerHTML = ''; // 이전 페이지네이션을 초기화

    var paginationHTML = '<ul>';

    // 이전 페이지로 이동 버튼
    paginationHTML += '<li><a href="javascript:void(0);" onclick="firstPage();"> &lt;&lt; </a></li>';
    paginationHTML += '<li><a href="javascript:void(0);" onclick="prevPage();">&lt;</a></li>';

    // 페이지 번호 링크 생성
    var startPage = Math.max(currentPage - 2, 1); // 현재 페이지를 중심으로 좌측으로 최대 2페이지까지 표시하도록 설정
    var endPage = Math.min(startPage + 4, totalPages); // 우측으로 최대 4페이지까지 표시하도록 설정
    if (endPage - startPage < 4) { // 오른쪽으로 표시할 페이지가 부족한 경우 좌측에 추가 페이지 버튼을 표시함
        startPage = Math.max(startPage - (4 - (endPage - startPage)), 1);
    }

    for (var i = startPage; i <= endPage; i++) {
        // 각 페이지에 대해 상품이 있는지 확인하여 없는 경우에는 페이지네이션 버튼을 생성하지 않습니다.
        var pageStartIdx = (i - 1) * pageSize;
        var pageEndIdx = Math.min(pageStartIdx + pageSize, filteredProductsCount);
        var currentPageProducts = productList.slice(pageStartIdx, pageEndIdx);

        if (currentPageProducts.length > 0) {
            paginationHTML += '<li ' + (currentPage === i ? 'class="active"' : '') + '><a href="javascript:void(0);" onclick="goToPage(' + i + ');">' + i + '</a></li>';
        }
    }

    // 다음 페이지로 이동 버튼
    paginationHTML += '<li><a href="javascript:void(0);" onclick="nextPage();">&gt;</a></li>';
    paginationHTML += '<li><a href="javascript:void(0);" onclick="lastPage();"> &gt;&gt; </a></li>';

    paginationHTML += '</ul>';

    paginationContainer.innerHTML = paginationHTML;
}

//---------- 현재 페이지의 상품들을 출력하는 함수 ----------
function displayProducts(products) { // 상품 목록을 출력할 요소를 가져옴
    var pListFormEl = document.getElementById('productListForm');
    pListFormEl.innerHTML = ''; // 요소 내용 초기화
    
    products.forEach(product => { // 각 상품을 순회하며 상품을 출력 , 상품을 출력하는 HTML 코드를 요소에 추가
        pListFormEl.insertAdjacentHTML('beforeend', productListForm(product));
    });
}
// 처음 페이지로 이동하는 함수
function firstPage() { // 현재 페이지가 첫 페이지가 아닌 경우에만 처음 페이지로 이동
    navigatePage(1);
}
// 이전 페이지를 가져와서 보여주는 함수
function prevPage() { // 현재 페이지가 첫 페이지가 아닌 경우에만 이전 페이지로 이동
    navigatePage(currentPage - 1);
}
// 다음 페이지를 가져와서 보여주는 함수
function nextPage() { // 현재 페이지가 마지막 페이지가 아닌 경우에만 다음 페이지로 이동
    navigatePage(currentPage + 1);
}
// 마지막 페이지로 이동하는 함수
function lastPage() { // 현재 페이지가 마지막 페이지가 아닌 경우에만 마지막 페이지로 이동
    navigatePage(totalPages);
}
// 페이지를 이동하는 함수
function navigatePage(pageNumber) {
	// pageNumber가 유효 범위안에 있고, 현재 페이지와 다른 페이지일 경우에만 이동
    if (pageNumber >= 1 && pageNumber <= totalPages && pageNumber !== currentPage) {
        currentPage = pageNumber;  // 현재 페이지 번호 업데이트
        goToPage(currentPage); // 새로운 페이지로 이동
    }
}