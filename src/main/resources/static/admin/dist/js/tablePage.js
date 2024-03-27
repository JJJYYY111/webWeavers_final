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

    for (; i < pageTotal; i++) {
        $('<a href="#" class="page"></a>')
            .attr('rel', i)
            .html(i + 1)
            .appendTo('#' + navId);
    }

    $('<a href="#" class="arrow next"><span>&gt;</span></a>').appendTo('#' + navId);

    $tr.addClass('off-screen')
        .slice(0, rowPerPage)
        .removeClass('off-screen');

    var $pagingLink = $('#' + navId + ' .page');
    $pagingLink.on('click', function(evt) {
        evt.preventDefault();
        var $this = $(this);
        if ($this.hasClass('active')) {
            return;
        }
        $pagingLink.removeClass('active');
        $this.addClass('active');

        var currPage = $this.attr('rel');
        var startItem = currPage * rowPerPage;
        var endItem = startItem + rowPerPage;

        $tr.css('opacity', '0.0')
            .addClass('off-screen')
            .slice(startItem, endItem)
            .removeClass('off-screen')
            .animate({ opacity: 1 }, 300);
    });

    $('#' + navId + ' .arrow').on('click', function(evt) {
        evt.preventDefault();
        var $this = $(this);
        var $activePage = $('#' + navId + ' .page.active');
        var currPage = parseInt($activePage.attr('rel'));
        if ($this.hasClass('prev')) {
            currPage = Math.max(0, currPage - 1);
        } else if ($this.hasClass('next')) {
            currPage = Math.min(pageTotal - 1, currPage + 1);
        }
        $('#' + navId + ' .page[rel=' + currPage + ']').click();
    });

    $pagingLink.filter(':first').addClass('active');
}

