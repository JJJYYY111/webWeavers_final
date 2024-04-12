/* ===================유효성 검사=============================================*/
// 회원이름 입력 필드에 숫자와 특수문자를 입력하지 못하도록 유효성 검사
    document.getElementById("memberName").addEventListener("input", function(event) {
        let inputValue = this.value;

        // 정규 표현식을 사용하여 입력된 값이 숫자 또는 특수문자를 포함하는지 확인
        if (/[0-9~!@#$%^&*()_+|<>?:{}]/.test(inputValue)) {
            // 숫자 또는 특수문자가 포함되어 있으면 모달 창을 띄움
            document.getElementById('alertMessageMemberName').textContent = '숫자와 특수문자는 입력할 수 없습니다.';
            $('#alertModalMemberName').modal('show'); // 모달 창 띄우기
            this.value = ''; // 입력 필드를 비움
        }
    });
    
    // 입력 필드 선택
    var memberIDInput = document.getElementById('memberID');

    // 입력 필드에 이벤트 리스너 추가
    memberIDInput.addEventListener('input', function(event) {
        var inputValue = event.target.value; // 입력된 값 가져오기

        // 정규 표현식을 사용하여 입력된 값이 한글 또는 특수문자를 포함하는지 확인
        if (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|~!@#$%^&*()_+|<>?:{}]/.test(inputValue)) {
            // 한글 또는 특수문자가 포함되어 있으면 모달 창을 띄움
            document.getElementById('alertMessage').textContent = '한글과 특수문자는 입력할 수 없습니다.';
            $('#alertModal').modal('show'); // 모달 창 띄우기
            event.target.value = ''; // 입력 필드를 비움
        }
    }); 
/* ===================유효성 검사=============================================*/

/* ==================회원 검색 필터===========================================*/
$("#search").on("click", function() {
	// 사용자가 검색버튼을 누르면
	console.log('[로그1] 서치 들어옴');
	var selectElement = document.getElementById("gradeName"); 
	// select 요소 가져오기
	var gradeName = selectElement.options[selectElement.selectedIndex].text; 
	// 선택된 option의 값 가져오기
	if (gradeName == '선택') {
		gradeName = "";
	}
	var memberName = document.getElementById("memberName").value;
	if (memberName == null) {
		memberName == "";
	}

	console.log('로그2 [' + memberName + ']');

	var memberID = document.getElementById("memberID").value;
	// memberRegdate의 위에서 value를 가져옴
	if (memberID == null) {
		memberID == "";
	}
	console.log('로그3 [' + memberID + ']');

	$.ajax({

		type: "POST",
		url: "/async/adminMemberSearch",
		data: {
			'gradeName': gradeName,
			'memberName': memberName,
			'memberID': memberID
		},
		dataType: 'json',
		success: function(datas) {
			console.log('로그4 [' + datas + ']');
			var tableHTML = "<table id='search' border='1'>";
			// 테이블 다시 만듦
			tableHTML += "<thead><tr><th>ID</th><th>이름</th><th>등급</th><th>생일</th><th>가입일</th><th>email수신동의</th></tr></thead>";
			tableHTML += "<tbody>";
			for (var i = 0; i < datas.length; i++) {
				tableHTML += "<tr class='memberTable' onclick=\"location.href='adminMemberStatusChange?memberID=" + datas[i].memberID + "'\" id='" + datas[i].memberID + "' style='cursor: pointer;'>";
				tableHTML += "<td>" + datas[i].memberID + "</td>";
				tableHTML += "<td>" + datas[i].memberName + "</td>";
				tableHTML += "<td>" + datas[i].gradeName + "</td>";
				tableHTML += "<td>" + datas[i].memberBirth + "</td>";
				tableHTML += "<td>" + datas[i].memberRegdate + "</td>";
				tableHTML += "<td>" + datas[i].memberMarketing + "</td>";
				tableHTML += "</tr>";
			}
			tableHTML += "</tbody></table>";
			$("#memberTable").html(tableHTML);

			$("#memberNav").remove();

			// 검색 후 페이징 처리 함수 호출
			var rowPerPage = 10;
			var $memberTable = $('#memberTable');
			if ($memberTable.length) {
				createPagination($memberTable, 'memberNav', rowPerPage);
			}

		},
		error: function(error) {

			console.log('실패')
			console.log('에러의 종류:' + error)
		}
	});


});


