var IMP = window.IMP;

function checkoutSubmit() {
  IMP.init("imp80131154");
  if (document.getElementById('checkout_zonecode').value == "") {
    alert("배송지를 입력해주세요");
  }
  else if (document.getElementById('checkout_roadAddress').value == "") {
    alert("배송지를 입력해주세요");
  }
  else {	
    requestPay();
  }
}

function requestPay() {
  var price = parseInt((document.getElementById('totalPrice').innerText).replace(/,/gi, ""));
  console.log(price);
  var productList = [];
  document.getElementsByName('selectedProducts').forEach(function (data) {
    productList.push(data.value);
  });
  
  var billingName = document.getElementsByClassName('checkoutProductName');
  console.log(billingName[0]);
  var billing = billingName[0].innerText + "외" + (billingName.length - 1) + "개";
  
  var memberEmail = document.getElementById('checkoutEmail').value;
  var memberName = document.getElementById('checkoutName').value;
  var memberPhone = document.getElementById('checkoutPhone').value;
  var memberAddressRoad = document.getElementById('checkout_roadAddress').value;
  var memberPostCode = document.getElementById('checkout_zonecode').value;
  var memberAddressName = document.getElementById('aname').value;
  var memberAddressDetail = document.getElementById('checkout_detail').value;
  
  var today = new Date();
  var dateUid = today.getFullYear().toString() + today.getMonth().toString() + today.getDate().toString() + today.getHours().toString() + today.getMinutes().toString() + today.getSeconds().toString() + today.getMilliseconds().toString() + price
  
  var address = "[" + memberAddressName + "] " + memberAddressRoad + " " + memberAddressDetail;
  IMP.request_pay(
    {
      pg: "html5_inicis",
      pay_method: "card",
      merchant_uid: dateUid,
      name: billing,
      custom_data: productList,
      amount: price,
      buyer_email: memberEmail,
      buyer_name: memberName,
      buyer_tel: memberPhone,
      buyer_addr: address,
      buyer_postcode: memberPostCode,
    },
    function (rsp) {
      if (rsp.success) {
        jQuery.ajax({
          url: "/async/paymentProcess",
          method: "POST",
          data: {
            imp_uid: rsp.imp_uid,            // 결제 고유번호
            merchant_uid : rsp.merchant_uid,   // 주문번호
            custom_data : (rsp.custom_data).toString(),
            serialDeliveryAddress : rsp.buyer_addr,
          },
          success: function (data) {
            if (data == 1) {
              document.checkoutForm.submit();
            }
            else {
				alert('결제취소!(실결제취소 미구현)');
            }
          },
          error: function () {
          }
        })
      } else {
        alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
      }
    }
  );
}
