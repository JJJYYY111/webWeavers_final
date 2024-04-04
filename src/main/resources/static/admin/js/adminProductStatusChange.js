
/* <!-- 상세설명 이미지  -->  */
function DetailloadFile(input) {
	let file = input.files[0]; // 선택파일 가져오기

	let newImage = document.createElement("img"); //새 이미지 태그 생성

	//이미지 source 가져오기
	newImage.src = URL.createObjectURL(file);
	newImage.style.width = "100%"; //div에 꽉차게 넣으려고
	newImage.style.height = "100%";

	// 이전에 표시된 이미지를 지우기 위해 이미지 표시 div를 찾고, 모든 자식 노드를 삭제
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


/* <!-- 모달창 --> */
function swtBasic() {
	event.preventDefault(); // 폼 제출 이벤트 기본 동작 차단
	return Swal.fire({
		title: "상품 수정을 완료했습니다.",
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
		window.location.href = "/adminProductStatus"; 
	});
});

/* <!-- 대표 이미지 태그 사진  --> */
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

/* <!-- 카테고리 선택 받아오기/등록하기  --> */
document.addEventListener('DOMContentLoaded', function () {
    console.log('들어온거 확인');
    // 저장된 카테고리 및 서브 카테고리 가져오기
   
    // 카테고리 체크 박스 선택
    var categoryRadios = document.querySelectorAll('input[name="categoryName"]');
    categoryRadios.forEach(function (radio) {
        console.log('카테고리 체크 확인');
        if (radio.value === selectedCategory) {
            radio.checked = true;
        }
    }); 

  // 두 번째 스크립트 실행
    selec(selectedCategory);

});

 function selec(selectedCategory) {
	const categoryRadios = document.querySelectorAll('.category');
	categoryRadios.forEach(function (radio) {
        const subCategoryElement = document.querySelector('.sub-category');
        console.log('카테고리'+radio.value);
        if (selectedCategory === '스킨케어') {
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
        } else if (selectedCategory === '클렌징') {
            subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='8' >클렌징폼</input>
<input type='checkbox' name='subCategoryName' value='9' style="margin-left: 1%;">클렌징크림/로션/워터</input>
<input type='checkbox' name='subCategoryName' value='10' style="margin-left: 1%;">클렌징젤/오일/티슈</input>
<input type='checkbox' name='subCategoryName' value='11' style="margin-left: 1%;">립,아이 리무버</input>
</div>
`;
        } else if (selectedCategory === '마스크팩') {
            subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='12' style="margin-left: 1%;">마사지/워시오프 팩</input>
<input type='checkbox' name='subCategoryName' value='13' style="margin-left: 1%;">필링/스크럽</input>
<input type='checkbox' name='subCategoryName' value='14' style="margin-left: 1%;">시트마스크</input>
<input type='checkbox' name='subCategoryName' value='15' style="margin-left: 1%;">코팩</input>
</div>
`;
        }
        console.log('로그1 ['+productPK+']');
        //서브 카테고리 체크박스 선택되는거 비동기
        $.ajax({
			
			type: "POST",
			url: "adminCategoryCheckbox",
			data: {
                'productPK': productPK
                
            },
            
            dataType: 'json',
            	
            	success:function(datas) {
            		
            		console.log('로그2 ['+datas+']');
        
            		$.each(datas, function(index, data){
            		    console.log("로그3 ["+data.subCategoryPK+']');
            	        const selectedSubCategoryCheckbox = subCategoryElement.querySelector('input[name="subCategoryName"][value="' + data.subCategoryPK + '"]');
            	        if (selectedSubCategoryCheckbox !== null) {
            	            selectedSubCategoryCheckbox.checked = true;
            	        }
            			
            		});
            		
            },
            error: function (error) {
            	
                console.log('에러의 종류:' + error)
            }
		});
		
	
	});
 	
        
	}
	
	
   document.addEventListener("DOMContentLoaded", function () {

     const categoryRadios = document.querySelectorAll('.category');

     categoryRadios.forEach(function (radio) {
       radio.addEventListener('click', function () {
         // 선택된 라디오 버튼의 값에 따라 세부카테고리 내용 변경
         const subCategoryElement = document.querySelector('.sub-category');
         if (radio.value === '스킨케어') {
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
         } else if (radio.value === '클렌징') {
           subCategoryElement.innerHTML = `
<div style="width: 120%;">
<input type='checkbox' name='subCategoryName' value='8' >클렌징폼</input>
<input type='checkbox' name='subCategoryName' value='9' style="margin-left: 1%;">클렌징크림/로션/워터</input>
<input type='checkbox' name='subCategoryName' value='10' style="margin-left: 1%;">클렌징젤/오일/티슈</input>
<input type='checkbox' name='subCategoryName' value='11' style="margin-left: 1%;">립,아이 리무버</input>
</div>
`;
         } else if (radio.value === '마스크팩') {
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
