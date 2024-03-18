//---------- 캐러셸 기능 ----------
 let slideIndex = 0;
  const slides = document.querySelectorAll('.carousel img');
  
  function showSlide(index) {
    slides.forEach((slide) => {
      slide.style.display = 'none';
    });
    slides[index].style.display = 'block';
  }

  function nextSlide() {
    slideIndex++;
    if (slideIndex >= slides.length) {
      slideIndex = 0;
    }
    showSlide(slideIndex);
  }

  function prevSlide() {
    slideIndex--;
    if (slideIndex < 0) {
      slideIndex = slides.length - 1;
    }
    showSlide(slideIndex);
  }

  // 자동으로 슬라이드 전환 (3초마다)
  setInterval(nextSlide, 3000);

//---------- 찜 기능 ----------
function wishClick(ppk, mid) {
	if(mid == null || mid == '' || mid == 'undefined'){			// 로그아웃 상태
		alert('찜 실패, 로그인 후 찜 가능합니다!');
	}
	else{
		$.ajax({
			type: "POST",										// POST 방식
			url: "likeButton.asy",								// 서블릿 url
			data: { 'pk': ppk },								// pk 전달
			dataType: "text",									// back단에서 text형태로 받음
			success: function(data) {							// 성공했다면,
				// data가 1이면 찜 성공
				if (data == 1) {
					Swal.fire({
					  hideClass : {
						  popup : 'animate__animated animate__fadeOut animate__faster'
						},
					  title: "찜완료",
					  imageUrl: "img/redheart.png",
					  imageWidth: 100,
					  imageHeight: 100,
					  imageAlt: "Custom image",
					  showConfirmButton : false,
					  width : "10%",
					  timer : "1000"
					});
					document.querySelectorAll('button.product-btn-'+ppk).forEach(data => {	// 문서의 해당 ppk를 가진 모든 버튼을 찾아 동일한 style 적용
						data.children[0].style.color = 'red'								// 버튼의 첫번째 자식(icon) color(폰트색)을 red
					})
				}
				// data가 2이면 찜 취소
				else if (data == 2) {
					Swal.fire({
						hideClass : {
						  popup : 'animate__animated animate__fadeOut animate__faster'
						},
					  title: "찜취소",
					  imageUrl: "img/emptyheart.png",
					  imageWidth: 100,
					  imageHeight: 100,
					  imageAlt: "Custom image",
					  showConfirmButton : false,
					  width : "10%",
					  timer : "1000"
					});
					document.querySelectorAll('button.product-btn-'+ppk).forEach(data => {	// 문서의 해당 ppk를 가진 모든 버튼을 찾아 동일한 style 적용
						data.children[0].style.color = '#fff'
						// 버튼의 첫번째 자식(icon) color(폰트색)을 #fff
					})
					
					// 찜 목록에서 찜 취소 > 상품 제거
					if(document.getElementById(ppk) != null){
						document.getElementById(ppk).remove();
					}
				}
				// data가 0이면 찜 실패
				else if (data == 0) {
					alert('찜 실패, 로그인 후 찜 가능합니다!');
				}
			},
			error: function(error) {
				console.log('에러발생');
				console.log('에러종류: ' + error);
			}
		})
	}
	
}

//---------- 이름검색 ----------
function removeSpace(){
	var inputElement = document.getElementById('searchNameText');
	inputElement.value = inputElement.value.trim();								// 검색란 공백 제거
}

//---------- 상품검색 <> xx ----------
function blankSpace() {
    var flag = true;
    // 모든 input 요소의 value 확인하기
    $('input[type="text"]').each(function() {
        var content = $(this).val().trim(); // trim() 함수를 호출합니다.
        // < 나 > 가 하나라도 입력되었는지 확인
        if (content.includes('<') || content.includes('>') || content.includes('%')) {
			console.log('< 입력');
            flag = false;     
        }
        $(this).val(content);
        if (content === '') { // 입력 값이 공백이면
            flag = false;
        }

    });
    return flag;
}

$(document).ready(function() {
	console.log('성공');
	 $('input[type="text"]').attr('maxlength', 50);
	$('textarea').attr('maxlength', 50);
        $('#insertForm').submit(function(event) {
			
            // 입력 필드의 값이 공백인지 확인합니다.
            if (!blankSpace()) {
                event.preventDefault(); // 폼 제출을 막습니다.
                alert('입력 필드에 기호 입력은 불가합니다'); // 사용자에게 알립니다.
            }
        });
    
});
//---------- 상품총가격 ----------
var orderListEl = document.getElementById('orderList')							// 'orderList'id가진 <div>저장
if(orderListEl != null){
	var productPriceEls = orderListEl.querySelectorAll('span.productPrice');	// <div>의 <span> 저장
	var totalPrice = 0;
	productPriceEls.forEach(x => {												// forEach로 가격 누적
		totalPrice += parseInt(priceStrToInt(x.innerText));									// <span>의 innerText(가격)을
	})
	
	document.getElementById('totalPrice').innerText = totalPrice.toLocaleString();				// 'totalPrice'id가진 요소의 innerText에 totalPrice 출력
	if(document.getElementById('totalPrice2')){
		document.getElementById('totalPrice2').innerText = totalPrice.toLocaleString();
	}
}

//---------- 가격 형변환 ----------
function priceStrToInt(price) {
    var priceSplit = price.split(',');
    var newPrice = '';
    priceSplit.forEach(x => {
        newPrice += x;
    })
    return parseInt(newPrice);
}

/*-------------------- 템플릿 기본js --------------------*/
$(function() {
	"use strict";

	//------- Parallax -------//
	skrollr.init({
		forceHeight: false
	});

	//------- Active Nice Select --------//
	$('select').niceSelect();

	//------- hero carousel -------//
	$(".hero-carousel").owlCarousel({ //Owl Carousel 외부 라이브러리 --> jQuery기반 --> 롤링 기능 지원하는 함수
		items: 3,
		margin: 10,
		autoplay: false,
		autoplayTimeout: 5000,
		loop: true,
		nav: false,
		dots: false,
		responsive: {
			0: {
				items: 1
			},
			600: {       // 모바일
				items: 2
			},
			810: {       // 테블릿
				items: 3
			},
			1280: {     // PC
				items: 5
			},
			1980: {     // PC
				items: 7
			}
		}
	});

	//------- Best Seller Carousel -------//
	if ($('.owl-carousel').length > 0) {
		$('#bestSellerCarousel').owlCarousel({
			loop: true,
			margin: 30,
			nav: true,
			navText: ["<i class='ti-arrow-left'></i>", "<i class='ti-arrow-right'></i>"],
			dots: false,
			responsive: {
				0: {
					items: 1
				},
				600: {
					items: 2
				},
				900: {
					items: 3
				},
				1130: {
					items: 4
				}
			}
		})
	}

	//------- single product area carousel -------//
	$(".s_Product_carousel").owlCarousel({
		items: 1,
		autoplay: false,
		autoplayTimeout: 5000,
		loop: true,
		nav: false,
		dots: false
	});

	//------- mailchimp --------//  
	function mailChimp() {
		$('#mc_embed_signup').find('form').ajaxChimp();
	}
	mailChimp();

	//------- fixed navbar --------//  
	$(window).scroll(function() {
		var sticky = $('.header_area'),
			scroll = $(window).scrollTop();

		if (scroll >= 100) sticky.addClass('fixed');
		else sticky.removeClass('fixed');
	});

	//------- Price Range slider -------//
	if (document.getElementById("price-range")) {

		var nonLinearSlider = document.getElementById('price-range');

		noUiSlider.create(nonLinearSlider, {
			connect: true,
			behaviour: 'tap',
			start: [0, 200000],
			range: {
				// Starting at 500, step the value by 500,
				// until 4000 is reached. From there, step by 1000.
				'min': [0],
				'0%': [0, 500],
				'100%': [200000, 1000],
				'max': [200000]
			}
		});
		
		var nodes = [
			document.getElementById('lower-value'), // 0
			document.getElementById('upper-value')  // 1
		];

		// Display the slider value and how far the handle moved
		// from the left edge of the slider.
		nonLinearSlider.noUiSlider.on('update', function(values, handle) {
			//console.log(values, handle, parseInt(values[handle].split('.')[0]).toLocaleString() , values[handle].split('.')[0].toLocaleString())
			nodes[handle].innerHTML = parseInt(values[handle].split('.')[0]).toLocaleString();
		});

	}

});
