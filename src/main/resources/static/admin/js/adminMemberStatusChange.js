/* ===================유효성 검사=============================================*/
// 회원이름 입력 필드에 숫자와 특수문자를 입력하지 못하도록 유효성 검사
document.getElementById("memberName").addEventListener("input", function(event) {
    let inputValue = this.value;

    // 숫자와 특수문자만 제거하여 설정
    this.value = inputValue.replace(/[0-9~!@#$%^&*()_+|<>?:{}]/g, '');

    // 정규 표현식을 사용하여 입력된 값이 숫자 또는 특수문자를 포함하는지 확인
    if (/[0-9~!@#$%^&*()_+|<>?:{}]/.test(inputValue)) {
        // 숫자 또는 특수문자가 포함되어 있으면 모달 창을 띄움
        document.getElementById('alertMessageMemberName').textContent = '숫자와 특수문자는 입력할 수 없습니다.';
        $('#alertModalMemberName').modal('show'); // 모달 창 띄우기
    }
});

// 모달 창이 닫힌 후에도 이전 값으로 복원
$('#alertModalMemberName').on('hidden.bs.modal', function () {
    // 여기에 복원할 필요 없음
});

    
// 전화번호 입력 필드에 숫자만 입력할 수 있도록 유효성 검사
document.getElementById("registPhone").addEventListener("input", function(event) {
    let inputValue = this.value;

    // 숫자만 남기고 다른 문자는 모두 제거하여 설정
    this.value = inputValue.replace(/\D/g, '');

    // 정규 표현식을 사용하여 입력된 값이 숫자만 포함하는지 확인
    if (/\D/.test(inputValue)) {
        // 숫자가 아닌 문자가 포함되어 있으면 모달 창을 띄움
        document.getElementById('alertMessagePhone').textContent = '숫자만 입력할 수 있습니다.';
        $('#alertModalPhone').modal('show'); // 모달 창 띄우기
    }
});