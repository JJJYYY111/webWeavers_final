
document.getElementById('reviewForm').addEventListener('submit', function(event) {
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


function uploadImage() {
	const fileInput = document.getElementById('imageInput');
	const formData = new FormData();
	formData.append('image', fileInput.files[0]);

	// 서버로 이미지 업로드 요청을 보내고 이미지 URL을 얻어옴
	// 이후 해당 URL을 이미지 태그의 src 속성에 할당하여 프리뷰를 표시할 수 있음
}

// ============= 이미지 등록시 화면에 출력 ===========
function readURL(input) {
	if (input.files && input.files[0]) { // 파일 입력이 존재하고, 최소한 하나의 파일이 선택되었는지 확인
		var reader = new FileReader();
		reader.onload = function(e) { // 파일을 읽었을 때의 이벤트 핸들러를 설정
			document.getElementById('preview').src = e.target.result; // 선택된 파일의 내용을 일고 읽은 결과를 이미지 미리보기 표시
		};
		reader.readAsDataURL(input.files[0]); // 선택된 파일을 불러옴
	} else {
		if(document.getElementById('prevImg').value == ""){
			document.getElementById('preview').src = "img/default.jpg"; // 파일이 선택되지 않은경우, 이미지 미리보기 요소를 비움
		}
		else{
			document.getElementById('preview').src = "uploadimg/" + document.getElementById('prevImg').value; // 파일이 선택되지 않은경우, 이미지 미리보기 요소를 비움
		}
	}
}
