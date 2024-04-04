/*===============필터 검색 회원 이름 유효성 모달 창==============================*/
// 회원이름 입력 필드에 숫자와 특수문자를 입력하지 못하도록 유효성 검사
document.getElementById("memberName").addEventListener("input", function(event) {
    let inputValue = this.value;

    // 정규 표현식을 사용하여 입력된 값이 숫자 또는 특수문자를 포함하는지 확인
    if (/[0-9~!@#$%^&*()_+|<>?:{}]/.test(inputValue)) {
        // 숫자 또는 특수문자가 포함되어 있으면 모달 창을 띄움
        document.getElementById('alertMessageMemberName').textContent = '숫자와 특수문자는 입력할 수 없습니다.';
        $('#alertModalMemberName').modal('show'); // 모달 창 띄우기
        
        // 숫자 또는 특수문자를 제거하기 위해 정규 표현식을 사용하여 문자만 남기고 지움
        this.value = inputValue.replace(/[0-9~!@#$%^&*()_+|<>?:{}]/g, '');
    }
});

/*==========================================================================*/
// 전화번호 입력 필드에 유효성 검사를 추가
document.getElementById("registPhone").addEventListener("input", function(event) {
    let inputValue = this.value;

    // 입력 값에서 숫자만 남기고 다른 문자를 제거
    let cleanedValue = inputValue.replace(/\D/g, '');

    // 입력 값이 11자리 이상이면 모달 창을 띄우고 입력 값 자름
    if (cleanedValue.length > 11) {
        document.getElementById('phoneValidationMessage').textContent = '11자리 이상의 전화번호는 입력할 수 없습니다.';
        $('#phoneValidationModal').modal('show');
        cleanedValue = cleanedValue.slice(0, 11);
    }

    this.value = cleanedValue;
});