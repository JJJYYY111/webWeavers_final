// Id를 사용하여 해당 요소들을 변수로 저장

var postcode = document.getElementById('sample4_postcode');
var roadAddress = document.getElementById('sample4_roadAddress');
var	jibunAddress = document.getElementById('sample4_jibunAddress');
var	detail = document.getElementById('sample4_detailAddress');


var modalInsert_zonecode = document.getElementById('modalInsert_zonecode');
var modalInsert_roadAddress = document.getElementById('modalInsert_roadAddress');
var modalInsert_jibunAddress = document.getElementById('modalInsert_jibunAddress');
var modalInsert_detail = document.getElementById('modalInsert_detail');


var modalUpdate_zonecode = document.getElementById('modalUpdate_zonecode');
var modalUpdate_roadAddress = document.getElementById('modalUpdate_roadAddress');
var modalUpdate_jibunAddress = document.getElementById('modalUpdate_jibunAddress');
var modalUpdate_detail = document.getElementById('modalUpdate_detail');


function addAddressInsert() { // 모달창 : 배송지 추가창에 주소값 세팅 
	addressSetting(modalInsert_zonecode, modalInsert_roadAddress, modalInsert_jibunAddress, modalInsert_detail);
}

function addAddressUpdate() { // 모달창 : 배송지 수정창에 주소값 세팅
	addressSetting(modalUpdate_zonecode, modalUpdate_roadAddress, modalUpdate_jibunAddress, modalUpdate_detail);
}

// 카카오 주소API를 사용하여 받아온 인자에 값을 세팅
function addressSetting(zonecode, roadAddress, jibunAddress, detail){ 
	new daum.Postcode(
		{
			oncomplete: function(data) {
				zonecode.value = data.zonecode;
				roadAddress.value = data.roadAddress;
				jibunAddress.value = data.jibunAddress;
				detail.focus();
			}
		}).open();
}

// 회원가입에서 카카오 주소창 사용
function sample4_execDaumPostcode() {
	addressSetting(postcode, roadAddress, jibunAddress, detail);
}