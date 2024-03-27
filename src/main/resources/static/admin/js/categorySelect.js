function fetchSubCategories() {
    var categorySelect = document.getElementById("categorySelect");
    var selectedCategory = categorySelect.value;

    fetch("fetch_subcategories.php", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "category=" + selectedCategory
    })
    .then(response => response.json())
    .then(data => {
        updateSubCategoryOptions(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function updateSubCategoryOptions(subCategories) {
    var subCategorySelect = document.getElementById("subCategorySelect");
    // 기존 옵션 제거
    subCategorySelect.innerHTML = '<option selected>SubCategory</option>';

    // 새로운 옵션 추가
    subCategories.forEach(function(subCategory) {
        subCategorySelect.innerHTML += '<option value="' + subCategory.id + '">' + subCategory.name + '</option>';
    });
}

