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

/*// 여기서 아래 그래프의 높이를 위 그래프와 같도록 설정 dashboard
$(function () {
    var barChartHeight = $("#bar-chart").height();
    $("#morris-area-chart").height(barChartHeight);
});
*/



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
            		var line = new Morris.Line({
        element: 'morris-line-chart2',
        resize: true,
        data: graphJson,
        xkey: 'y',
        ykeys: [ 'item2'],
        labels: [ '오늘'],
        gridLineColor: '#eef0f2',
        lineColors: [ '#01caf1'],
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
            value: 40,

        },  {
            label: "클렌징",
            value: 20
        }, {
            label: "마스크",
            value: 40
        }],
        resize: true,
        colors: ['#5f76e8', '#01caf1', '#8fa0f3']
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

// $(function () {
// 	"use strict";
// 	// Bar chart
// 	new Chart(document.getElementById("bar-chart"), {
// 		type: 'bar',
// 		data: {
// 			labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
// 			datasets: [
// 				{
// 					label: "Population (millions)",
// 					backgroundColor: ["#6174d5", "#5f76e8", "#768bf4", "#7385df", "#b1bdfa"],
// 					data: [8478, 6267, 5734, 4784, 1833]
// 				}
// 			]
// 		},
// 		options: {
// 			legend: { display: false },
// 			title: {
// 				display: true,
// 				text: '일일 매출 현황'
// 			}
// 		}

// 	});
// });


//----------------------------------------------------------------------------------------------


$(function () {
	"use strict";
	// 변경된 데이터
	var newData = [4000, 3000, 2000, 1000];

	// Bar chart
	new Chart(document.getElementById("bar-chart"), {
		type: 'bar',
		data: {
			labels: ["총매출", "스킨케어", "클렌징", "마스크,팩"],
			datasets: [
				{
					label: "Population (millions)",
					backgroundColor: ["#6174d5", "#5f76e8", "#768bf4", "#7385df", "#b1bdfa"],
					data: newData // 변경된 데이터를 사용
				}
			]
		},
		options: {
			legend: { display: false },
			title: {
				display: true,
				text: '카테고리별 매출 현황',
				fontSize: 15
			},
			scales: {
				xAxes: [{
					categoryPercentage: 0.5, // 막대 간 간격 설정
					barPercentage: 0.8 // 막대 너비 설정 (0~1 사이의 비율)
				}],
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			}
		}
	});
});



$(function () {

	// Horizental Bar Chart
	new Chart(document.getElementById("bar-chart-horizontal"), {
		type: 'horizontalBar',
		data: {
			labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
			datasets: [
				{
					label: "Population (millions)",
					backgroundColor: ["#6174d5", "#5f76e8", "#768bf4", "#7385df", "#b1bdfa"],
					data: [8478, 6267, 5534, 4784, 3433]
				}
			]
		},
		options: {
			legend: { display: false },
			title: {
				display: true,
				text: 'Predicted world population (millions) in 2050'
			}
		}
	});

});

$(function () {

	//Polar Chart
	new Chart(document.getElementById("polar-chart"), {
		type: 'polarArea',
		data: {
			labels: ["Africa", "Asia", "Europe", "Latin America"],
			datasets: [
				{
					label: "Population (millions)",
					backgroundColor: ["#5e73da", "#b1bdfa", "#5f76e8", "#8fa0f3"],
					data: [2478, 5267, 5734, 3784]
				}
			]
		},
		options: {
			title: {
				display: true,
				text: 'Predicted world population (millions) in 2050'
			}
		}
	});


});

$(function () {


	//Radar chart
	new Chart(document.getElementById("radar-chart"), {
		type: 'radar',
		data: {
			labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
			datasets: [
				{
					label: "250",
					fill: true,
					backgroundColor: "rgba(1, 202, 241,0.2)",
					borderColor: "rgba(1, 202, 241,1)",
					pointBorderColor: "#fff",
					pointBackgroundColor: "rgba(1, 202, 241,1)",
					data: [8.77, 55.61, 21.69, 6.62, 6.82]
				}, {
					label: "4050",
					fill: true,
					backgroundColor: "rgba(95, 118, 232,0.2)",
					borderColor: "rgba(95, 118, 232,1)",
					pointBorderColor: "#fff",
					pointBackgroundColor: "rgba(95, 118, 232,1)",
					pointBorderColor: "#fff",
					data: [25.48, 54.16, 7.61, 8.06, 4.45]
				}
			]
		},
		options: {
			title: {
				display: true,
				text: 'Distribution in % of world population'
			}
		}
	});

});

$(function () {


	//Line Chart

	new Chart(document.getElementById("line-chart"), {
		type: 'line',
		data: {
			labels: [4500, 3500, 3200, 3050, 2700, 2450, 2200, 1750, 1499, 2050],
			datasets: [{
				data: [86, 114, 106, 106, 107, 111, 133, 221, 783, 2478],
				label: "Africa",
				borderColor: "#5f76e8",
				fill: false
			}, {
				data: [282, 350, 411, 502, 635, 809, 947, 1402, 3700, 5267],
				label: "Asia",
				borderColor: "#768bf4",
				fill: false
			}, {
				data: [168, 170, 178, 190, 203, 276, 408, 547, 675, 734],
				label: "Europe",
				borderColor: "#7385df",
				fill: false
			}, {
				data: [40, 20, 10, 16, 24, 38, 74, 167, 508, 784],
				label: "Latin America",
				borderColor: "#b1bdfa",
				fill: false
			}, {
				data: [6, 3, 2, 2, 7, 26, 82, 172, 312, 433],
				label: "North America",
				borderColor: "#8fa0f3",
				fill: false
			}
			]
		},
		options: {
			title: {
				display: true,
				text: 'World population per region (in millions)'
			}
		}
	});

	// line second

});


$(function () {
	// // 초기에 차트를 생성합니다.
	// var myChart = createBarChart(chartData);

	// New chart
	new Chart(document.getElementById("pie-chart"), {
		type: 'pie',
		data: {
			labels: ["Africa", "Asia", "Europe", "Latin America"],
			datasets: [{
				label: "Population (millions)",
				backgroundColor: ["#5e73da", "#b1bdfa", "#5f76e8", "#8fa0f3"],
				data: [2478, 5267, 3734, 2784]
			}]
		},
		options: {
			title: {
				display: true,
				text: 'Predicted world population (millions) in 2050'
			}
		}
	});



 }); 
 
 
  $(function () {
	// // 초기에 차트를 생성합니다.
	// var myChart = createBarChart(chartData);
	//donut
new Chart(document.getElementById("donut-chart"), {
	type: 'doughnut',
	data: {
	  labels: ['스킨', '클렌징', '마스크'],
	  datasets: [
		{
		  label: "donut (chart)",
		  backgroundColor: ["#e74a3b", "#3498db", "#f1c40f"],
		  data: [300, 50, 100],
		  hoverOffset: 4
		}
	  ]
	},
	options: {
	  title: {
		display: true,
		text: 'Donut Chart'
	  }
	}
  });

 }); 
 
   $(function () {
	// // 초기에 차트를 생성합니다.
 // 차트 생성
        new Chart(document.getElementById("curve-line-chart"), {
            type: 'line', // 곡선 차트 유형 설정
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                datasets: [
                    {
                        label: "My Dataset",
                        data: [65, 59, 80, 81, 56, 55, 40],
                        borderColor: 'rgb(75, 192, 192)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)', //반투명 
                        pointBackgroundColor: 'rgb(75, 192, 192)',
                        pointBorderColor: 'rgb(75, 192, 192)'
                    }
                ]
            },
            options: {
                title: {
                    display: true,
                    text: 'curve-line-chart'
                }
            }
        });
 });  
 
 
/*   $(function () {
 // 데이터 준비
        var labels = ['0~3시', '3~6시', '6~9시', '9~12시', '12~15시', '15~18시', '18~21시', '21~24시'];
        var datasetYesterday = {
            label: "어제",
            data: [0, 0, 0, 0, 0, 0, 0, 0],
            borderColor: 'rgb(75, 192, 192)',
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            pointBackgroundColor: 'rgb(75, 192, 192)',
            pointBorderColor: 'rgb(75, 192, 192)'
        };
        var datasetToday = {
            label: "오늘",
            data: [0, 0, 0, 0, 0, 0, 0, 0],
            borderColor: 'rgb(255, 99, 132)',
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            pointBackgroundColor: 'rgb(255, 99, 132)',
            pointBorderColor: 'rgb(255, 99, 132)'
        };

        // 차트 생성
        new Chart(document.getElementById("comparison-line-chart"), {
            type: 'line', // 곡선 차트 유형 설정
            data: {
                labels: labels,
                datasets: [datasetYesterday, datasetToday]
            },
            options: {
                title: {
                    display: true,
                    text: 'Comparison Line Chart'
                },
                scales: {
                    xAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: '시간대'
                        }
                    }],
                    yAxes: [{
                        scaleLabel: {
                            display: true,
                            labelString: '판매량'
                        }
                    }]
                }
            }
        });
  }); */
  
  $(function () {
            // 데이터 준비
            var labels = ['0~3시', '3~6시', '6~9시', '9~12시', '12~15시', '15~18시', '18~21시', '21~24시'];
             var datasetYesterday = {
                label: "어제",
                data: [0, 0, 0, 0, 0, 0, 0, 0],
                borderColor: 'rgb(75, 192, 192)',
                /*backgroundColor: 'rgba(75, 192, 192, 0.2)',*/
                backgroundColor: 'rgba(0, 0, 0, 0)',
                pointBackgroundColor: 'rgb(75, 192, 192)',
                pointBorderColor: 'rgb(75, 192, 192)'
                
            };
            var datasetToday = {
                label: "오늘",
                data: [0, 0, 0, 0, 0, 0, 0, 0],
                borderColor: 'rgb(255, 99, 132)',
                /*backgroundColor: 'rgba(255, 99, 132, 0.2)',*/
                backgroundColor: 'rgba(0, 0, 0, 0)',
                pointBackgroundColor: 'rgb(255, 99, 132)',
                pointBorderColor: 'rgb(255, 99, 132)'
                
            };

            // 차트 생성
            var lineChart = new Chart(document.getElementById("comparison-line-chart"), {
                type: 'line', // 곡선 차트 유형 설정
                data: {
                    labels: labels,
                    datasets: [datasetYesterday, datasetToday]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Comparison Line Chart'
                    },
                    scales: {
                        xAxes: [{
                            scaleLabel: {
                                display: true,
                                labelString: '시간대'
                            }
                        }],
                        yAxes: [{
                            scaleLabel: {
                                display: true,
                                labelString: '판매량'
                            }
                        }]
                    }
                }
            });

            // 어제 데이터 요청
            $.ajax({
                type: 'POST',
                url: 'adminYesterdaySalesGraph',
                dataType: 'json',
                success: function(datas) {
                    datas.forEach(function(data, index) {
                        lineChart.data.datasets[0].data[index] = data.totalPrice;
                    });
                    lineChart.update(); // 차트 업데이트
                },
                error: function(error) {
                    console.log('에러의 종류:' + error);
                }
            });

            // 오늘 데이터 요청
            $.ajax({
                type: 'POST',
                url: 'adminTodaySalesGraph',
                dataType: 'json',
                success: function(datas) {
                    datas.forEach(function(data, index) {
                        lineChart.data.datasets[1].data[index] = data.totalPrice;
                    });
                    lineChart.update(); // 차트 업데이트
                },
                error: function(error) {
                    console.log('에러의 종류:' + error);
                }
            });
        });
  
  
  $(function () {
    "use strict";

    // 데이터 준비
    var labels = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
    var salesData = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

    // 데이터 요청
    $.ajax({
        type: "POST",
        url: "adminMonthlySalesGraph",
        dataType: 'json',
        success: function(datas) {
            console.log('로그' + datas);
            datas.forEach(function(data) {
                var monthIndex = parseInt(data.month.substr(-2, 2)) - 1;
                salesData[monthIndex] = data.totalPrice;
            });

            // 차트 생성
            new Chart(document.getElementById("curve-area-chart"), {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '매출액',
                        data: salesData,
                        borderColor: 'rgb(75, 192, 192)',
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        pointBackgroundColor: 'rgb(75, 192, 192)',
                        pointBorderColor: 'rgb(75, 192, 192)'
                    }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Monthly Sales Chart'
                    },
                    scales: {
                        xAxes: [{
                            scaleLabel: {
                                display: true,
                                labelString: '월'
                            }
                        }],
                        yAxes: [{
                            scaleLabel: {
                                display: true,
                                labelString: '매출액'
                            }
                        }]
                    }
                }
            });
        },
        error: function(error) {
            console.log('에러의 종류:' + error);
        }
    });
});

  
 // graphZip.js

$(document).ready(function() {
    // 현재 페이지 URL 가져오기
    var currentPageURL = window.location.href;

    // adminDashboard 페이지에서만 실행될 스크립트
    if (currentPageURL.indexOf("adminDashboard") !== -1) {
        // 초기에는 바바 그래프만 보이도록 설정
        $("#donut-chart").hide();

        // 바바 버튼 클릭 시 실행되는 함수
        $("#baba-button").click(function() {
            // 바바 버튼을 클릭하면 바바 그래프를 보이고 도낫 그래프를 숨김
            $("#bar-chart").show();
            $("#donut-chart").hide();
        });

        // 도낫 버튼 클릭 시 실행되는 함수
        $("#donut-button").click(function() {
            // 도낫 버튼을 클릭하면 도낫 그래프를 보이고 바바 그래프를 숨김
            $("#donut-chart").show();
            $("#bar-chart").hide();
        });
    }
});

$(document).ready(function() {
    // 현재 페이지 URL 가져오기
    var currentPageURL = window.location.href;

    // adminDashboard 페이지에서만 실행될 스크립트
    if (currentPageURL.indexOf("adminDashboard") !== -1) {
        // 초기에는 월 매출 그래프만 보이도록 설정
        $("#comparison-line-chart").hide();

        // 월 매출 버튼 클릭 시 실행되는 함수
        $("#monthly-sales-button").click(function() {
            // 월 매출 버튼을 클릭하면 월 매출 그래프를 보이고 일 매출 그래프를 숨김
            $("#curve-area-chart").show();
            $("#comparison-line-chart").hide();
        });

        // 일 매출 버튼 클릭 시 실행되는 함수
        $("#daily-sales-button").click(function() {
            // 일 매출 버튼을 클릭하면 일 매출 그래프를 보이고 월 매출 그래프를 숨김
            $("#comparison-line-chart").show();
            $("#curve-area-chart").hide();
        });
    }
});

