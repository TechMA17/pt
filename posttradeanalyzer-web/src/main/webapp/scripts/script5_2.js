$(document).ready(function(){
	$("#btnGetHeatMap").click(function() {
		location.reload();
	});
	
});



document.addEventListener("DOMContentLoaded", function() {
	doGetHeatMap()
	});


function doGetHeatMap() {
	
	$.ajax({
		type : "GET",
		url : "rest/heatmap",
		success : function(map) {
				alert(map);
				var tradeQtyLs = [];
				var stockNameLs = [];
				var sumHeatMapinfo =  new String();
			for (var i = 0; i < lstHeatMap.length; i++) {
				var heatMap = lstHeatMap[i];
				
				var stockName = heatMap.stockName;
				var timeStamp = heatMap.timeStamp;
				var tradePerHour = heatMap.tradePerHour;
				
				tradeQtyLs.push(tradePerHour);
				stockNameLs.push(stockName);
				
				heatMapinfo ='Number of Trades ' + heatMap.stockName
				+  ' Timestamp ' + heatMap.timeStamp
				+ ' StockNames ' + heatMap.tradePerHour + "<br>"
				; 
				
				sumHeatMapinfo = sumHeatMapinfo +heatMapinfo;
			}	
				
			document.getElementById("myDIV").innerHTML = sumHeatMapinfo;
			
			
			plotTradeVolume();
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}


function plotTradeVolume(){

	Highcharts.chart('TradeVolumecontainer', {

	    chart: {
	        type: 'heatmap',
	        marginTop: 40,
	        marginBottom: 80,
	        plotBorderWidth: 1
	    },


	    title: {
	        text: 'Trade Volumn Heat Map'
	    },

	    xAxis: {
	        categories: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00']
	    },

	    yAxis: {
	        categories: ['AAPL', 'TSLA', 'CSCO'	],
	        title: null
	    },

	    colorAxis: {
	        min: 0,
	        minColor: '#FFFFFF',
	        maxColor: Highcharts.getOptions().colors[0]
	    },

	    legend: {
	        align: 'right',
	        layout: 'vertical',
	        margin: 0,
	        verticalAlign: 'top',
	        y: 25,
	        symbolHeight: 280
	    },

	    tooltip: {
	        formatter: function () {
	            return '<b>' + this.series.yAxis.categories[this.point.y] + '</b> traded <br><b>' +
	                this.point.value + '</b> at <br><b>' + this.series.xAxis.categories[this.point.x] + '</b>';
	        }
	    },

	    series: [{
	        name: 'Sales per employee',
	        borderWidth: 1,
	        data: [[0, 0, 10], [0, 1, 19], [0, 2, 8], 
	        	   [1, 0, 92], [1, 1, 58], [1, 2, 78],
	        	   [2, 0, 35], [2, 1, 15], [2, 2, 123],
	        	   [3, 0, 72], [3, 1, 132], [3, 2, 114],
	        	   [4, 0, 38], [4, 1, 5], [4, 2, 8],
	        	   [5, 0, 88], [5, 1, 32], [5, 2, 12],
	        	   [6, 0, 13], [6, 1, 44], [6, 2, 88], 
	        	   [7, 0, 31], [7, 1, 1], [7, 2, 82], 
	        	   [8, 0, 85], [8, 1, 97], [8, 2, 123],  
	        	   [9, 0, 47], [9, 1, 114], [9, 2, 31]],
	        dataLabels: {
	            enabled: true,
	            color: '#000000'
	        }
	    }]

	});
}