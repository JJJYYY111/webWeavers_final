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
// 페이지 로드 시 초기 값 설정
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
});