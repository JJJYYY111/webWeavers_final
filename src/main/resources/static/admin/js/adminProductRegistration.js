
/*  <!-- 상세설명 이미지 저장  -->  */
function DetailloadFile(input) {
	let file = input.files[0]; // 선택파일 가져오기

	let newImage = document.createElement("img"); //새 이미지 태그 생성

	//이미지 source 가져오기
	newImage.src = URL.createObjectURL(file);
	newImage.style.width = "100%"; //div에 꽉차게 넣으려고
	newImage.style.height = "100%";
	//newImage.style.objectFit = "cover"; // div에 넘치지 않고 들어가게

	// 이전에 표시된 이미지를 지우기 위해 이미지 표시 div를 찾고, 모든 자식 노드를 삭제합니다.
	let container = document.getElementById('show-image');
	container.innerHTML = "";

	//이미지를 image-show div에 추가
	container.appendChild(newImage);
	currentImage = newImage; // 현재 이미지를 저장

}

function detailDeleteButton() {
	if (currentImage) {
		currentImage.remove(); // 이미지 삭제
	}
}



/*  <!-- 대표이미지 태그 사진 넣는 js  --> */
function loadFile(input) {
	var file = input.files[0]; // 선택된 파일 가져오기

	// 미리 만들어 놓은 div에 text(파일 이름) 추가
	var name = document.getElementById('fileName');
	name.textContent = file.name;

	// 새로운 이미지 div 추가
	var newImage = document.createElement("img");
	newImage.setAttribute("class", 'img');
	newImage.setAttribute("id", 'uploadedImage'); // 새로운 이미지에 ID 추가

	// 이미지 source 가져오기
	newImage.src = URL.createObjectURL(file);

	newImage.style.width = "70%";
	newImage.style.height = "70%";
	newImage.style.objectFit = "contain";

	// 이미지를 image-show div에 추가
	var container = document.getElementById('image-show');
	container.innerHTML = ""; // 이미지가 추가되기 전에 이미지를 초기화하여 이전 이미지를 삭제
	container.appendChild(newImage);
};

function deleteImage() {
	var image = document.getElementById('uploadedImage'); // 추가된 이미지 가져오기
	if (image) {
		image.remove(); // 이미지 삭제
		document.getElementById('fileName').textContent = ""; // 파일 이름 초기화
		document.getElementById('chooseFile').value = ""; // 파일 선택 input 초기화
	}
}

/*  <!-- 카테고리 버튼 스크립트  --> */
document.addEventListener("DOMContentLoaded", function() {


	// 라디오 버튼 요소들을 가져옴
	const categoryRadios = document.querySelectorAll('.category');

	// 라디오 버튼에 대한 이벤트 리스너 추가
	categoryRadios.forEach(function(radio) {
		radio.addEventListener('click', function() {
			// 선택된 라디오 버튼의 값에 따라 세부카테고리 내용 변경
			const subCategoryElement = document.querySelector('.sub-category');
			if (radio.value === 'skin') {
				subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='1' >스킨</input>
<input type='checkbox' name='subCategoryName' value='2' style="margin-left: 1%;">로션/에멀젼</input>
<input type='checkbox' name='subCategoryName' value='3' style="margin-left: 1%;">에센스/세럼/앰플</input>
<input type='checkbox' name='subCategoryName' value='4' style="margin-left: 1%;">크림</input>
<input type='checkbox' name='subCategoryName' value='5' style="margin-left: 1%;">아이케어</input>
<input type='checkbox' name='subCategoryName' value='6' style="margin-left: 1%;">미스트/부스터</input>
<input type='checkbox' name='subCategoryName' value='7' style="margin-left: 1%;">세트</input>
</div>
`;
			} else if (radio.value === 'cleanging') {
				subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='8' >클렌징폼</input>
<input type='checkbox' name='subCategoryName' value='9' style="margin-left: 1%;">클렌징크림/로션/워터</input>
<input type='checkbox' name='subCategoryName' value='10' style="margin-left: 1%;">클렌징젤/오일/티슈</input>
<input type='checkbox' name='subCategoryName' value='11' style="margin-left: 1%;">립,아이 리무버</input>
</div>
`;
			} else if (radio.value === 'mask') {
				subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='12' style="margin-left: 1%;">마사지/워시오프 팩</input>
<input type='checkbox' name='subCategoryName' value='13' style="margin-left: 1%;">필링/스크럽</input>
<input type='checkbox' name='subCategoryName' value='14' style="margin-left: 1%;">시트마스크</input>
<input type='checkbox' name='subCategoryName' value='15' style="margin-left: 1%;">코팩</input>
</div>
`;
			}
		});
	});
});

  /*        <!-- 모달창 -->           */

function swtBasic() {
	 event.preventDefault(); // 폼 제출 이벤트 기본 동작 차단
    return Swal.fire({
    	 title: "상품 등록을 완료했습니다.",
        showCancelButton: false,
        confirmButtonText: '확인',
        confirmButtonColor: '#50C8FF', 
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById('myForm').submit(); // 확인 버튼을 클릭한 경우 폼 제출
        }
    });
}     

/*  취소 버튼 누르면 상품 현황 페이지로 이동 */
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('cancelButton').addEventListener('click', function() {
        window.location.href = "/adminProductStatus"; // your_cancel_url에는 이동하길 원하는 페이지의 URL을 입력합니다.
    });
});
	
