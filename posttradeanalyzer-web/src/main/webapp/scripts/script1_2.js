

$(document).ready(function(){
	$("#btnGetIOC").click(function() {
		location.reload();
	});
	
});



document.addEventListener("DOMContentLoaded", function() {
		doGetIOC();
	});


function doGetIOC() {

	$.ajax({
		type : "GET",
		url : "rest/hitrate",
		success : function(IOC) {
			var object = {};
			var adata = [];
			var sumContent = new String();
			for(i=0; i <IOC.length; i++){
				var singleData = IOC[i];
				object = { Time: singleData[0], a: singleData[1]};
				adata.push(object);
				var liContent = " the data is " + singleData + "<br>";
				sumContent = sumContent + liContent ; 
			}
				
			document.getElementById("myDIV").innerHTML =sumContent;
			adata.toString();
			
			plotGraph();
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}

//line chart code starts here 
function plotGraph(){
Highcharts.chart('IOCcontainer', {
    title: {
        text: 'IOC Hit Rate Line Chart'
    },
    subtitle: {
        text: 'IOC hit rate V.S. time'
    },		    
    chart: {
    		zoomType: 'xy'
    },
    yAxis: {
        title: {
            text: 'IOC Hit Rate'
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
        name: 'IOC Hit Rate',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }]

});
}