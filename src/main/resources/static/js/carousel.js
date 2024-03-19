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