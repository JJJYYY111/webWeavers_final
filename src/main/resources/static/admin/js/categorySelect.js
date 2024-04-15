function changeFirstCategory() {
    var firstCategorySelect = document.getElementById("categorySelect");
    var selectedCategory = firstCategorySelect.value;
    var secondCategorySelect = document.getElementById("subCategorySelect");

    // 서브 카테고리 데이터 (하드코딩된 예시)
    var subCategories = {
        "1": [["선택",0], ["스킨",1], ["로션/에멀젼",2], ["에센스/세럼/앰플",3], ["크림",4], ["아이케어",5], ["미스트/부스터",6], ["세트",7]],
        "2": [["선택",0], ["클렌징폼",8], ["클렌징크림/로션/워터",9], ["클렌징젤/오일/티슈",10], ["립&아이 리무버",11]],
        "3": [["선택",0], ["마사지/워시오프 팩",12], ["필링/스크럽",13], ["시트마스크",14], ["코팩",15]]
    };

    // 두 번째 셀렉트 박스 업데이트
    secondCategorySelect.innerHTML = ''; // 옵션 초기화

    // 선택된 카테고리가 "Category"가 아닌 경우에만 서브 카테고리를 업데이트합니다.
    if (selectedCategory !== "0") {
        subCategories[selectedCategory].forEach(function (subCategory) {
            var option = document.createElement("option");
            option.value = subCategory[1]; // 임의의 값 설정
            option.text = subCategory[0];
            secondCategorySelect.appendChild(option);
        });
    } else {
        // "Category"를 선택한 경우에는 기본값인 "SubCategory" 항목을 추가합니다.
        var defaultOption = document.createElement("option");
        defaultOption.text = "SubCategory";
        defaultOption.selected = true;
        defaultOption.value = 0;        
        secondCategorySelect.appendChild(defaultOption);
    }
}

$(document).on("click", "#searchButton", function () {
    console.log('들어옴') // 테스트 용 로그
    
    // 카테고리 선택 요소 가져오기
    var categorySelectElement = document.getElementById("categorySelect"); // select 요소 가져오기

	// 선택된 카테고리의 값 가져오기
    var categoryPK = categorySelectElement.options[categorySelectElement.selectedIndex].value; // 선택된 option의 값 가져오기

    console.log('로그 카테고리 1 ' + categoryPK);  // 테스트 용 로그

	// 서브 카테고리 선택 요소 가져오기
    var subCategorySelectElement = document.getElementById("subCategorySelect"); // select 요소 가져오기

	// 선택된 서브 카테고리의 값 가져오기
    var subCategoryPK = subCategorySelectElement.options[subCategorySelectElement.selectedIndex].value; // 선택된 option의 값 가져오기

    console.log('로그 서브카테고리 1 ' + subCategoryPK);

	// 시작 날짜 가져오기
    var startDate = document.getElementById("registFirstDay").value;
    console.log('로그 시작날짜 2 ' + startDate);
    
    // 끝 날짜 가져오기
    var endDate = document.getElementById("registLastDay").value;
    console.log('로그 끝날짜 2 ' + endDate);

	// Ajax 요청
    $.ajax({
        type: "POST",
        url: "/async/adminSearchSales",
        data: {
            'categoryPK': categoryPK,
            'subCategoryPK': subCategoryPK,
            'startDate': startDate,
            'endDate': endDate
        },
        dataType: 'json',
        success: function (datas) {
            console.log('콘솔 [' + datas + ']');
            
            // 검색 결과를 받아왔을 때
            var table = $('#zero_config').DataTable();

            // 테이블 초기화
            table.clear().draw();

            // 데이터 추가
            for (var i = 0; i < datas.length; i++) {
                table.row.add([
                    datas[i].productPK,
                    datas[i].productName,
                    datas[i].categoryName,
                    datas[i].subCategoryName,
                    datas[i].productPrice,
                    datas[i].totalCnt,
                    datas[i].totalPrice
                ]).draw(false);
            }

            // 페이징 재설정
            table.page('first').draw('page');
        },
        error: function (error) {
            console.log('에러의 종류:' + error)
        }
    });
});

$(document).ready(function() {
    // 리셋 버튼 클릭 시 실행될 함수
    $('#resetButton').on('click', function() {
        // 각 입력 요소의 값을 초기화
        $('#registFirstDay').val('');
        $('#registLastDay').val('');
        $('#categorySelect').val('0');
        
        // 두 번째 카테고리를 초기화
        $('#subCategorySelect').empty(); // 옵션 초기화
        $('#subCategorySelect').append('<option value="0">SubCategory</option>'); // 기본값으로 설정
    });
});

// 오늘 날짜 설정
$(document).ready(function() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // 1월 : 0 
    var yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    $('#registFirstDay').attr('max', today);
    $('#registLastDay').attr('max', today);
});

// 시작일이 종료일보다 이후일 경우 종료일을 시작일로 설정
$(document).ready(function() {
    $('#registFirstDay').change(function() {
        var startDate = new Date($('#registFirstDay').val());
        var endDate = new Date($('#registLastDay').val());

        if (startDate > endDate) {
            $('#registLastDay').val($('#registFirstDay').val());
        }
    });
});

// 종료일이 시작일보다 이전일 경우 시작일을 종료일로 설정
$(document).ready(function() {
    $('#registLastDay').change(function() {
        var startDate = new Date($('#registFirstDay').val());
        var endDate = new Date($('#registLastDay').val());

        if (endDate < startDate) {
            $('#registFirstDay').val($('#registLastDay').val());
        }
    });
});
