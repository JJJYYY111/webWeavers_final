<%@ tag description="Star Rating Component" pageEncoding="UTF-8" %>
<%@ attribute name="id" required="true" type="java.lang.String" description="The ID of the stars container" %>
<%@ attribute name="defaultRating" required="false" type="java.lang.Integer" description="The default selected rating" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="${id}" class="stars-container" data-star="" data-text="최고">
    <div class="stars">
        <div id="${id}starButtonsContainer">
            <!-- 별점 버튼들은 여기에 동적으로 추가될 예정 -->
        </div>
        <span class="status default-review-star"></span>
        <p align="justify" id="${id}selectedRatingDescription"></p>
    </div>
    <div class="review-description">
    </div>
</div>

<script>
    // 별점 컴포넌트 초기화 함수
    function initializeStarRating_${id}(defaultRating) { // 매개변수는 컴포넌트가 초기화 될때 기본적으로 선택된 별점을 나타냄
        const starContainer = document.getElementById("${id}"); // 별점 컨테이너
        const statusElement = starContainer.querySelector('.status'); // 상태요소
        const descriptionElement = document.getElementById('${id}selectedRatingDescription'); // 설명 요소
        const starButtonsContainer = document.getElementById('${id}starButtonsContainer'); // 별점 버튼 컨테이너

        let selectedRating = defaultRating;

        // 별점의 수
        const totalstar = 5;

        // 별점 버튼들을 동적으로 생성
        for (let i = 1; i <= totalstar; i++) {
            const starButton = document.createElement('button');
            starButton.classList.add('star-value', 'js-star-btn');
            starButton.type = 'button';
            starButton.value = i;
            starButton.dataset.text = getDescription(i);
            starButton.innerHTML = '★';

            starButtonsContainer.appendChild(starButton);
        }

        const starButtons = starContainer.querySelectorAll('.js-star-btn');

        function getDescription(value) { // 주어진 별점 값에 대한 설명을 반환
            const ratingDescriptions = {
                '1': '나쁨',
                '2': '별로',
                '3': '보통',
                '4': '좋음',
                '5': '최고'
            };
            return ratingDescriptions[value];
        }

        function updateRating(value) { // 선택된 별점을 업데이트하고 관련 요소들을 업데이트함
        	if(document.getElementById("${id}").getAttribute('id') == 'myStarRating'){
        		document.getElementById('scope').value = value;
        	}
            selectedRating = value;
            statusElement.textContent = ` `; // statusElement에 선택된 별점을 표시하고, 선택된 별점 버튼에 selected클래스를 추가함.
											 // descriptionElement에 선택된 별점의 설명을 표시함
            // Remove the 'selected' class from all buttons
            starButtons.forEach(btn => btn.classList.remove('selected'));

            // Add the 'selected' class to buttons up to the clicked button
            for (let i = 0; i < selectedRating; i++) {
                starButtons[i].classList.add('selected');
            }

            // Display the rating description
            descriptionElement.innerHTML = '<strong></strong> ' + getDescription(selectedRating);
        }

       /*  starContainer.addEventListener('mousedown', (event) => {
            if (event.target.classList.contains('js-star-btn')) {
                isClicked = true;
            }
        }); */
        if(document.getElementById("${id}").getAttribute('id') == 'myStarRating'){
	        starContainer.addEventListener('mouseup', (event) => {
	            if (event.target.classList.contains('js-star-btn')) {
	                isClicked = false;
	                updateRating(parseInt(event.target.value));
	            }
	        });
        	
        }

        /* starContainer.addEventListener('mousemove', (event) => {
            if (isClicked && event.target.classList.contains('js-star-btn')) {
                updateRating(parseInt(event.target.value));
            }
        });

        starContainer.addEventListener('mouseleave', () => {
            // When the mouse leaves the container, reset to the selected rating
            if (isClicked) {
                updateRating(selectedRating);
            }
        }); */

        // 초기 설정된 별점 적용
        updateRating(selectedRating);
    }

    // 페이지 로드 시 별점 컴포넌트 초기화
    
    	initializeStarRating_${id}(<c:out value="${defaultRating}" default="${data.scope}" />);
    	
</script>