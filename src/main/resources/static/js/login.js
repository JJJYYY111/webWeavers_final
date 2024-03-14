// [로그인 - 유효성 검사]
	
	// 페이지 로드 후 함수 실행
document.addEventListener('DOMContentLoaded', function() {
  // document에서 name이 loginForm인 <form>을 찾아서
  // 로그인 폼을 submit할 때 함수 실행
  document.loginForm.onsubmit = function() {
    console.log('[로그] 로그인 유효성검사');
    validate();
  };

  function validate() {
    var id = document.getElementById('id').value;
    var password = document.getElementById('password').value;
    var errorBox = document.getElementById('errorBox');

    // 아이디와 비밀번호가 비어있지 않은지 확인
    if (id.trim() === '' || password.trim() === '') {
      errorBox.textContent = '아이디와 비밀번호를 입력해주세요.';
      return;
    }

    // 아이디 유효성 검사 (영문 소문자와 숫자만 허용)
    var idRegex = /^[a-z0-9]+$/;
    if (!idRegex.test(id)) {
      errorBox.textContent = '아이디는 영문 소문자와 숫자로만 이루어져야 합니다.';
      return;
    }

    // 비밀번호 유효성 검사 (영문 대소문자, 숫자, 특수문자 조합, 최소 8자리 이상)
    var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordRegex.test(password)) {
      errorBox.textContent = '비밀번호는 영문 대소문자, 숫자, 특수문자 조합이어야 하며, 최소 8자리 이상이어야 합니다.';
      return;
    }

    // 모든 유효성 검사 통과
    errorBox.textContent = '';
    // 로그인 성공시 다음 작업 수행
    
    
  }
});


