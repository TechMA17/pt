$(document).ready(function(){

	$("#btnGetComm").click(function() {
		location.reload();
	});

});

document.addEventListener("DOMContentLoaded", function(){
	doGetAllComm();
	doGetAllCommPie();
});


function doGetAllComm(){
	
	$.ajax({
		type : "GET",
		url : "rest/clienttradecost/commission",
		success : function(Comms) {
			var object = {};
			var adata = [];
			var sumContent = new String();
			for (var i = 0; i < Comms.length; i++) {
				var commission = Comms[i];
				object = { Time: commission[0], a: commission[1]};
				adata.push(object);
				sumContent = sumContent + "The data is " + commission + "<br>";
			}
			document.getElementById("myDIV").innerHTML = sumContent;
			adata.toString();
			
			plotCommsLine();
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}

function doGetAllCommPie(){
	
	$.ajax({
		type : "GET",
		url : "rest/clienttradecost/commissionpie",
		success : function(Commspie) {
			var object = {};
			var adata = [];
			var sumContent = new String();
			for (var i = 0; i < Commspie.length; i++) {
				var commission = Commspie[i];
				object = { label: commission[0], value: commission[1]};
				adata.push(object);
				sumContent = sumContent + "The data is " + commission + "<br>";
			}
			document.getElementById("myDIV1").innerHTML = sumContent;
			adata.toString();
			plotCommsPie();
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}


function plotCommsLine(){
	Highcharts.chart('CommsLinecontainer', {

	    title: {
	        text: 'Commission Line Chart'
	    },

	    subtitle: {
	        text: 'Commission V.S. time'
	    },
	    
	    chart: {
	    		zoomType: 'xy'
	    },

	    yAxis: {
	        title: {
	            text: 'Commission'
	        }
	    },

	    xAxis: {
	        title: {
	            text: 'time'
	        },
	        categories: ['00:00', '01:00', 'O2:00', '03:00', '04:00', '05:00', '06:00', '07:00']
	    },

	    plotOptions: {
	        line: {
	            marker: {
	                enabled: false
	            },
	            lineWidth: 5
	        }
	    },

	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    series: [{
	        name: 'commission',
	        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
	    }]

	});
}

function plotCommsPie(){
	Highcharts.chart('CommsPiecontainer', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Commission Pie Chart'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                style: {
	                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                }
	            }
	        }
	    },
	    series: [{
	        name: 'Percentage',
	        colorByPoint: true,
	        data: [{
	            name: 'C3PO',
	            y: 45
	        }, {
	            name: '68B3',
	            y: 10,
	            sliced: true,
	            selected: true
	        }, {
	            name: 'R2D2',
	            y: 5
	        }, {
	            name: 'SLINE',
	            y: 26
	        }, {
	            name: '78Q1',
	            y: 14
	        }]
	    }]
	});
}