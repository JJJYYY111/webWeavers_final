// Dashboard 1 Morris-chart
$(function () {
    "use strict";
    var monthlySalesGraph = [{
            y: '1월',
            sales: 0,
        }, {
            y: '2월',
            sales: 0,
        }, {
            y: '3월',
            sales: 0,
        }, {
            y: '4월',
            sales: 0,
        }, {
            y: '5월',
            sales: 0,
        }, 
        	{
            y: '6월',
            sales: 0,
        },
        	{
            y: '7월',
            sales: 0,
        },
        	{
            y: '8월',
            sales: 0,
        },
          {
            y: '9월',
            sales: 0,
        },
          {
            y: '10월',
            sales: 0,
        },
          {
            y: '11월',
            sales: 0,
        },      
        	{
            y: '12월',
            sales: 0,
        }];
        
    $.ajax({
			type: "POST",
			url: "adminMonthlySalesGraph",
            dataType: 'json',
            	success:function(datas) {
            		
            		console.log('로그'+datas);
        
            		$.each(datas, function(index, data){
						console.log(data.month.substr(-2,2));
            		    monthlySalesGraph[parseInt(data.month.substr(-2,2))-1].sales = data.totalPrice
            		});
            		Morris.Area({
        element: 'morris-area-chart',
        data: monthlySalesGraph,
        xkey: 'y',
        ykeys: ['sales'],
        labels: ['매출액'],
        pointSize: 3,
        fillOpacity: 0,
        pointStrokeColors: ['#5f76e8'],
        behaveLikeLine: true,
        gridLineColor: '#e0e0e0',
        lineWidth: 3,
        hideHover: 'auto',
        lineColors: ['#5f76e8'],
        resize: true,
        parseTime: false, // x축이 시간 형식이 아님을 나타내는 옵션
    });
            },
            error: function (error) {
            	
                console.log('에러의 종류:' + error)
            }
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
    // 여기가 차트
    var graphJson = [
            { y: '0~3시' , 어제: 0, 오늘: 0 },
            { y: '3~6시' , 어제: 0, 오늘: 0 },
            { y: '6~9시' , 어제: 0, 오늘: 0 },
            { y: '9~12시' , 어제: 0, 오늘: 0 },
            { y: '12~15시' , 어제: 0, 오늘: 0 },
            { y: '15~18시' , 어제: 0, 오늘: 0 },
            { y: '18~21시' , 어제: 0, 오늘: 0 },
            { y: '21~24시' , 어제: 0, 오늘: 0 }
        ];
    
    $.ajax({
			type: "POST",
			url: "adminTodaySalesGraph",
            dataType: 'json',
            	success:function(datas) {
            		
            		console.log('로그'+datas);
        
            		$.each(datas, function(index, data){
            		    graphJson[(data.totalTemp)/3].item2 = data.totalPrice
            		});
            		
            },
            error: function (error) {
            	
                console.log('에러의 종류:' + error)
            }
		});
		
    $.ajax({
			type: "POST",
			url: "adminYesterdaySalesGraph",
            dataType: 'json',
            	success:function(datas) {
            		console.log('로그'+datas);
            		$.each(datas, function(index, data){
            		    graphJson[(data.pvtotalTemp)/3].item1 = data.totalPrice
            		});
            		
            		var line = new Morris.Line({
        element: 'morris-line-chart',
        resize: true,
        data: graphJson,
        xkey: 'y',
        ykeys: ['item1', 'item2'],
        labels: ['어제', '오늘'],
        gridLineColor: '#eef0f2',
        lineColors: ['#5f76e8', '#01caf1'],
        lineWidth: 1,
        hideHover: 'auto',
        parseTime: false // x축이 시간 형식이 아님을 나타내는 옵션

    });
            		
            },
            error: function (error) {
            	
                console.log('에러의 종류:' + error)
            }
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