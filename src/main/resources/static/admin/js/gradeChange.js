/*
		document.addEventListener('DOMContentLoaded', function() {
			var selectElement = document.getElementById('inlineFormCustomSelect');
			var textField = document.getElementById('registGreadPK');
			var initialGradePK = textField.value;

			// 함수를 사용하여 초기 값 설정
			function setInitialGrade(value) {
				switch (value) {
				case '1':
					textField.value = '탈퇴';
					break;
				case '2':
					textField.value = '관리자';
					break;
				case '3':
					textField.value = '브론즈';
					break;
				case '4':
					textField.value = '실버';
					break;
				case '5':
					textField.value = '골드';
					break;
				default:
					textField.value = '예외발생';
				}
			}

			// 페이지 로드 시 초기 값 설정
			setInitialGrade(initialGradePK);

			// select 요소의 값이 변경될 때마다 실행
			selectElement.addEventListener('change', function() {
				var selectedValue = selectElement.value;
				setInitialGrade(selectedValue);
			});
		});*/
// 페이지 로드 시 초기 값 설정 및 이벤트 처리
document.addEventListener('DOMContentLoaded', function() {
    var selectElement = document.getElementById('inlineFormCustomSelect');
    var textField = document.getElementById('registGradePK');
    var initialGradePK = textField.value;

    // 함수를 사용하여 초기 값 설정
    function setInitialGrade(value) {
		console.log("등급 확인");
        switch (value) {
            case '1':
                textField.value = '탈퇴';
                break;
            case '2':
                textField.value = '관리자';
                break;
            case '3':
                textField.value = '브론즈';
                break;
            case '4':
                textField.value = '실버';
                break;
            case '5':
                textField.value = '골드';
                break;
            default:
                textField.value = '예외발생';
        }
    }

    // 페이지 로드 시 초기 값 설정
    setInitialGrade(initialGradePK);

    // select 요소의 값이 변경될 때마다 실행
    selectElement.addEventListener('change', function() {
        var selectedValue = selectElement.value;
        setInitialGrade(selectedValue);
    });

    // 등급 정보 배열
    var grades = [
        { value: "1", label: "탈퇴" },       // 등급 1: 탈퇴
        { value: "2", label: "관리자" },     // 등급 2: 관리자
        { value: "3", label: "브론즈" },     // 등급 3: 브론즈
        { value: "4", label: "실버" },       // 등급 4: 실버
        { value: "5", label: "골드" }        // 등급 5: 골드
    ];

    // 셀렉트 박스 요소 초기화
    selectElement.innerHTML = '';

    // 등급 정보 배열을 반복하여 셀렉트 박스에 옵션 추가
    grades.forEach(function(grade) {
        var option = document.createElement('option');    // 새로운 옵션 요소 생성
        option.value = grade.value;                      // 옵션의 값 설정
        option.text = grade.label;                       // 옵션에 표시될 텍스트 설정
        if (grade.value === initialGradePK) {            // 현재 등급이 선택된 등급과 일치하는지 확인
            option.selected = true;                      // 일치하는 경우 해당 옵션을 선택 상태로 설정
        }
        selectElement.add(option);                       // 셀렉트 박스에 옵션 추가
    });
});