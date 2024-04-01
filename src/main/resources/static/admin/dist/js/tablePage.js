/* 페이징 처리 */
$(document).ready(function() {
    var rowPerPage = 10; // 기본 페이지당 행 수 설정

    // 주문 관리 페이징 처리
    var $products = $('#products');
    if ($products.length) {
        var $products = $('#products');
        createPagination($products, 'productNav', rowPerPage);
    }

    // 회원 페이징 처리
    var $memberTable = $('#memberTable');
    if ($memberTable.length) {
        var $memberTable = $('#memberTable');
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

    $('<a href="#" class="arrow prev"><span>&lt;</span></a>').appendTo('#' + navId);

    var currentPage = 0; // 현재 페이지 번호 설정
    var startPage = currentPage * rowPerPage; // 시작 항목 인덱스 설정
    var endPage = Math.min((currentPage + 1) * rowPerPage, rowTotals); // 종료 항목 인덱스 설정

    for (i = 0; i < pageTotal; i++) { // 페이지 버튼을 생성
        $('<a href="#" class="page"></a>')
            .attr('rel', i)
            .html(i + 1)
            .appendTo('#' + navId);
    }

    $('<a href="#" class="arrow next"><span>&gt;</span></a>').appendTo('#' + navId);

 $tr.addClass('off-screen')
        .slice(0, rowPerPage)
        .removeClass('off-screen');


    // 페이지 버튼 이벤트 처리
    var $pagingLink = $('#' + navId + ' .page');
    $pagingLink.on('click', function(evt) {
        evt.preventDefault();
        var $this = $(this);
        if ($this.hasClass('active')) {
            return;
        }
        $pagingLink.removeClass('active');
        $this.addClass('active');

        currentPage = parseInt($this.attr('rel'));
        startPage = currentPage * rowPerPage;
        endPage = Math.min((currentPage + 1) * rowPerPage, rowTotals);

        $tr.removeClass('off-screen').slice(0, startPage).addClass('off-screen');
        $tr.slice(startPage, endPage).removeClass('off-screen');
        $tr.slice(endPage).addClass('off-screen');
    });

    // 이전/다음 화살표 이벤트 처리
    $('#' + navId + ' .arrow').on('click', function(evt) {
        evt.preventDefault();
        var $this = $(this);
        var $activePage = $('#' + navId + ' .page.active');

        if ($this.hasClass('prev')) {
            if (currentPage > 0) {
                currentPage--;
            }
        } else if ($this.hasClass('next')) {
            if (currentPage < pageTotal - 1) {
                currentPage++;
            }
        }

        $('#' + navId + ' .page[rel=' + currentPage + ']').click();
    });

    // 초기 활성화된 페이지 설정
    $pagingLink.filter('[rel=' + currentPage + ']').addClass('active');
}






