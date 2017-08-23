$(document).ready(function(){
	$("#btnGetVWAP").click(function() {
		location.reload();
	});
	$("select#dropDownLst").change(function() {
		doPostOption();
	})
});



document.addEventListener("DOMContentLoaded", function() {
	//doGetVWAP();
	doGetOption()
});






function doGetOption(){
	$.ajax({
		type : "GET",
		url : "rest/vwap/option",
		success : function(stocks) {		
			
			var defaultOption = "<option value='default' >Select A Stock</option>"
			var	toAppend = toAppend + defaultOption;
			for(i=0; i <stocks.length; i++){
				toAppend += "<option value='"+stocks[i]+"'>"+stocks[i] + "</option>";
			}
			document.getElementById("dropDownLst").innerHTML= toAppend;
		},
		error : function(err) {
			alert("Error on option"+err);
		}
		});
}


function doPostOption(){
	
	var stock = $("#dropDownLst").find(":selected").text();
	JSON.stringify(stock);
	$.ajax({
		type : "GET",
		url : 'rest/vwap/select/' + stock,
		success : function(VWAP) {
			document.getElementById("VWAP").innerHTML = "";
			var object = {};
			var adata = [];
			var sumContent = new String();
			for(i=0; i <VWAP.length; i++){
				var singleData = VWAP[i];
				object = { Time: singleData[0], a: singleData[1] , vwap : singleData[2]};
				adata.push(object);
				var liContent = " the data is " + singleData + "<br>";
				sumContent = sumContent + liContent ; 
			}
				
			document.getElementById("myDIV").innerHTML =sumContent;
			adata.toString();
			
			//plotfunction
			plotVWAPline();
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});
}



//default function
function doGetVWAP() {
	
	$.ajax({
		type : "GET",
		url : "rest/vwap",
		success : function(VWAP) {			
			var object = {};
			var adata = [];
			var sumContent = new String();
			for(i=0; i <VWAP.length; i++){
				var singleData = VWAP[i];
				object = { Time: singleData[0], a: singleData[1] , vwap : singleData[2]};
				adata.push(object);
				var liContent = " the data is " + singleData + "<br>";
				sumContent = sumContent + liContent ; 
			}
				
			document.getElementById("myDIV").innerHTML =sumContent;
			adata.toString();
			
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}

function plotVWAPline(){
	Highcharts.chart('VWAPlinecontainer', {

	    title: {
	        text: 'VWAP Line Chart'
	    },

	    subtitle: {
	        text: 'VWAP (per 5mins) and price V.S. time'
	    },
	    
	    chart: {
	    		zoomType: 'xy'
	    },

	    yAxis: {
	        title: {
	            text: 'VWAP and Price'
	        }
	    },

	    xAxis: {
	        title: {
	            text: 'time'
	        },
	        categories: ['00:00', '01:00', 'O2:00', '03:00', '03:59', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00']
	    },

	    plotOptions: {
	        line: {
	            marker: {
	                enabled: false
	            },
	            lineWidth: 5,
	        }
	    },

	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    series: [{
	        name: 'VWAP',
	        step: 'left',
	        data: [43934, 43934, 43934, 43934, 43934, 57177, 57177, 57177,57177, 57177, 97031, 97031, 97031, 97031, 97031]
	    },

	    {
	        name: 'Price',
	        data: [38945, 37285, 48573, 32144, 49762, 47642, 42135, 53363, 55254, 46542, 84732, 85734, 98573, 82374, 99999]
	    }]

	});
}
