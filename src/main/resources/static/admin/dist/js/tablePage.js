/* 페이징 처리 */
$(document).ready(function() {
    var rowPerPage = 10; // 기본 페이지당 행 수 설정

    // 주문 관리 페이징 처리
    var $products = $('#products');
    if ($products.length) {
        createPagination($products, 'productNav', rowPerPage);
    }

    // 회원 페이징 처리
    var $memberTable = $('#memberTable');
    if ($memberTable.length) {
        createPagination($memberTable, 'memberNav', rowPerPage);
    }
});

function createPagination($table, navId, rowPerPage) {
    var $nav = $('<div id="' + navId + '">');
    $table.after($nav);

    var $tr = $table.find('tbody tr');
    var rowTotals = $tr.length;
    var pageTotal = Math.ceil(rowTotals / rowPerPage);
    var i = 0;

    var currentPage = 0; // 현재 페이지 번호 설정

    // 페이지 버튼 이벤트 처리
    function handlePageClick(targetPage) {
        currentPage = targetPage;
        var startPage = currentPage * rowPerPage;
        var endPage = Math.min((currentPage + 1) * rowPerPage - 1, rowTotals - 1); // 종료 항목 인덱스 설정

        $tr.addClass('off-screen').slice(startPage, endPage + 1).removeClass('off-screen');

        $('#' + navId + ' .page').removeClass('active'); // 모든 페이지 버튼의 활성 클래스 제거
        $('#' + navId + ' .page[rel=' + currentPage + ']').addClass('active'); // 클릭된 페이지 버튼에만 활성 클래스 추가
    }
// 페이지 버튼 생성
function generatePageButtons(start, end) {
    $('#' + navId).empty(); // 기존 페이지 버튼 제거
    $('<a href="#" class="arrow prev"><span>&lt;</span></a>').appendTo('#' + navId); // 이전 버튼 추가

    for (i = start; i <= end; i++) {
        $('<a href="#" class="page"></a>')
            .attr('rel', i)
            .html(i + 1)
            .appendTo('#' + navId)
            .click(function() {
                handlePageClick(parseInt($(this).attr('rel'))); // 페이지 번호는 0부터 시작하지 않으므로 변경 없이 사용
            });
    }

    $('<a href="#" class="arrow next"><span>&gt;</span></a>').appendTo('#' + navId); // 다음 버튼 추가

    // 초기 활성화된 페이지 설정
    $('#' + navId + ' .page[rel=' + currentPage + ']').addClass('active');

    // "이전" 버튼 클릭 이벤트 처리
    $('#' + navId + ' .arrow.prev').click(function(evt) {
        evt.preventDefault();
        if (currentPage > 0) {
            handlePageClick(currentPage - 1);
        }
    });
}

    // 초기 페이지 버튼 생성
    generatePageButtons(0, Math.min(pageTotal - 1, 9));

    $tr.addClass('off-screen')
        .slice(0, rowPerPage)
        .removeClass('off-screen');

// 다음 화살표 이벤트 처리
$(document).on('click', '#' + navId + ' .arrow.next', function(evt) {
    evt.preventDefault();
    var nextPageGroupStart = Math.floor((currentPage + 1) / 10) * 10; // 다음 페이지 그룹의 시작 페이지
    var nextPageGroupEnd = Math.min(nextPageGroupStart + 9, pageTotal - 1); // 다음 페이지 그룹의 끝 페이지

    if (currentPage < pageTotal - 1 && currentPage < nextPageGroupStart) {
        var nextPage = nextPageGroupStart;
        var startPage = nextPage;
        var endPage = Math.min(startPage + 9, pageTotal - 1);

        // 페이지 버튼 재생성
        generatePageButtons(startPage, endPage);

        handlePageClick(nextPage);
    } else if (currentPage < pageTotal - 1) {
        handlePageClick(currentPage + 1);
    }
});

// 이전 화살표 이벤트 처리
$(document).on('click', '#' + navId + ' .arrow.prev', function(evt) {
    evt.preventDefault();

    if (currentPage > 0) {
        var prevPage = currentPage - 0; // 현재 페이지 번호에서 1을 빼서 이전 페이지로 이동
        var startPage = Math.floor(prevPage / 10) * 10; // 이전 페이지 그룹의 시작 페이지
        var endPage = Math.min(startPage + 9, pageTotal - 1); // 이전 페이지 그룹의 끝 페이지

        // 페이지 버튼 재생성
        generatePageButtons(startPage, endPage);

        handlePageClick(prevPage);
    }
});

    // 초기 활성화된 페이지 설정
    $('#' + navId + ' .page[rel=' + currentPage + ']').addClass('active');
}

