<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.stars {
	display: flex;
	align-items: center;
}

.stars button {
	background: none;
	border: none;
	font-size: 24px;
	cursor: pointer;
}

.stars button:hover {
	color: gold;
}

.stars .status {
	margin-left: 10px;
	font-size: 16px;
	color: red;
}

.selected {
	color: gold;
}

.review-description {
	margin-top: 20px;
	font-size: 18px;
}
</style>
</head>
<body>
	<div class="stars-container" data-star="" data-text="최고">
		<div class="stars">
			<div id="starButtonsContainer">
				<!-- 별점 버튼들은 여기에 동적으로 추가될 예정 -->
			</div>
			<span class="status default-review-star">(필수)<em>*</em></span>
			<p id="selectedRatingDescription" ></p>
		</div>

		<div class="review-description">
			
		</div>
	</div>

	<script>
  const starContainer = document.querySelector('.stars-container');
  const statusElement = document.querySelector('.status');
  const descriptionElement = document.getElementById('selectedRatingDescription');
  const starButtonsContainer = document.getElementById('starButtonsContainer');

  let selectedRating = 0;
  let isClicked = false;

  // 별점의 수
  const totalStars = 5;

  // 별점 버튼들을 동적으로 생성
  for (let i = 1; i <= totalStars; i++) {
    const starButton = document.createElement('button');
    starButton.classList.add('star-value', 'js-star-btn');
    starButton.type = 'button';
    starButton.value = i;
    starButton.dataset.text = getDescription(i);
    starButton.innerHTML = '★';

    starButtonsContainer.appendChild(starButton);
  }

  const starButtons = document.querySelectorAll('.js-star-btn');

  function getDescription(value) {
    const ratingDescriptions = {
      '1': '나쁨',
      '2': '별로',
      '3': '보통',
      '4': '좋음',
      '5': '최고'
    };
    return ratingDescriptions[value];
  }

  function updateRating(value) {
    selectedRating = value;
     statusElement.textContent = ` `; 
/*      statusElement.textContent = `선택된 평가: ${selectedRating}`;  */

    // Remove the 'selected' class from all buttons
    starButtons.forEach(btn => btn.classList.remove('selected'));

    // Add the 'selected' class to buttons up to the clicked button
    for (let i = 0; i < selectedRating; i++) {
      starButtons[i].classList.add('selected');
    }

    // Display the rating description
    descriptionElement.innerHTML = '<strong>평가 내용:</strong> ' + getDescription(selectedRating);
  }

  starContainer.addEventListener('mousedown', (event) => {
    if (event.target.classList.contains('js-star-btn')) {
      isClicked = true;
    }
  });

  starContainer.addEventListener('mouseup', (event) => {
    if (event.target.classList.contains('js-star-btn')) {
      isClicked = false;
      updateRating(parseInt(event.target.value));
    }
  });

  starContainer.addEventListener('mousemove', (event) => {
    if (isClicked && event.target.classList.contains('js-star-btn')) {
      updateRating(parseInt(event.target.value));
    }
  });

   starContainer.addEventListener('mouseleave', () => {
    // When the mouse leaves the container, reset to the selected rating
    if (isClicked) {
      updateRating(selectedRating);
    }
  }); 
  
/*   starContainer.addEventListener('mouseleave', (event) => {
	  // 마우스가 별점을 주는 칸의 범위를 벗어났을 때만 실행
	  if (isClicked && !starContainer.contains(event.relatedTarget)) {
	    updateRating(selectedRating);
	  }
	}); */
/* 	starContainer.addEventListener('mouseleave', (event) => {
		  // 마우스가 별점을 주는 칸의 범위를 벗어났을 때만 실행
		  if (isClicked && !starContainer.contains(event.relatedTarget)) {
		    const starButtonsArray = Array.from(starButtons);
		    const currentIndex = starButtonsArray.findIndex(btn => btn.classList.contains('selected'));
		    const targetIndex = starButtonsArray.findIndex(btn => btn === event.relatedTarget);

		    // 고정할 별점의 위치를 찾아서 해당 별점을 클릭한 것과 같은 효과를 줌
		    if (targetIndex !== -1) {
		      const fixedRating = targetIndex + 1;
		      updateRating(fixedRating);
		    } else {
		      // 벗어난 위치에는 현재 선택된 별점을 유지
		      updateRating(selectedRating);
		    }
		  }
		}); */
/* 		starContainer.addEventListener('mouseleave', (event) => {
			  // 마우스가 별점을 주는 칸의 범위를 벗어났을 때만 실행
			  if (isClicked && !starContainer.contains(event.relatedTarget)) {
			    const starButtonsArray = Array.from(starButtons);
			    const currentIndex = starButtonsArray.findIndex(btn => btn.classList.contains('selected'));
			    const targetIndex = starButtonsArray.findIndex(btn => btn === event.relatedTarget);

			    // 고정할 별점의 위치를 찾아서 해당 별점을 클릭한 것과 같은 효과를 줌
			    if (targetIndex !== -1) {
			      const fixedRating = targetIndex + 1;
			      updateRating(fixedRating);
			    } else {
			      // 벗어난 위치에는 현재 선택된 별점을 유지
			      updateRating(selectedRating);
			    }
			  } else if (isClicked && starContainer.contains(event.relatedTarget)) {
			    const starButtonsArray = Array.from(starButtons);
			    const targetIndex = starButtonsArray.findIndex(btn => btn === event.relatedTarget);

			    // 마우스가 다시 별점을 주는 칸으로 돌아왔을 때, 현재 선택된 별점을 유지하며 이동한 별점의 효과를 줌
			    if (targetIndex !== -1) {
			      const movedRating = targetIndex + 1;
			      updateRating(movedRating);
			    }
			  }
			}); */
</script>

</body>
</html>
