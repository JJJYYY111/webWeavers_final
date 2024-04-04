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
	  labels: ['Red', 'Blue', 'Yellow'],
	  datasets: [
		{
		  label: "donut (chart)",
		  backgroundColor: ["#5e73da", "#b1bdfa", "#5f76e8"],
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

 }); 
 