
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


// ==================================================
/*	const dataTransfer = new DataTransfer();

    $("#uploadimg").change(function(){
       
        let fileArr = document.getElementById("uploadimg").files

        if(fileArr != null && fileArr.length>0){

          // =====DataTransfer 파일 관리========
            for(var i=0; i<fileArr.length; i++){
                dataTransfer.items.add(fileArr[i])
            }
            document.getElementById("files").files = dataTransfer.files;
            console.log("dataTransfer =>",dataTransfer.files);
            console.log("input FIles =>", document.getElementById("files").files);
		// ==========================================
           
        }
         
    })
    // 이거 안씀
    */
//============================================

/*var arr_file_list = Array.from($("#uploadimg")[0].files); // 배열 선언

function fn_fileNameView(){ 
	
	const dataTransfer = new DataTransfer();
	var files = $("#uploadimg")[0].files;
	var add_files = Array.from(files); // 파일을 배열에 넣는다.
	arr_file_list = arr_file_list.concat(add_files); //기존에 배열에 새로운 배열을 합한다.
	arr_file_list.forEach(files => { dataTransfer.items.add(files);}); //배열을 파일로 변환
	
    //[공통-파일명 노출]
	$("#file_name").empty(); // 첨부된 파일명이 보여질 영역 초기화
	$("#uploadimg")[0].files = dataTransfer.files; //변환된 파일을 input file에 넣는다..
	
	var file_leng = $("#uploadimg")[0].files.length; //파일의 길이 

	for(let i = 0 ; i < file_leng ; i++){ // 파일의 길이 만큼 돌면서 값 추가
		$("#file_name").append($("#uploadimg").get(0).files[i].name + "  <a href=\"javascript:fn_delete_fileName('"+$("#uploadimg").get(0).files[i].name+"','"+i+"')\">삭제</a> <br>");
	}
	
}

function fn_delete_fileName(fileName,Del_index){ //파일명과 , 삭제할 파일의 index를 받는다.
	const dataTransfer = new DataTransfer();
	let files = $("#uploadimg")[0].files; // 현재 파일의 목록을 받아서
	let fileArray = Array.from(files); //배열에 저장시킨다.
	fileArray.splice(Del_index,1); //삭제한 파일의 index부터 1개의 배열을 잘라낸다.(= 삭제)
	fileArray.forEach(files => { dataTransfer.items.add(files);}); //배열 변환
	
    //[공통-파일명 노출]
	$("#file_name").empty(); //첨부된 파일명이 보여질 영역 초기화 
	$("#uploadimg")[0].files = dataTransfer.files;//변환된 파일을 input file에 넣는다.
	
	var file_leng = $("#uploadimg")[0].files.length;//파일의 길이

	for(let i = 0 ; i < file_leng ; i++){//파일의 길이만큼 돌면서 값 추가
		$("#file_name").append($("#uploadimg").get(0).files[i].name + "  <a href=\"javascript:fn_delete_fileName('"+$("#uploadimg").get(0).files[i].name+"','"+i+"')\">삭제</a> <br>");
	}
	
}*/

// ============= 이미지 등록시 화면에 출력 ===========
function readURL(input) {
	if (input.files && input.files[0]) { // 파일 입력이 존재하고, 최소한 하나의 파일이 선택되었는지 확인
		var reader = new FileReader();
		reader.onload = function(e) { // 파일을 읽었을 때의 이벤트 핸들러를 설정
			document.getElementById('preview').src = e.target.result; // 선택된 파일의 내용을 일고 읽은 결과를 이미지 미리보기 표시
		};
		reader.readAsDataURL(input.files[0]); // 선택된 파일을 불러옴
	} else {
		if(document.getElementById('reviewImg').value == ""){
			document.getElementById('preview').src = "img/default.jpg"; // 파일이 선택되지 않은경우, 이미지 미리보기 요소를 비움
		}
		else{
			document.getElementById('preview').src = "uploadimg/" + document.getElementById('reviewImg').value; // 파일이 선택되지 않은경우, 이미지 미리보기 요소를 비움
		}
	}
}
