/**
 * 장바구니 cart 관련 js파일 
 * <pre>
 * <b>History:</b>
 *    작성자, 1.0, 216.1.3 최초작성
 * </pre>
 *
 * @author 천지성
 * @version 1.0, 216.1.3 소스 수정
 * @see    None
 */


/*  장바구니안에서 개수 및 가격 변경 */
/**
 * updateQuantity 함수: 제품 수량을 업데이트하고, 서버에 업데이트 요청을 보냄
 * Parameters:
 * 
 * ppk: 제품 고유 식별자
 * updown: '1'일 경우 수량 증가, '0'일 경우 수량 감소
 * price: 제품 가격
 * 
 */
function updateQuantity(ppk, updown, price) {
    // 해당 제품의 수량 입력 필드와 총 가격 표시 영역을 jQuery로 선택함
    var result = $('#qty_' + ppk);
    var sumPrice = $('#total_' + ppk);

    // 현재 수량과 총 가격을 가져옴
    var qty = result.val();
    var total = parseInt(price) * parseInt(qty);

    // 수량이 이미 1이고 감소 버튼이 클릭된 경우
    if (updown === '0' && parseInt(qty) <= 1) {
        return; // 더 이상 수량을 감소하지 않음
    }

    // jQuery를 사용하여 AJAX POST 요청을 보냄
    $.ajax({
        url: 'cartUpdate.asy',
        type: 'POST',
        data: {
            ppk: ppk,
            updown: updown
        },
        success: function(response) {
            // 성공적으로 처리된 경우에는 클라이언트 측에서 수량을 업데이트함
            if (updown === '1') {
                result.val(parseInt(qty) + 1);
            } else {
                result.val(parseInt(qty) - 1);
            }

            // 총 가격을 계산하고 화면에 업데이트함
            total = parseInt(price) * parseInt(result.val());
			// 숫자 현지화 메서드 사용
			formattedPrice = total.toLocaleString();
            sumPrice.text(formattedPrice);

            // 수정: 수량이 변경될 때마다 전체 소계를 업데이트함
            updateSubtotal();
        },
        error: function() {
            // 에러 처리 로직을 추가할 수 있음
        }
    });
}

// updateSubtotal 함수: 장바구니의 전체 소계를 업데이트함
function updateSubtotal() {
    var total = 0;

    // 모든 제품 가격 요소를 가져와서 총 가격을 계산함
    var productPrices = document.getElementsByClassName('productPrice');
	console.log(priceStrToInt(productPrices[0].textContent), productPrices[0].textContent)
    for (var i = 0; i < productPrices.length; i++) {
        total += priceStrToInt(productPrices[i].textContent);
		//console.log(total)
    }

	var formattedPrice = total.toLocaleString();

    // 수정: jQuery를 사용하여 화면에 전체 소계를 업데이트함
    $('#totalPrice').text(formattedPrice);
}


/* 장바구니 추가 */
// addToCart 함수: 장바구니에 제품 추가하는 함수
function addToCart() {
    // getProductPPK 함수를 통해 동적으로 설정된 데이터 속성 'data-ppk' 값을 가져옴
    var ppk = getProductPPK();  

    // 수량 입력값 가져오기
    var quantity = document.getElementById('sst').value;

    // AJAX를 사용하여 서버에 데이터 전송
    var data = {
        ppk: ppk,
        cnt: quantity
    };

    $.ajax({
        type: 'POST',
        url: 'cartInsert.asy',  
        data: data,
        success: function(response) {
            // 서버 응답에 따라 알림 표시
            if (response === '1') {
               /* alert('상품이 장바구니에 추가되었습니다.');*/
               Swal.fire({
						hideClass : {
						  popup : 'animate__animated animate__fadeOut animate__faster'
						},
					  title: "장바구니 추가 완료",
					  imageUrl: "img/cart.png",
					  imageWidth: 100,
					  imageHeight: 100,
					  imageAlt: "Custom image",
					  showConfirmButton : false,
					  width : "20%",
					  timer : "1000"
					});
            } else {
				if(sessionStorage.getItem("sessionMid") == null){
					alert('로그인을 해야 사용가능합니다.');
				}
				else{
                	alert('장바구니 추가에 실패했습니다.');
				}
            }
        },
        error: function() {
            alert('AJAX 요청 실패');
        }
    });
}

// getProductPPK 함수: HTML에서 설정한 데이터 속성 'data-ppk' 값을 읽어오는 함수
function getProductPPK() {
    // HTML에서 설정한 데이터 속성을 읽어옴
    var ppk = document.querySelector('.product_image_area').getAttribute('data-ppk');
    return ppk;
}

