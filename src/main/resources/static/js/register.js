// [회원가입 - 유효성 검사]
if(document.joinForm){
	//console.log('회원가입 유효성');
	checkJoinForm();
	registFromPageCheck();
}
// [회원탈퇴 - 유효성 검사]
else if(document.unregisterForm){
	//console.log('회원탈퇴 유효성');
	checkUnregisterForm();
}
// [회원확인 - 유효성 검사]
else if(document.pwCheckForm){
	//console.log('회원확인 유효성');
	checkPwForm();
}
// [개인정보수정 - 유효성 검사]
else if(document.profileChangeForm){	
	registFromPageCheck()												// 유효성 검사 (값 형식 및 중복 검사) 함수 사용
	document.profileChangeForm.onsubmit = function(){					// 유효성 (미)통과 여부에 따른 폼 제출
        var inputElIds = ['registNickname', 'registMpw', 'confirmMpw'];	// <input>의 id값 배열로 저장
        for (var inputElId of inputElIds) {
            var inputEl = document.getElementById(inputElId);			// <input> 요소 저장
            if (inputEl.dataset.formCheck == 'N') {						// <input>의 formCheck값이 N이면
                alert('형식에 맞춰주세요.');
                return false;											// 폼 제출 불가
            }
		}
	}
}

//---------- 회원가입 - required ----------
function checkJoinForm() {
	// document에서 name이 joinForm인 <form>을 찾아서
	// 회원가입 폼을 submit할때 함수 실행
	document.joinForm.onsubmit = function() {

		// 약관 동의 필수 체크 유효성 검사 ↓
		for (box of joinForm.selector) {
			if (!box.checked) {
				alert('필수 약관에 모두 동의해야 가입할 수 있습니다.')
				box.focus();
				return false;
			}
		}

		// 값이 없을 때 유효성 검사 ↓
		if ((!joinForm.memberName.value) || joinForm.memberName.value.trim() == '') {
			alert('이름을 입력하세요.');
			joinForm.memberName.focus();
			console.log('이름');
			return false;
		}
		if (!joinForm.memberID.value) {
			alert('아이디를 입력하세요.');
			joinForm.memberID.focus();
			return false;
		}
		
		if (!joinForm.memberPassword.value || joinForm.memberPassword.value == '') {
			alert('비밀번호를 입력하세요.');
			joinForm.memberPassword.focus();
			return false;
		}
		if (!joinForm.confirmMpw.value) {
			alert('비밀번호 재확인을 위해 입력하세요.');
			joinForm.confirmMpw.focus();
			return false;
		}
		if (!joinForm.memberNickname.value) {
			alert('닉네임을 입력하세요.');
			joinForm.memberNickname.focus();
			return false;
		}
		if (!joinForm.addressZonecode.value) {
			alert('주소 입력하세요.');
			joinForm.addressZonecode.focus();
			return false;
		}
		if (!joinForm.memberPhone.value) {
			alert('휴대폰 번호를 입력하세요.');
			joinForm.memberPhone.focus();
			return false;
		}
		if (!joinForm.memberBirth.value) {
			alert('생년월일을 입력하세요.');
			joinForm.memberBirth.focus();
			return false;
		}
		if (!joinForm.memberEmail.value) {
			alert('이메일을 입력하세요.');
			joinForm.memberEmail.focus();
			return false;
		}
		
		// 폼 유효성 검사
		var flag;
		joinForm.querySelectorAll('input').forEach(inputEl => {
			// console.log(index, inputEl)
			
			// <input>의 dataset의 formCheck가 null이 아니고, 값이 'N'이라면 --> 폼 제출 불가
			if(inputEl.dataset.formCheck != null && inputEl.dataset.formCheck == 'N'){
				flag = 'N'
			}
			
		})
		
		// console.log(flag)
		if(flag == 'N'){
			alert('형식에 맞춰주세요.');
			return false;
		}
		
	}
}

//--------------------회원가입 유효성 검사 (요소 저장 및 함수 사용)--------------------
function registFromPageCheck(){
	
	// 요소 저장
	var registIdEl = document.getElementById('registMid')						// 아이디 요소 저장
	var registNickEl = document.getElementById('registNickname')				// 닉네임 요소 저장
	var registPhoneEl = document.getElementById('registPhone')					// 휴대폰 요소 저장
	var certificationNumEl = document.getElementById('certificationNum')		// 문자 인증번호 요소 저장
	var registEmailEl = document.getElementById('registEmail')					// 이메일 요소 저장
	var registPwEl = document.getElementById('registMpw')						// 비밀번호 요소 저장
	var confirmPwEl = document.getElementById('confirmMpw')						// 비밀번호 확인 요소 저장
	
	// 유효성 미통과 --> N, 통과 --> Y
	registIdEl.dataset.formCheck = 'N'
	registNickEl.dataset.formCheck = 'Y'
	registPhoneEl.dataset.formCheck = 'N'
	registEmailEl.dataset.formCheck = 'N'
	registPwEl.dataset.formCheck = 'Y'
	confirmPwEl.dataset.formCheck = 'Y'
	certificationNumEl.dataset.formCheck = 'N'
	
	// 정규식
	const phoneRegex = /^01[0-9]{1}[0-9]{4}[0-9]{4}$/
	const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
	const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,12}$/
	
	// 함수 사용
	registIDCheck(registIdEl, 'idCheck', 'async/idCheck', 'ID')									// 함수 사용 (아이디 중복 검사)
	registNickNameCheck(registNickEl, 'nickCheck', 'async/nickNameCheck', '닉네임')				// 함수 사용 (닉네임 중복 검사)
	registFormCheck(registPhoneEl, 'phoneCheck', phoneRegex, '올바른 형식이 아닙니다. 다시 입력해주세요')		// 함수 사용 (휴대폰 형식 유효성)
	smsCertification(certificationNumEl, 'confirmSmsNumCheck')									// 함수 사용 (문자 인증번호 확인 유효성)
	registFormCheck(registEmailEl, 'emailCheck', emailRegex, '올바른 이메일 형식을 입력하세요')				// 함수 사용 (이메일 형식 유효성)
	registFormCheck(registPwEl, 'pwCheck', passwordRegex, '영문,숫자,특수문자가 포함된 8 ~ 12글자로 입력하세요')// 함수 사용 (비밀번호 형식 유효성)
	pwValueCheck(confirmPwEl, registPwEl, 'confirmPwCheck')										// 함수 사용 (비밀번호 확인 유효성)	

	
}

//---------- 값 형식 유효성 검사 함수 ----------
function registFormCheck(element, innerTextId, regex, text){
	element.addEventListener('blur', event => {									// 요소에 'blur' 이벤트가 발생하면 함수 실행
		var registUserText = event.target.value									// 사용자가 입력한 값 (이벤트가 일어난 요소의 값 저장)
		var checkEl = document.getElementById(innerTextId)						// 매개변수로 전달된 'innerTextId'를 ID로 갖는 요소 저장
		
		if(registUserText == ''){												// 사용자가 입력한 값이 비었으면,
			checkEl.innerText = ''												// innerText 비우고 함수 종료
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		else if(!regex.test(registUserText)){									// 정규식에 맞지 않으면, 
			checkEl.innerText = text;											// innerText 메시지 출력
			checkEl.style.color = 'red'
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		else{																	// 정규식에 맞으면,
			checkEl.innerText = ''												// innerText 비우기
			event.target.dataset.formCheck = 'Y'								// 통과 --> 'Y'
		}
		
	})
}

//---------- 비밀번호 확인 유효성 검사 함수 ----------
function pwValueCheck(element, pwElement, innerTextId){
	element.addEventListener('blur', function(event){							// 요소에 'blur' 이벤트가 발생하면 함수 실행
	
		var registPwText = pwElement.value										// 비밀번호 입력값 저장
		var registUserText = event.target.value									// 비밀번호 확인 입력값 저장 (이벤트가 일어난 요소의 값 저장)
		var checkEl = document.getElementById(innerTextId)						// 매개변수로 전달된 'innerTextId'를 ID로 갖는 요소 저장
		
		if(registUserText == ''){												// 사용자가 입력한 값이 비었으면,
			checkEl.innerText = ''												// innerText 비우고 함수 종료
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		else if(registPwText != registUserText){								// 비밀번호가 일치하지 않으면,
			checkEl.innerText = "비밀번호가 일치하지 않습니다. 다시 확인해주세요"			// innerText 메시지 출력
			checkEl.style.color = 'red'
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}	
		else{																	// 비밀번호가 일치하면,
			checkEl.innerText = ''												// innerText 비우기
			event.target.dataset.formCheck = 'Y'								// 통과 --> 'Y'
		}
		
	})

}

//---------- 아이디, 닉네임 중복검사 함수 ----------
function registIDCheck(element, innerTextId, url, text) {
	element.addEventListener('blur', function(event) {							// 요소가 blur 이벤트가 발생했을 때 함수 실행
		
		var registUserText = event.target.value.trim();							// 사용자가 입력한 값 (이벤트가 일어난 요소의 값 저장)
		var checkEl = document.getElementById(innerTextId);						// 매개변수로 전달된 'innerTextId'를 ID로 갖는 요소 저장
		if (registUserText != '') {												// 사용자가 입력한 값이 비어있지 않으면,
			$.ajax({
				type: "POST",
				url: "/async/idCheck",											// 매개변수로 전달받은 url
				data: { 'memberID' : registUserText },							// 백단에 사용자가 입력한값 넘겨주기
				dataType: 'text',
				success: function(data) {
					if (data == 1) {											// 백단에서 응답한 값이 1이면 중복 x 사용 가능
						checkEl.innerText = '사용가능한 ' + text + '입니다.'
						checkEl.style.color = '#43d22d'
						event.target.dataset.formCheck = 'Y'					// 통과 --> 'Y'
					}
					else if (data == 0) {										// 백단에서 응답한 값이 0이면 중복 o 사용 불가
						checkEl.innerText = '중복된 ' + text + '입니다.'
						checkEl.style.color = 'red'
						event.target.dataset.formCheck = 'N'					// 미통과 --> 'N'
					}
				},
				error: function(error) {
					console.log('에러발생')
					console.log('에러종류: ' + error)
				}
			})
		}
		else {																	// 사용자가 입력한 값이 비어있으면,
			checkEl.innerText = '사용할수 없는 ' + text + '입니다.'
			checkEl.style.color = 'red'											// innerText 비우기
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		
	})
}

//---------- 아이디, 닉네임 중복검사 함수 ----------
function registNickNameCheck(element, innerTextId, url, text) {
	element.addEventListener('blur', function(event) {							// 요소가 blur 이벤트가 발생했을 때 함수 실행
	
		var registUserText = event.target.value.trim()							// 사용자가 입력한 값 (이벤트가 일어난 요소의 값 저장)
		var checkEl = document.getElementById(innerTextId)						// 매개변수로 전달된 'innerTextId'를 ID로 갖는 요소 저장
		if (registUserText != '') {												// 사용자가 입력한 값이 비어있지 않으면,
			$.ajax({
				type: "POST",
				url: "/async/nickNameCheck",									// 매개변수로 전달받은 url
				data: { 'memberNickname' : registUserText },					// 백단에 사용자가 입력한값 넘겨주기
				dataType: 'text',
				success: function(data) {
					if (data == 1) {											// 백단에서 응답한 값이 1이면 중복 x 사용 가능
						checkEl.innerText = '사용가능한 ' + text + '입니다.'
						checkEl.style.color = '#43d22d'
						event.target.dataset.formCheck = 'Y'					// 통과 --> 'Y'
					}
					else if (data == 0) {										// 백단에서 응답한 값이 0이면 중복 o 사용 불가
						checkEl.innerText = '중복된 ' + text + '입니다.'
						checkEl.style.color = 'red'
						event.target.dataset.formCheck = 'N'					// 미통과 --> 'N'
					}
				},
				error: function(error) {
					console.log('에러발생')
					console.log('에러종류: ' + error)
				}
			})
		}
		else {																	// 사용자가 입력한 값이 비어있으면,
			checkEl.innerText = '사용할수 없는 ' + text + '입니다.'
			checkEl.style.color = 'red'											// innerText 비우기
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		
	})
}

// -------------------- 회원탈퇴 - 약관 동의 필수 --------------------
function checkUnregisterForm() {
	document.unregisterForm.onsubmit = function() {								// unregisterForm <form>을 제출할때 함수 실행
		for (box of unregisterForm.selector) {									// <form>의 모든 체크박스(selector)를 확인
			if (!box.checked) {													// 체크가 안돼있으면,
				alert('필수 약관에 모두 동의해야 탈퇴할 수 있습니다.')							// 메시지 출력
				box.focus();		
				return false;													// 폼 제출 취소
			}
		}
	}
}

// -------------------- 회원확인 - 비밀번호 입력 필수 --------------------
function checkPwForm(){
	document.pwCheckForm.onsubmit = function() {								// pwCheckForm <form>을 제출할때 함수 실행
		if (!pwCheckForm.mpw.value) {											// <form>의 mpw요소의 값이 비어있다면,
			alert('비밀번호를 입력하세요.');											// 메시지 출력
			pwCheckForm.mpw.focus();
			return false;														// 폼 제출 취소
		}
	}
}

// [회원확인 - 취소버튼]
function cancelAction() {
    window.location.href = "mypage.do";
}




// -------------------- 문자 API (coolSMS) - 회원가입 --------------------
// 초기 인증번호 발송 전 -1 저장
var smsCertificationNum = -1;
// [문자 인증번호 발송]
function smsService(){
	var userPhoneNumber = document.getElementById('registPhone').value			// 전화번호 요소 저장
	const phoneRegex = /^01[0-9]{1}[0-9]{4}[0-9]{4}$/							// 전화번호 정규식
	//console.log(userPhoneNumber)
	if(!phoneRegex.test(userPhoneNumber)){										// 전화번호 정규식에 맞지않으면 모달창 띄우기
		showModal('전화번호 형식에 맞지않습니다.', 'closeModal')
		return;
	}
	
	$.ajax({
		type: 'POST',
		url: '/async/smsCertification',												// 요청 url
		data: { 'memberPhone' : userPhoneNumber},								// 컨트롤러에게 전달할 사용자가 입력한 값
		dataType: 'text',
		success: function(data) {
			console.log(data)
			showModal('인증번호를 발송했습니다.', 'closeModal')
			smsCertificationNum = data / 7777;									// 최소한의 보안을 위해 * 7777 한 인증번호 다시 / 7777
		},
		error: function(error) {
			console.log('에러발생')
			console.log('에러종류: ' + error)
		}
	})
}
// [문자 인증번호 확인]
function smsCertification(element, innerTextId){								// 매개변수(인증번호 확인 요소, 인증번호 확인 알람 요소)
	element.addEventListener('blur', event => {									// 요소에 'blur' 이벤트가 발생하면 함수 실행
		var registUserText = event.target.value									// 사용자가 입력한 값 (이벤트가 일어난 요소의 값 저장)
		var checkEl = document.getElementById(innerTextId)						// 매개변수로 전달된 'innerTextId'를 ID로 갖는 요소 저장
		
		if(registUserText == ''){												// 사용자가 입력한 값이 비었으면,
			checkEl.innerText = ''												// innerText 비우고 함수 종료
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		else if(smsCertificationNum < 0){										// 인증번호를 발송하지 않으면, 
			checkEl.innerText = '휴대폰 인증번호가 발송되지 않았습니다.';					// innerText 메시지 출력
			checkEl.style.color = 'red'
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		else if(smsCertificationNum != registUserText){							// 인증번호가 일치하지 않으면,
			checkEl.innerText = '인증번호가 일치하지 않습니다.';						// innerText 메시지 출력
			checkEl.style.color = 'red'
			event.target.dataset.formCheck = 'N'								// 미통과 --> 'N'
		}
		else{																	// 인증번호가 일치하면,
			checkEl.innerText = '인증번호가 일치합니다.';								// innerText 메시지 출력
			checkEl.style.color = '#43d22d'
			event.target.dataset.formCheck = 'Y'								// 통과 --> 'Y'
		}
		
	})
}
// -------------------- 문자 API (coolSMS) - 아이디 찾기 --------------------
if(document.getElementById('findIdBtn')){
	document.getElementById('findIdBtn').onclick = sendID;						// 버튼 클릭 시 함수 실행
}
function sendID(){
	var memberName = document.getElementById('findName').value					// 사용자가 입력한 값 (이름)
	var memberPhone = document.getElementById('findPhone').value				// 사용자가 입력한 값 (전화번호)
	
	if(memberName == '' || memberName == null){									// 이름 값이 공백이거나 null이면,
		showModal('이름을 입력해주세요.', 'closeModal')							// 모달창 띄우고 return
		return;
	}
	else if(memberPhone == '' || memberPhone == null){							// 전화번호 값이 공백이거나 null이면,
		showModal('핸드폰번호를 입력해주세요.', 'closeModal')						// 모달창 띄우고 return
		return;
	}
	
	$.ajax({
		type: 'POST',
		url: '/async/smsSendID',												// 해당 url로 ajax 요청
		data: {'memberName' : memberName, 'memberPhone' : memberPhone},			// { "멤버변수명" : 입력값 } 전달				
		dataType: 'text',
		success: function(data) {
			if(data > 0){
				showModal('아이디가 문자로 전송됐습니다. 로그인페이지로 이동합니다.', 'sendIDSuccess')	// 성공 : 로그인 페이지로 이동 안내
			}
			else{
				showModal('일치하는 회원이 없습니다. 다시 입력바랍니다.', 'closeModal')					// 실패 : 재입력 안내
			}
		},
		error: function(error) {
			console.log('에러발생')
			console.log('에러종류: ' + error)
		}
	})
}

function sendIDSuccess(){
	$.ajax({
		type: 'GET',
		url: '/async/successSmsSendID',	// 성공시 로그인 페이지 이동 url
		success: function() {
		},
		error: function(error) {
			console.log('에러발생')
			console.log('에러종류: ' + error)
		}
	})
}

// -------------------- 문자 API (coolSMS) - 모달창 --------------------
// 인자(모달내용, 확인버튼클릭시 수행할 함수명)
function showModal(contentText, functionName){
	var modalDoc = `
		<div id="custom_modal" class="custom-modal-layout">
		    <div class="custom-modal-main">
		        <div class="custom-modal-tittle">
		            WeaveGlow
		        </div>
		        <div class="custom-modal-content">
		            ${contentText}
		        </div>
		        <div class="custom-modal-footer">
		            <div class="custom-modal-button" onclick=${functionName}()>확인</div>
		        </div>
		    </div>
		</div>
	`
	
	$('#modal_site').html(modalDoc);
}
// 모달창 닫기 > html 비우기
function closeModal(){
	$('#modal_site').html('');
}

