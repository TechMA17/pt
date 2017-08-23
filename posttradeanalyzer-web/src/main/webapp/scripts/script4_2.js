$(document).ready(function(){

	$("#btnGetTradeCost").click(function() {
		location.reload();
	});

});

document.addEventListener("DOMContentLoaded", function(){
	doGetTradeCost();
});


function doGetTradeCost() {
	
	$.ajax({
		type : "GET",
		url : "rest/clienttradecost",
		success : function(tradeCost) {
			
			var tcsummary = new String();
			var object = {};
			var adata = [];
			for (var i = 0; i < tradeCost.length; i++) {
				var cltradecost = tradeCost[i];
				object = { Account: cltradecost[0], a: cltradecost[1]};
				adata.push(object);
				// Formula message string
				var tcsummary = tcsummary + "Account: " + cltradecost[0] + " today's trade cost: "
						+ cltradecost[1] + "<br>";
			}
			document.getElementById("myDIV").innerHTML = tcsummary;
			adata.toString();
			plotTradeCost()
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}


function plotTradeCost(){
	Highcharts.chart('TradeCostcontainer', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Trade Cost Bar Chart'
	    },
	    subtitle: {
	        text: 'tarde cost V.S. account'
	    },
	    xAxis: {
	        title:{
	            text: 'Account'
	        },
	        categories: ['C3PO', '68B3', 'R2D2', 'SLINE', '78Q1'], 
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Trade Cost'
	        }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:15px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0"> </td>' +
	            '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    series: [{
	        name: 'Trade Cost',
	        data: [49.9, 71.5, 106.4, 129.2, 144.0]
	    }]
	});
}