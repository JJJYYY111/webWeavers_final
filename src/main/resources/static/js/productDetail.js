/* 좋아요순 버튼을 누르면 정렬하게 */
$("#like").on("click", function() {
    // 원하는 형식으로 날짜를 포맷하는 함수
    function formatDate(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더해줍니다.
        var day = date.getDate();

        // 월과 일이 한 자리 수인 경우 앞에 0을 붙여줍니다.
        if (month < 10) {
            month = '0' + month;
        }
        if (day < 10) {
            day = '0' + day;
        }

        return year + '-' + month + '-' + day;
    }

    $.ajax({
        type: "POST",
        url: "/reviewOrderedList",
        data: {
            'productPK' : document.getElementById('productNumber').value,
            'searchCondition': 'reviewLike'
        },
        dataType: 'json',
        success: function(datas) {
            var tableHTML = '';
            datas.forEach(function(data) {
                var parts = data.reviewRegdate.split(/[\s,]+/); // "4월 3, 2024"와 같은 문자열을 공백이나 쉼표를 기준으로 분할하여 parts 배열에 저장
                var monthIndex = parseInt(parts[0].replace("월", "")) - 1; // 월을 숫자로 변환
                var day = parseInt(parts[1].replace(",", "")); // 일을 숫자로 변환
                var year = parseInt(parts[2]); // 연도를 숫자로 변환

                var reviewDate = new Date(year, monthIndex, day); // Date 객체 생성

                // YYYY-MM-DD 형식으로 변환
                var formattedDate = formatDate(reviewDate);

                tableHTML += `
                    <div class="review_item">
                        <div class="media">
                            <div class="media-body">
                                <span>${formattedDate}</span>
                                <br>
                                <br>
                                <h4>
                                    <span>작성자 : ${data.memberNickname}</span>
                                    <button onclick="reviewLikeClick('${data.reviewPK}','${data.sessionMid}')" class="review-btn-${data.reviewPK}" style="margin-top: 3px; background: #ffffff; border: none;">
                                        <img src="${data.reviewLike == 1 ? '/resources/reviewLikeRed.png' : '/resources/reviewLike.png'}" alt="좋아요" style="width: 25px;">
                                    </button>
                                    <span id="reviewCnt${data.reviewPK}">${data.reviewLikeCnt}</span>
                                </h4>
                                <br>
                                <input class="starValue" type="hidden" name="reviewScope" id="scope_${data.reviewPK}" value="${data.reviewScope}">
                                <!-- 해당 회원이 작성한 리뷰 별점 -->
                                <star:star id="${data.reviewPK}" defaultRating="${data.reviewScope}" />
                            </div>
                            <div class="d-flex">
                                <!-- 리뷰 작성할 때 사용자가 등록한 이미지 -->
                                ${data.reviewImg ? `<div class="feature-img"><img style="max-width: 200%; max-height: 200px;" class="img-fluid" src="${data.reviewImg}" alt="리뷰작성 이미지"></div>` : ''}
                            </div>
                        </div>
                        <br>
                        <div>
                            <textarea class="col-lg-12" rows="3" name="reviewContent" placeholder="리뷰 내용" readonly style="resize: none; border: 2px solid gray; border-radius: 5px; line-height: 2; font-size: large;">${data.reviewContent}</textarea>
                        </div>
                    </div>
                `;
            });
            $(".review_list").html(tableHTML);
        },
        error: function(error) {
            console.log('실패');
            console.log('에러의 종류:' + error);
        }
    });
});


/*<div class="review_item">
                        <span>${data.reviewRegdate}</span>
                        <h4>
                            작성자 : ${data.memberNickname}
                            <button onclick="reviewLikeClick('${data.reviewPK}','${sessionMid}')" class="review-btn-${data.reviewPK}" style="margin-top: 3px; background: #ffffff; border: none;">
                                <img src="${data.reviewLike == 1 ? '/resources/reviewLikeRed.png' : '/resources/reviewLike.png'}" alt="좋아요" style="width: 25px;">
                            </button>
                            ${data.reviewLikeCnt}
                        </h4>
                        <div class="d-flex">
                            ${data.reviewImg ? `<div class="feature-img"><img style="max-width: 200%; max-height: 200px;" class="img-fluid" src="${data.reviewImg}" alt="리뷰작성 이미지"></div>` : ''}
                        </div>
                        <div><textarea class="col-lg-12" rows="3" name="reviewContent" placeholder="리뷰 내용" readonly style="resize: none; border: 2px solid gray; border-radius: 5px; line-height: 2; font-size: large;">${data.reviewContent}</textarea></div>
                    </div>*/

/* 최신순 버튼을 누르면 정렬하게 */

$("#recent").on("click", function() {
	 function formatDate(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더해줍니다.
        var day = date.getDate();

        // 월과 일이 한 자리 수인 경우 앞에 0을 붙여줍니다.
        if (month < 10) {
            month = '0' + month;
        }
        if (day < 10) {
            day = '0' + day;
        }

        return year + '-' + month + '-' + day;
    }
	 var sessionMid = `${sessionMid}`;
    $.ajax({
        type: "POST",
        url: "/reviewOrderedList",
        data: {
            'productPK': document.getElementById('productNumber').value,
            'searchCondition': 'regdate',
            'sessionMid': sessionMid
        },
        dataType: 'json',
        success: function(datas) {
			console.log('데이터: '+ datas);
            var reviewListHTML = '';
            datas.forEach(function(data) {
				 var parts = data.reviewRegdate.split(/[\s,]+/); // "4월 3, 2024"와 같은 문자열을 공백이나 쉼표를 기준으로 분할하여 parts 배열에 저장
                var monthIndex = parseInt(parts[0].replace("월", "")) - 1; // 월을 숫자로 변환
                var day = parseInt(parts[1].replace(",", "")); // 일을 숫자로 변환
                var year = parseInt(parts[2]); // 연도를 숫자로 변환

                var reviewDate = new Date(year, monthIndex, day); // Date 객체 생성

                // YYYY-MM-DD 형식으로 변환
                var formattedDate = formatDate(reviewDate);
                reviewListHTML += `
                    <div class="review_item">
                        <div class="media">
                            <div class="media-body">
                               <span>${formattedDate}</span>
                                <br>
                                <br>
                                <h4>
                                    <span>작성자 : ${data.memberNickname}</span>
                                    <button onclick="reviewLikeClick('${data.reviewPK}','` + document.getElementById('sessionMid').value + `')" class="review-btn-${data.reviewPK}" style="margin-top: 3px; background: #ffffff; border: none;">
                                        <img src="${data.reviewLike == 1 ? '/resources/reviewLikeRed.png' : '/resources/reviewLike.png'}" alt="좋아요" style="width: 25px;">
                                    </button>
                                    <span id="reviewCnt${data.reviewPK}">${data.reviewLikeCnt}</span>
                                </h4>
                                <br>
                                <input class="starValue" type="hidden" name="reviewScope" id="scope_${data.reviewPK}" value="${data.reviewScope}">
                                <!-- 해당 회원이 작성한 리뷰 별점 -->
                                <star:star id="${data.reviewPK}" defaultRating="${data.reviewScope}" />
                            </div>
                            <div class="d-flex">
                                <!-- 리뷰 작성할 때 사용자가 등록한 이미지 -->
                                ${data.reviewImg ? `<div class="feature-img"><img style="max-width: 200%; max-height: 200px;" class="img-fluid" src="${data.reviewImg}" alt="리뷰작성 이미지"></div>` : ''}
                            </div>
                        </div>
                        <br>
                        <div>
                            <textarea class="col-lg-12" rows="3" name="reviewContent" placeholder="리뷰 내용" readonly style="resize: none; border: 2px solid gray; border-radius: 5px; line-height: 2; font-size: large;">${data.reviewContent}</textarea>
                        </div>
                    </div>
                `;
            });
            $(".review_list").html(reviewListHTML);
        },
        error: function(error) {
            console.log('실패');
            console.log('에러의 종류:' + error);
        }
    });
});


/* 리뷰 좋아요 */

function reviewLikeClick(reviewPK, memberID) {
    if (memberID == null || memberID == '' || memberID == 'undefined') { // 로그아웃 상태
        alert('리뷰 좋아요 실패, 로그인 후 이용가능합니다!');
    } else {
        $.ajax({
            type: "POST",
            url: "/reviewLike",
            data: {
                'reviewPK': reviewPK
            },
            dataType: "text",
            success: function(data) {
                //data가 1이면 좋아요 성공
                var reviewLikeCnt = parseInt(document.getElementById('reviewCnt'+reviewPK).innerText);

                if (data == 1) {
                    document.getElementById('reviewCnt'+reviewPK).innerText = reviewLikeCnt+1; //리뷰 좋아요 수 증가
                    document.querySelectorAll('button.review-btn-' + reviewPK).forEach(button => {
                        button.innerHTML = '<img src="/resources/reviewLikeRed.png" alt="좋아요" style="width: 25px;">';
                    });
                }
                //data가 2면 좋아요 취소
                else if (data == 2) {
                    document.getElementById('reviewCnt'+reviewPK).innerText = reviewLikeCnt-1; // 리뷰 좋아요 수 감소
                    document.querySelectorAll('button.review-btn-' + reviewPK).forEach(button => {
                        button.innerHTML = '<img src="/resources/reviewLike.png" alt="좋아요를 누르지 않은 상태" style="width: 25px;">';
                    });
                } else if (data == 0) {
                    alert('로그인 후 이용가능합니다.')
                }
            },
            error: function(error) {
                console.log('에러발생');
                console.log('에러종류: ' + error);
            }
        });
    }
}

/* 리뷰  */
var reviewForm = document.getElementById('reviewForm');
if(reviewForm != null){
	
reviewForm.addEventListener('submit', function(event) {
	const message = document.getElementById('content').value.trim();
	const messageError = document.getElementById('messageError');

	// 리뷰 내용이 비어 있는지 확인
	if (message === '') {
		messageError.textContent = '리뷰 내용을 입력하세요.';
		event.preventDefault(); // 제출 중지
	} else {
		messageError.textContent = ''; // 에러 메시지 제거
	}
});
}


function uploadImage() {
	const fileInput = document.getElementById('imageInput');
	const formData = new FormData();
	formData.append('image', fileInput.files[0]);

	// 서버로 이미지 업로드 요청을 보내고 이미지 URL을 얻어옴
	// 이후 해당 URL을 이미지 태그의 src 속성에 할당하여 프리뷰를 표시할 수 있음
}

// 리뷰 별점 개수 및 평균값
    
window.onload = function(){ // 화면이 로드가 완료되면 실행
	
    let starVal1 = 0;
    let starVal2 = 0;
    let starVal3 = 0;
    let starVal4 = 0;
    let starVal5 = 0;
    let cnt = 0;
    let starAvg = 0;
    
    var elements = document.querySelectorAll('.starValue'); // 해당 클래스를 가진 요소들을 배열로 저장
    for(e of elements){ // 반복문을 사용하여 위의 배열을 사용
		switch(parseInt(e.value)) // switch-case문을 통해서 조건에 따른 기능을 수행
		{
			case 1 : 
			starVal1 += 1;
			cnt += 1;
			break;
			
			case 2 :
			starVal2 += 1;
			cnt += 1;
			break;
			
			case 3 : 
			starVal3 += 1;
			cnt += 1;
			break;
			
			case 4 : 
			starVal4 += 1;
			cnt += 1;
			break;
			
			case 5 : 
			starVal5 += 1;
			cnt += 1;
			break;
		}
		// 콘솔로 확인해보기
		console.log("starVal1 " + starVal1 + " " + typeof(starVal1));
		console.log("starVal2 " + starVal2 + " " + typeof(starVal2));
		console.log("starVal3 " + starVal3 + " " + typeof(starVal3));
		console.log("starVal4 " + starVal4 + " " + typeof(starVal4));
		console.log("starVal5 " + starVal5 + " " + typeof(starVal5));		
		console.log(cnt + typeof(cnt));
		console.log(starAvg + typeof(starAvg));
	}
		if(cnt > 0){ // 리뷰개수가 0보다 크면 평균값을 계산하여 숫자로 출력
			let totalAvg = ((starVal1*1)+(starVal2*2)+(starVal3*3)+(starVal4*4)+(starVal5*5))*1.0/cnt
			starAvg = totalAvg.toFixed(1);
		}
		else{ // 리뷰개수가 0일경우 Nan을 방지하기위해 평균값을 0으로 초기화
			starAvg = 0;
		}
		
		// id로 해당 요소를 검색하여 해당 요소들안에 값을 입력
		document.getElementById('starVal1').innerText = starVal1;
		document.getElementById('starVal2').innerText = starVal2;
		document.getElementById('starVal3').innerText = starVal3;
		document.getElementById('starVal4').innerText = starVal4;
		document.getElementById('starVal5').innerText = starVal5;
		document.getElementById('avgScore').innerText = starAvg;		
}