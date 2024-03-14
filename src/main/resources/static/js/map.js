// 네이버 지도 초기화 함수
function initMap() {
	var mapOptions = {
		center: new naver.maps.LatLng(37.499455,
			127.035886), // 본사 위치
		zoom: 17, // 지도 초기 확대 레벨
	};

	var map = new naver.maps.Map('map', mapOptions);

	// 네이버 지도에 마커 추가 예시
	var marker = new naver.maps.Marker({
		position: new naver.maps.LatLng(37.499455,
			127.035886),
		map: map,

	});

	// 마커에 정보 표시 (인포윈도우)
	var infoWindow = new naver.maps.InfoWindow(
		{
			content: '<div style="padding:10px;">위브글로우본사</div>',
			disableAnchor: true, // 마커에 인포윈도우가 따라다니지 않도록 설정 
		});

	naver.maps.Event.addListener(marker, 'click',
		function() { //function () 마커 클릭 시 인포윈도우를 열도록 되어 있음
			infoWindow.open(map, marker); // 마커 위치를 기준으로 인포윈도우를 출력
		});

};

window.onload = function() {
	initMap()
};





