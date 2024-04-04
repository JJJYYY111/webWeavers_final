/* 주문 관리 필터 검색*/
$(document).on("click", "#search", function() {
	console.log('들어옴')
	var selectElement = document.getElementById("serialStatus"); // select 요소 가져오기

	var serialStatus = selectElement.options[selectElement.selectedIndex].value; // 선택된 option의 값 가져오기

	console.log('로그 [' + serialStatus + ']');
	var memberName = document.getElementById("memberName").value;

	console.log('로그1' + memberName);

	var serialRegdate = document.getElementById("serialRegdate").value;
	//memberId의 위에서 value를 가져온다.
	console.log('로그	2' + serialRegdate);

	$.ajax({

		type: "POST",
		url: "adminSearchSerial",
		data: {
			'serialStatus': serialStatus,
			'memberName': memberName,
			'serialRegdate': serialRegdate

		},

		dataType: 'json',

		success: function(datas) {
			console.log('콘솔 [' + datas + ']');
			var tableHTML = "<table id='products' border='1' style='table-layout:auto;'>";
			tableHTML += "<thead><tr><th>주문번호</th><th>주문날짜</th><th>주문자</th><th>상품명</th><th>총결제금액</th><th>주문상태</th><th>배송지</th></tr></thead>";
			tableHTML += "<tbody>";
			for (var i = 0; i < datas.length; i++) {
				tableHTML += "<tr>";
				tableHTML += "<td class=\"productName\" id=\"" + datas[i].serialPK + "\">" + datas[i].serialPK + "</td>";
				tableHTML += "<td class=\"productName\" id=\"" + datas[i].serialPK + "\">" + datas[i].serialRegdate + "</td>";
				tableHTML += "<td class=\"productName\" id=\"" + datas[i].serialPK + "\">" + datas[i].memberName + "</td>";
				if (datas[i].buyProductCnt == 0) {
					tableHTML += "<td class=\"productName\"id=\"" + datas[i].serialPK + "\">" + datas[i].productName + "</td>";
				} else {
					tableHTML += "<td class=\"productName\" id=\"" + datas[i].serialPK + "\">" + datas[i].productName + " 외 " + datas[i].buyProductCnt + "개</td>";
				}
				tableHTML += "<td class=\"productName\" id=\"" + datas[i].serialPK + "\">" + new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(datas[i].totalPrice) + "</td>";
				tableHTML += "<td><select class=\"custom-select mr-sm-2 serialStatus\" style=\"color: #000000; width:auto;\" id=\"" + datas[i].serialPK + "\">";
				tableHTML += "<option value=\"receipt\" " + (datas[i].serialStatus === 'receipt' ? 'selected' : '') + ">접수</option>";
				tableHTML += "<option value=\"finish\" " + (datas[i].serialStatus === 'finish' ? 'selected' : '') + ">완료</option>";
				tableHTML += "</select></td>";
				tableHTML += "<td class=\"productName\" id=\"" + datas[i].serialPK + "\">" + datas[i].serialDeliveryAddress + "</td>";
				tableHTML += "</tr>";
			}


			tableHTML += "</tbody></table>";
			$("#products").html(tableHTML);

			tableHTML += "</tbody></table>";
			$("#products").html(tableHTML);

			$("#productNav").remove();

			// 검색 후 페이징 처리 함수 호출
			var rowPerPage = 10;
			var $products = $('#products');
			if ($products.length) {
				createPagination($products, 'productNav', rowPerPage);
			}

		},
		error: function(error) {

			console.log('에러의 종류:' + error)
		}
	});


});

/*=============================*/
/* 누르면 모달창 띄우기 */
$(document).on('click', '.productName', function() {
	var serialPK = $(this).prop('id');
	var modal = document.getElementById("myModal");
	var productNameModal = document.getElementById("productNameModal");
	console.log('serialPK 들어옴 :' + serialPK);

	$.ajax({
		type: "POST",
		url: "adminDetailOrderList",
		data: {
			'serialPK': serialPK
		},
		dataType: 'json',
		success: function(data) {
			console.log(data);

			$('#modalMemberName').text(data[0].memberName);
			$('#modalMemberDate').text(data[0].serialRegdate);

			var totalPriceSum = 0; // 총 금액을 누적하여 저장할 변수 선언
			var tableHTML = `<tr style="color: #000000;">
                                                                <th style="width: 25%;">상품명</th>
                                                                <th style="width: 25%;">수량</th>
                                                                <th style="width: 25%;">금액</th>
                                                           </tr> `; // 테이블의 HTML 코드를 저장할 변수 선언


			// 각 행의 데이터를 테이블에 추가
			for (var i = 0; i < data.length; i++) {
				tableHTML += "<tr>"; // 새로운 <tr> 생성
				console.log('data' + data);
				tableHTML += "<td>" + data[i].productName + "</td>";
				tableHTML += "<td>" + data[i].buyProductCnt + "개" + "</td>";
				tableHTML += "<td>" + new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(data[i].totalPrice) + "</td>";
				tableHTML += "</tr>"; // <tr> 닫기

				// totalPrice 값을 누적하여 더함
				totalPriceSum += parseInt(data[i].totalPrice);
			}

			// 총 금액을 하나의 행으로 표시
			var totalPriceFormatted = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(totalPriceSum);

			tableHTML += "<tr>";
			tableHTML += "<td colspan='2'style='font-weight: bold; background-color: #f2f2f2;'>총 금액 </td>";
			tableHTML += "<td>" + totalPriceFormatted + "</td>";
			tableHTML += "</tr>";

			// 테이블에 HTML 코드 추가
			$("#productsTable").append(tableHTML);

			modal.style.display = "block"; // 모달 표시
		},

		error: function(error) {

			console.log('테이블 에러');

			console.log('에러의 종류:' + error)
		}


	});


});



document.querySelector('.close').addEventListener('click', function() {
	var modal = document.getElementById("myModal");
	modal.style.display = "none"; // 모달 숨김
	$("#productsTable").empty();
});

window.onclick = function(event) {
	var modal = document.getElementById("myModal");
	if (event.target === modal) {
		modal.style.display = "none"; // 모달 숨김
		$("#productsTable").empty();
	}
}


/* 주문 상태 변경 모달창 */
$(document).on('change', '.serialStatus', function() {
	//console.log("주문상태 선택");



	var self = this;
	// 사용자가 선택된 값 가져오기
	var selectedStatus = $(self).val();

	var serialPK = $(self).attr('id');

	// 이벤트 핸들러 일시적으로 해제
	$('#serialStatus').unbind('change');

	$.ajax({
		type: "POST",
		url: "adminSerialStatusChange",
		data: {
			'serialStatus': selectedStatus, // 변수명 수정
			'serialPK': serialPK
		},
		dataType: 'text',

		success: function(datas) {
			swal("주문상태가 변경되었습니다.");
			console.log('콘솔' + datas);
			console.log('serialPK : ' + serialPK);
		},

		error: function(error) {
			console.log('serialPK' + `${serialDTO.serialPK}`);
			console.log('에러의 종류:' + error);
		}
	});
});

/*  금액을 원화 표시   */
var option = document
	.querySelector('option');
var serialStatus = option.dataset.serialStatus;

// totalPrice를 원화 표시로 변환
var totalPrices = document
	.querySelectorAll('.totalPrice');
totalPrices
	.forEach(function(
		totalPrice) {
		totalPrice.textContent = new Intl.NumberFormat(
			'ko-KR',
			{
				style: 'currency',
				currency: 'KRW'
			})
			.format(parseInt(totalPrice.textContent));
	});


