// Dashboard 1 Morris-chart
$(function () {
    "use strict";
    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            y: '1st Week',
            month: 50,
            date: 80,
            itouch: 20
        }, {
            y: '2nd Week',
            month: 130,
            date: 100,
            itouch: 80
        }, {
            y: '3rd Week',
            month: 80,
            date: 60,
            itouch: 70
        }, {
            y: '4th Week',
            month: 70,
            date: 200,
            itouch: 140
        }, {
            y: '5th Week',
            month: 180,
            date: 150,
            itouch: 140
        }, {
            y: '31st',
            month: 105,
            date: 100,
            itouch: 80

        }],
        xkey: 'y',
        ykeys: ['month', 'date'],
        labels: ['month', 'date'],
        pointSize: 3,
        fillOpacity: 0,
        pointStrokeColors: ['#5f76e8', '#01caf1'],
        behaveLikeLine: true,
        gridLineColor: '#e0e0e0',
        lineWidth: 3,
        hideHover: 'auto',
        lineColors: ['#5f76e8', '#01caf1'],
        resize: true,
        parseTime: false, // x축이 시간 형식이 아님을 나타내는 옵션
    });
});




$(function () {
    Morris.Area({
        element: 'morris-area-chart2',
        data: [{
            period: '2010',
            SiteA: 0,
            SiteB: 0,

        }, {
            period: '2011',
            SiteA: 130,
            SiteB: 100,

        }, {
            period: '2012',
            SiteA: 80,
            SiteB: 60,

        }, {
            period: '2013',
            SiteA: 70,
            SiteB: 200,

        }, {
            period: '2014',
            SiteA: 180,
            SiteB: 150,

        }, {
            period: '2015',
            SiteA: 105,
            SiteB: 90,

        },
        {
            period: '2016',
            SiteA: 250,
            SiteB: 150,

        }],
        xkey: 'period',
        ykeys: ['SiteA', 'SiteB'],
        labels: ['Site A', 'Site B'],
        pointSize: 0,
        fillOpacity: 0.6,
        pointStrokeColors: ['#5f76e8', '#01caf1'],
        behaveLikeLine: true,
        gridLineColor: '#e0e0e0',
        lineWidth: 0,
        smooth: false,
        hideHover: 'auto',
        lineColors: ['#5f76e8', '#01caf1'],
        resize: true

    });
});

// $(function () {
//     // LINE CHART
//     var line = new Morris.Line({
//         element: 'morris-line-chart',
//         resize: true,
//         data: [
//             { y: '2011 Q1', item1: 2666 },
//             { y: '2011 Q2', item1: 2778 },
//             { y: '2011 Q3', item1: 4912 },
//             { y: '2011 Q4', item1: 3767 },
//             { y: '2012 Q1', item1: 6810 },
//             { y: '2012 Q2', item1: 5670 },
//             { y: '2012 Q3', item1: 4820 },
//             { y: '2012 Q4', item1: 15073 },
//             { y: '2013 Q1', item1: 10687 },
//             { y: '2013 Q2', item1: 8432 }
//         ],
//         xkey: 'y',
//         ykeys: ['item1'],
//         labels: ['Item 1'],
//         gridLineColor: '#eef0f2',
//         lineColors: ['#5f76e8'],
//         lineWidth: 1,
//         hideHover: 'auto'
//     });
// });

$(function () {
    // LINE CHART
    var line = new Morris.Line({
        element: 'morris-line-chart',
        resize: true,
        data: [
            { y: '00:00', item1: 8323, item2: 10543 },
            { y: '03:00', item1: 14892, item2: 8194 },
            { y: '06:00', item1: 10583, item2: 8724 },
            { y: '09:00', item1: 12654, item2: 7821 },
            { y: '12:00', item1: 9065, item2: 12987 },
            { y: '15:00', item1: 11034, item2: 11567 },
            { y: '18:00', item1: 8754, item2: 11234 },
            { y: '21:00', item1: 9832, item2: 13459 }
        ],
        xkey: 'y',
        ykeys: ['item1', 'item2'],
        labels: ['Item 1', 'Item 2'],
        gridLineColor: '#eef0f2',
        lineColors: ['#5f76e8', '#01caf1'],
        lineWidth: 1,
        hideHover: 'auto',
        parseTime: false // x축이 시간 형식이 아님을 나타내는 옵션

    });
});




$(function () {
    // Morris donut chart

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "스킨",
            value: 10,

        }, {
            label: "로션",
            value: 30
        }, {
            label: "클렌징",
            value: 20
        }, {
            label: "마스크",
            value: 40
        }],
        resize: true,
        colors: ['#5f76e8', '#01caf1', '#8fa0f3', '#01caf1']
    });
});
$(function () {
    // Morris donut chart

    Morris.Donut({
        element: 'morris-donut-chart2',
        data: [{
            label: "스킨",
            value: 30,

        }, {
            label: "로션",
            value: 40
        }, {
            label: "클렌징",
            value: 20
        }, {
            label: "마스크",
            value: 10
        }],
        resize: true,
        colors: ['#5f76e8', '#01caf1', '#8fa0f3', '#01caf1']
    });
});
$(function () {
    // Morris bar chart
    Morris.Bar({
        element: 'morris-bar-chart',
        data: [
            {
                // {
                // y: '1월',
                // 스킨: 100,
                // 로션: 90,
                // 클렌징: 60,
                // 마스크: 50}
                y: '1분기',
                작년: 75,
                올해: 45
            },
            {
                y: '2분기',
                작년: 25,
                올해: 65
            }, {
                y: '3분기',
                작년: 50,
                올해: 40
            }, {
                y: '4분기',
                작년: 35,
                올해: 85
            }
            // {
            //     y: '5월',
            //     스킨: 50,
            //     로션: 40,
            //     클렌징: 30,
            //     마스크: 50
            // }, {
            //     y: '6월',
            //     스킨: 75,
            //     로션: 65,
            //     클렌징: 40,
            //     마스크: 50
            // }, {
            //     y: '7월',
            //     스킨: 100,
            //     로션: 90,
            //     클렌징: 40,
            //     마스크: 50
            // }, {
            //     y: '8월',
            //     스킨: 80,
            //     로션: 65,
            //     클렌징: 40,
            //     마스크: 50
            // }, {
            //     y: '9월',
            //     스킨: 75,
            //     로션: 65,
            //     클렌징: 40,
            //     마스크: 50
            // }, {
            //     y: '10월',
            //     스킨: 75,
            //     로션: 65,
            //     클렌징: 40,
            //     마스크: 50
            // }, {
            //     y: '11월',
            //     스킨: 75,
            //     로션: 65,
            //     클렌징: 40,
            //     마스크: 50
            // }, {
            //     y: '12월',
            //     스킨: 75,
            //     로션: 65,
            //     클렌징: 40,
            //     마스크: 50
            // }
        ],

        // x축 키
        xkey: 'y',

        // // y축 키
        // ykeys: ['스킨', '로션', '클렌징', '마스크'],
        ykeys: ['작년', '올해'],

        // // 막대 레이블

        // labels: ['스킨', '로션', '클렌징', '마스크'],
        labels: ['작년', '올해'],

        // // 막대 색상
        // barColors: ['#01caf1', '#5f76e8', '#f15c80', '#9933ff'],
        barColors: ['#01caf1', '#5f76e8'],
        // 마우스 호버 시 막대 정보 표시 여부
        hideHover: 'auto',

        // 그리드 라인 색상
        gridLineColor: '#eef0f2',

        // 차트 크기 조절 여부
        resize: true,
    });
});

$(function () {
    Morris.Area({
        element: 'extra-area-chart',
        data: [{
            period: '2010',
            iphone: 0,
            ipad: 0,
            itouch: 0
        }, {
            period: '2011',
            iphone: 50,
            ipad: 15,
            itouch: 5
        }, {
            period: '2012',
            iphone: 20,
            ipad: 50,
            itouch: 65
        }, {
            period: '2013',
            iphone: 60,
            ipad: 12,
            itouch: 7
        }, {
            period: '2014',
            iphone: 30,
            ipad: 20,
            itouch: 120
        }, {
            period: '2015',
            iphone: 25,
            ipad: 80,
            itouch: 40
        }, {
            period: '2016',
            iphone: 10,
            ipad: 10,
            itouch: 10
        }


        ],
        lineColors: ['#01caf1', '#5f76e8'],
        xkey: 'period',
        ykeys: ['iphone', 'ipad'],
        labels: ['Site A', 'Site B'],
        pointSize: 0,
        lineWidth: 0,
        resize: true,
        fillOpacity: 0.8,
        behaveLikeLine: true,
        gridLineColor: '#e0e0e0',
        hideHover: 'auto'

    });
});  