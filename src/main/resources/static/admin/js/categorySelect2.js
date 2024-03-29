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
    console.log('들어옴')
    var categorySelectElement = document.getElementById("categorySelect"); // select 요소 가져오기

    var categoryPK = categorySelectElement.options[categorySelectElement.selectedIndex].value; // 선택된 option의 값 가져오기

    console.log('로그 카테고리 1 ' + categoryPK);

    var subCategorySelectElement = document.getElementById("subCategorySelect"); // select 요소 가져오기

    var subCategoryPK = subCategorySelectElement.options[subCategorySelectElement.selectedIndex].value; // 선택된 option의 값 가져오기

    console.log('로그 서브카테고리 1 ' + subCategoryPK);

    var startDate = document.getElementById("registFirstDay").value;
    console.log('로그 시작날짜 2 ' + startDate);
    var endDate = document.getElementById("registLastDay").value;
    console.log('로그 끝날짜 2 ' + endDate);

    $.ajax({

        type: "POST",
        url: "searchSales",
        data: {
            'categoryPK': categoryPK,
            'subCategoryPK': subCategoryPK,
            'startDate': startDate,
            'endDate': endDate
        },

        dataType: 'json',

        success: function (datas) {
            console.log('콘솔 [' + datas + ']');
            var tableHTML = `<table id="zero_config" class="table table-striped table-bordered no-wrap">`;
            tableHTML += `<thead> <tr>
                <th class="sorting_asc" style="background-color: #f2f2f2;">PK</th>
                <th class="sorting_asc" style="background-color: #f2f2f2;">상품명</th>
                <th class="sorting_asc" style="background-color: #f2f2f2;">카테고리</th>
                <th class="sorting_asc" style="background-color: #f2f2f2;">서브카테고리</th>
                <th class="sorting_asc" style="background-color: #f2f2f2;">가격</th>
                <th class="sorting_asc" style="background-color: #f2f2f2;">수량</th>
                <th class="sorting_asc" style="background-color: #f2f2f2;">금액</th>
            </tr>
            </thead>
            <tbody>`;

            for (var i = 0; i < datas.length; i++) {
                tableHTML += "<tr>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].productPK + "</td>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].productName + "</td>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].categoryName + "</td>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].subCategoryName + "</td>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].productPrice + "</td>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].totalCnt + "</td>";
                tableHTML += "<td class=\"sorting_asc\" style=\"background-color: #f2f2f2;\">" + datas[i].totalPrice + "</td>";
                tableHTML += "</tr>";
            }

            tableHTML += "</tbody></table>";
            $("#zero_config").html(tableHTML);

        },
        error: function (error) {

            console.log('에러의 종류:' + error)
        }
    });


});