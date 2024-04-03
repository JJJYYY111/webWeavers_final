// -------------------- 문자 API (coolSMS) - 아이디 찾기 --------------------
if(document.getElementById('findIdBtn')){
	document.getElementById('findIdBtn').onclick = sendID;						// 버튼 클릭 시 함수 실행
}
// [문자 아이디 전송]
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
				showModal('아이디가 문자로 전송됐습니다. 로그인페이지로 이동합니다.', 'loginPage')	// 성공 : 로그인 페이지로 이동 안내
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
// [로그인 페이지로 이동]
function loginPage(){
	 location.href = '/login';
}

// -------------------- 문자 API (coolSMS) - 비밀번호 찾기 --------------------
function sendPhonePW(){
	console.log('핸드폰 ajax 진입')
	console.log(document.getElementById('memberID-01').value)
	console.log(document.getElementById('memberPhone').value)	
	var memberID = document.getElementById('memberID-01').value					// 사용자가 입력한 값 (이름)
	var memberPhone = document.getElementById('memberPhone').value				// 사용자가 입력한 값 (전화번호)

	if(memberID == '' || memberID == null){										// 아이디 값이 공백이거나 null이면,
		showModal('아이디를 입력해주세요.', 'closeModal')							// 모달창 띄우고 return
		return;
	}
	else if(memberPhone == '' || memberPhone == null){							// 전화번호 값이 공백이거나 null이면,
		showModal('핸드폰번호를 입력해주세요.', 'closeModal')						// 모달창 띄우고 return
		return;
	}	
	
	$.ajax({
		type: 'POST',
		url: '/async/smsSendPW',												// 해당 url로 ajax 요청
		data: {'memberID' : memberID, 'memberPhone' : memberPhone},				// { "멤버변수명" : 입력값 } 전달				
		dataType: 'text',
		success: function(data) {
			if(data > 0){
				showModal('임시비밀번호가 문자로 전송됐습니다. 비밀번호 변경 페이지로 이동합니다.', 'changePasswordPage')		// 성공 : 로그인 페이지로 이동 안내
			}
			else{
				showModal('일치하는 회원이 없습니다. 다시 입력바랍니다.', 'closeModal')										// 실패 : 재입력 안내
			}
		},
		error: function(error) {
			console.log('에러발생')
			console.log('에러종류: ' + error)
		}
	})	
	
}

// -------------------- 이메일 API - 비밀번호 찾기 --------------------
function sendEmailPW(){
	console.log('이메일 로그')
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