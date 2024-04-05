//---------- 캐러셀 기능 ----------

// 현재 보여지는 슬라이드의 인덱스를 나타내는 변수
let slideIndex = 0;

// 모든 슬라이드 요소를 가져옵니다.
const slides = document.querySelectorAll('.carousel img');

// 주어진 인덱스에 해당하는 슬라이드를 보여주는 함수
function showSlide(index) {
  // 모든 슬라이드를 숨깁니다.
  slides.forEach((slide) => {
    slide.style.display = 'none';
  });
  // 주어진 인덱스에 해당하는 슬라이드를 보여줍니다.
  slides[index].style.display = 'block';
}

// 다음 슬라이드로 이동하는 함수
function nextSlide() {
  // 다음 슬라이드의 인덱스를 증가시킵니다.
  slideIndex++;
  // 슬라이드의 끝에 도달하면 첫 번째 슬라이드로 되돌아갑니다.
  if (slideIndex >= slides.length) {
    slideIndex = 0;
  }
  // 변경된 슬라이드를 보여줍니다.
  showSlide(slideIndex);
}

// 이전 슬라이드로 이동하는 함수
function prevSlide() {
  // 이전 슬라이드의 인덱스를 감소시킵니다.
  slideIndex--;
  // 첫 번째 슬라이드에서 이전을 누르면 마지막 슬라이드로 이동합니다.
  if (slideIndex < 0) {
    slideIndex = slides.length - 1;
  }
  // 변경된 슬라이드를 보여줍니다.
  showSlide(slideIndex);
}

// 자동으로 슬라이드를 전환하는 함수 (3초마다)
setInterval(nextSlide, 3000);
