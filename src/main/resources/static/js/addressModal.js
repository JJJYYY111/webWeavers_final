
// ----- 전체 주소 목록 출력
																																						
function getAddressList(){	// 비동기처리로 DB의 배송지들을 출력 (DAO => SELECTALL)										
	$.ajax({																	
		type: "GET",
		url: 'addressSelectAll.asy',
		data: {},												
		success: function(datas) {												
			if (datas.length > 0) {
				datas = JSON.parse(datas); // 문자열로 받은 데이터를 json타입으로 변환
				var aListFormEl = document.getElementById('cart'); // id가 cart인 요소를 aListFormEl변수로 저장
								
				datas.forEach(data => {aListFormEl.insertAdjacentHTML('beforeend', addressListForm(data))})
				// datas.forEach(data =>{}) 배열을 돌면서 aListFormEl에 html형식으로 저장
																									
			}else{
				alert('상품 없음')
			}
		},
		error: function(error) {
			console.log('에러발생');
			console.log('에러종류: ' + error);
		}
	})
}

//----------배송지 출력목록----------
// 각 배송지 목록에 추가할 데이터를 아래의 HTML코드를 생성해 반환
function addressListForm(data){
	var address = `
	<tr>
		<td style="text-align: left;">
		<br>
	<input class="checkInput" type="radio" name="addressformcheck" id="addressNum_${data.apk}" style="display:none;" value="${data.apk}">
	<label class="checkLabel offset-lg-1 col-lg-10" for="addressNum_${data.apk}">
			<div class="media">
				<div class="media-body" style="color:black;">
					<h3 class="billing-title">${data.aname}</h3>
					<p id="modalSelectAll_zonecode">우편번호 : &nbsp; ${data.zonecode}</p>
					<p id="modalSelectAll_jibunAddress">지번주소 : &nbsp; ${data.jibunaddress?data.jibunaddress:''}</p>
					<p id="modalSelectAll_roadAddress">도로명주소 : &nbsp; ${data.roadaddress}</p>
					<p id="modalSelectAll_detail">상세주소 : &nbsp; ${data.detail?data.detail:''}</p>
					<input type="hidden" id="modalSelectAll_apk" value="${data.apk}">
				</div>
			</div>
			<div>
				<button data-bs-target="#modalboxUpdate" data-bs-toggle="modal" data-bs-dismiss="modal" class="btn btn-primary" onclick='addressUpdateSetting("${data.apk}", "${data.zonecode}", "${data.jibunaddress?data.jibunaddress:''}", "${data.roadaddress}", "${data.detail?data.detail:''}", "${data.aname}")'>수정</button> &nbsp;
				<button data-bs-target="#modalboxDelete" data-bs-toggle="modal" data-bs-dismiss="modal" class="btn btn-secondary" onclick='addressDeleteSetting("${data.apk}", "${data.aname}")'>삭제</button>
			</div>
	</label>
	<br>
		</td>
	</tr>
	`
	return address;
}

// 배송지목록을 비워주는 기능의 함수 => 배송지가 갱신되면 새로 받아와야하기때문에 기존 배송지들을 삭제
function deleteList(){
	document.getElementById('cart').innerHTML = "";
	console.log("deleteList실행");
}

// getElementById로 해당 요소들을 받아와서 변수로 저장
var modalInsert_zonecode = document.getElementById('modalInsert_zonecode');
var modalInsert_roadAddress = document.getElementById('modalInsert_roadAddress');
var modalInsert_jibunAddress = document.getElementById('modalInsert_jibunAddress');
var modalInsert_detail = document.getElementById('modalInsert_detail');
var modalInsert_aname = document.getElementById('modalInsert_aname');


var modalUpdate_apk = document.getElementById('modalUpdate_apk');
var modalUpdate_zonecode = document.getElementById('modalUpdate_zonecode');
var modalUpdate_roadAddress = document.getElementById('modalUpdate_roadAddress');
var modalUpdate_jibunAddress = document.getElementById('modalUpdate_jibunAddress');
var modalUpdate_detail = document.getElementById('modalUpdate_detail');
var modalUpdate_aname = document.getElementById('modalUpdate_aname');

var modalDelete_apk = document.getElementById('modalDelete_apk');
var modalDelete_aname = document.getElementById('modalDelete_aname');


function addSubmit() { // 비동기처리로 DB에 주소를 추가 (DAO => INSERT)
	if(document.getElementById('modalInsert_zonecode').value == ""){
		alert("우편번호를 입력해주세요");
		return false;
	}
	else if(document.getElementById('modalInsert_roadAddress').value == ""){
		alert("도로명주소를 입력해주세요");
		return false;
	}
	$.ajax({																	
		type: "POST",
		url: 'addressInsert.asy',
		data: { 'zondecode' : modalInsert_zonecode.value,				
				'roadAddress' : modalInsert_roadAddress.value,
				'jibunAddress' : modalInsert_jibunAddress.value,
				'detail' : modalInsert_detail.value,
				'aname' : modalInsert_aname.value
			  },												
		success: function(data) {
			if(data == 1){ // 1을 받아왔다면 주소테이블에 주소 추가 성공
				console.log('성공');
				deleteList(); // 주소 목록을 전부 비우기
				getAddressList(); // 주소목록을 다시 세팅해주기
			}
			else{ // 0을 받아왔다면 주소테이블에 주소 추가 실패
				console.log('실패');
				deleteList(); // 주소 목록을 전부 비우기
				getAddressList(); // 주소목록을 다시 세팅해주기	
			}										
			
		},
		error: function(error) {
			console.log('에러발생');
			console.log('에러종류: ' + error);
		}
	})
	return true;
}

function modalInsertEmpty(){ // 배송지 추가창을 띄울때 요소에 저장된 기존값을 없애주기 위한 함수
	modalInsert_zonecode.value = "";
	modalInsert_roadAddress.value = "";
	modalInsert_jibunAddress.value = "";
	modalInsert_detail.value = "";
	modalInsert_aname.value = "";
}

function addressUpdateSetting(apk, zoncode, jibunAddress, roadAddress, detail, aname){
	// 배송지 수정창을 띄울때 요소에 변경할 주소의 기존값을 세팅;
	
	modalUpdate_apk.innerText = apk;
	modalUpdate_zonecode.value = zoncode;
    modalUpdate_roadAddress.value = roadAddress;
	modalUpdate_jibunAddress.value = jibunAddress;
	modalUpdate_detail.value = detail;
	modalUpdate_aname.value = aname;
}

function changeSubmit(){ // 비동기처리로 DB에 주소를 변경 (DAO => UPDATE)
	if(document.getElementById('modalUpdate_zonecode').value == ""){
		alert("우편번호를 입력해주세요");
		return false;
	}
	else if(document.getElementById('modalUpdate_roadAddress').value == ""){
		alert("도로명주소를 입력해주세요");
		return false;
	}
	$.ajax({																	
		type: "POST",
		url: 'addressUpdate.asy',
		data: { 'apk' : modalUpdate_apk.innerText,
				'aname' : modalUpdate_aname.value,
				'zondecode' : modalUpdate_zonecode.value,
				'roadAddress' : modalUpdate_roadAddress.value,
				'jibunAddress' : modalUpdate_jibunAddress.value,
				'detail' : modalUpdate_detail.value
			  },												
		success: function(data) {
			if(data == 1){
				console.log('성공');
				deleteList();
				getAddressList();
			}
			else{
				console.log('실패');
				deleteList();
				getAddressList();	
			}										
			
		},
		error: function(error) {
			console.log('에러발생');
			console.log('에러종류: ' + error);
		}
	})
	return true;
}

// 배송지 삭제를 할때 삭제할 배송지의 apk값을 세팅해주는 함수
function addressDeleteSetting(apk, aname){ 
	modalDelete_apk.value = apk;
	modalDelete_aname.innerText = aname;
}

function deleteSubmit(){ // 비동기처리로 DB에 주소를 삭제 (DAO => DELETE)
	$.ajax({																	
		type: "POST",
		url: 'addressDelete.asy',
		data: { 'apk' : modalDelete_apk.value
			  },												
		success: function(data) {
			if(data == 1){ // 1을 받아왔다면 주소삭제에 성공 
				console.log('성공');
				deleteList(); // 주소 목록을 전부 비우기
				getAddressList(); // 주소목록을 다시 세팅해주기
			}
			else{ // 0을 받아왔다면 주소삭제에 실패
				console.log('실패');
				deleteList(); // 주소 목록을 전부 비우기
				getAddressList(); // 주소목록을 다시 세팅해주기	
			}										
			
		},
		error: function(error) {
			console.log('에러발생');
			console.log('에러종류: ' + error);
		}
	})
}

function selectAllSubmit(){ // 배송지 목록에서 사용할 주소를 결정한경우 확인을 눌렀을때 주소가 선택이 되어있는지 확인하는 함수 
	var selectAddress = document.querySelector('input[name="addressformcheck"]:checked');
	if(selectAddress == null){
		alert("주소를 선택해주세요");
	}
	else{
		selectOneAddress();
	}
}

var addressSelected = [];
function selectOneAddress(){ // 비동기처리로 DB에서 선택한 주소를 출력 (DAO => SELECTONE)
	$.ajax({																	
		type: "POST",
		url: 'addressSelectOne.asy',
		data: { 'apk' : document.querySelector('input[name="addressformcheck"]:checked').value
			  },												
		success: function(data) {
			addressSelected = JSON.parse(data); // 받아온 데이터를 json타입으로 변환
			console.log(addressSelected);
			
			// 결제창에 주소모달에서 선택한 주소의 apk(주소PK번호), 우편번호, 지번주소, 도로명주소, 상세주소, 배송지면을 저장
			document.getElementById('apk').value = document.querySelector('input[name="addressformcheck"]:checked').value;
			document.getElementById('checkout_zonecode').value = Object.values(addressSelected)[1];
			document.getElementById('checkout_jibunAddress').value = Object.values(addressSelected)[2];
			document.getElementById('checkout_roadAddress').value = Object.values(addressSelected)[3];
			document.getElementById('checkout_detail').value = Object.values(addressSelected)[4];
			document.getElementById('aname').value = Object.values(addressSelected)[5];
			console.log(document.getElementById('apk').value);
			console.log(document.getElementById('aname').value);
		},
		error: function(error) {
			console.log('에러발생');
			console.log('에러종류: ' + error);
		}
	})
}

function checkoutSubmit(){
	if(document.getElementById('checkout_zonecode').value == ""){
		alert("배송지를 입력해주세요");
		return false;
	}
	else if(document.getElementById('checkout_roadAddress').value == ""){
		alert("배송지를 입력해주세요");
		return false;
	}
	return true;
}


