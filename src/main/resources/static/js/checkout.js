	var IMP = window.IMP; 
    
	function checkoutSubmit(){
		IMP.init("imp80131154"); 
		if(document.getElementById('checkout_zonecode').value == ""){
			alert("배송지를 입력해주세요");
		}
		else if(document.getElementById('checkout_roadAddress').value == ""){
			alert("배송지를 입력해주세요");
		}
		else{
			requestPay();
		}
	}
    
    function requestPay() {
	var price = document.getElementById('totalPrice').innerText;
	var billingName = document.getElementsByTagName('productName');
    var billing = billingName[0] + "외" + billingName.length-1 + "개";
    var memberEmail = document.getElementById('checkoutEmail').value;
    var memberName = document.getElementById('checkoutName').value;
    var memberPhone = document.getElementById('checkoutPhone').value;
    var memberAddress = document.getElementById('checkout_roadAddress').value;
    var memberPostCode = document.getElementById('checkout_zonecode').value;
    var today = new Date();
    var dateUid = today.getFullYear+today.getMonth+today.getDate+today.getHours+today.getMinutes+today.getSeconds+today.getMilliseconds+price
    console.log("billing : "+billing + " / memberEmail : "  + memberEmail + " / memberName : "+ memberName+ " / memberPhone : "+ memberPhone )
        IMP.request_pay(
          {
            pg: "html5_inicis",
            pay_method: "card",
            merchant_uid: dateUid,
            name: billing, // 이부분 json배열로 던지기
            amount: 1,
            buyer_email: "wgw1008@gmail.com",
            buyer_name: memberName,
            buyer_tel: memberPhone,
            buyer_addr: memberAddress,
            buyer_postcode: memberPostCode,
          },
          function (rsp) {
    if (rsp.success) {
		console.log(rsp);
      jQuery.ajax({
        url: "/main", 
        method: "POST",
        headers: { "Content-Type": "application/json" },
        data: {
          imp_uid: rsp.imp_uid,            // 결제 고유번호
          merchant_uid: rsp.merchant_uid   // 주문번호
        }
      }).done(function (data) {
        // 고객사 서버 결제 API 성공시 로직
            document.checkoutForm.submit();
      })
    } else {
      alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
    }
  }
        );
      }
      