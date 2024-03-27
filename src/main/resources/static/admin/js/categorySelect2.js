function changeFirstCategory() {
    var firstCategorySelect = document.getElementById("categorySelect");
    var selectedCategory = firstCategorySelect.value;
    var secondCategorySelect = document.getElementById("subCategorySelect");

    // 서브 카테고리 데이터 (하드코딩된 예시)
    var subCategories = {
        "1": ["선택","스킨", "로션/에멀젼", "에센스/세럼/앰플", "크림", "아이케어", "미스트/부스터", "세트"],
        "2": ["선택","클렌징폼", "클렌징크림/로션/워터", "클렌징젤/오일/티슈", "립&아이 리무버"],
        "3": ["선택","마사지/워시오프 팩", "필링/스크럽", "시트마스크", "코팩"]
    };

    // 두 번째 셀렉트 박스 업데이트
    secondCategorySelect.innerHTML = ''; // 옵션 초기화

    // 선택된 카테고리가 "Category"가 아닌 경우에만 서브 카테고리를 업데이트합니다.
    if (selectedCategory !== "Category") {
        subCategories[selectedCategory].forEach(function(subCategory, index) {
            var option = document.createElement("option");
            option.value = index + 1; // 임의의 값 설정
            option.text = subCategory;
            secondCategorySelect.appendChild(option);
        });
    } else {
        // "Category"를 선택한 경우에는 기본값인 "SubCategory" 항목을 추가합니다.
        var defaultOption = document.createElement("option");
        defaultOption.text = "SubCategory";
        defaultOption.selected = true;
        secondCategorySelect.appendChild(defaultOption);
    }
}
