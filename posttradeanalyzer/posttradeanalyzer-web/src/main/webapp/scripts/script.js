$(document).ready(function(){
	$("#btnGetData").click(function() {
		doGetData();
	});
	$("#btnGetAllOrderData").click(function() {
		doGetAllOrder();
	});
	$("#btnPostData").click(function() {
		doSelectOrder();
	});
	$("#btnSumtData").click(function() {
		doSumData();
	});
});



document.addEventListener("DOMContentLoaded", function() {
		//doGetData();
		doGetAllOrder();
});


function doSumData(){
	
	var totalNOrder = 1000;
	var totalNCompletedOrder = 800;
	var totalNCanceledTrade = 200;
	
	var sumcontent = "Total Number Of Order: " + totalNOrder 
					+" Completed Order: " + totalNCompletedOrder
					+" Canceled Order: " + totalNCanceledTrade;	
	
	document.getElementById("myDIVindex").innerHTML = sumcontent;
}

function doSelectOrder(){
	var OrdID = document.getElementById("postID").value;
	
	//document.getElementById("myDIVindex").innerHTML = OrdID;
	
	$.ajax({
		type : "POST",
		url : "rest/orderdata/select",
		data: {"id":OrdID},
		success : function(order) {
			var orderS = order;
			
			if(orderS.buySell == 1 ){ var buySell = "Buy";}
			else{ var buySell = "Sell";}
			
			
			var socontent = 'Client Order ID ' + orderS.clOrdID
			+ '<br>Order Type ' + buySell
			+  '<br>Order Quantity ' + orderS.ordQty
			+ ' <br>Share Price ' + orderS.sharePrice 
			; 
			
			document.getElementById("myDIVindex").innerHTML = socontent;
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});
}


function doGetData() {
	
	$.ajax({
		type : "GET",
		url : "rest/orderdata/single",
		success : function(order5) {
			var orderS = order5;
			
			if(orderS.buySell == 1 ){ var buySell = "Buy";}
			else{ var buySell = "Sell";}
			
			
			var socontent = 'Client Order ID ' + orderS.clOrdID
			+ '<br>Order Type ' + buySell
			+  '<br>Order Quantity ' + orderS.ordQty
			+ ' <br>Share Price ' + orderS.sharePrice 
			; 
			
			document.getElementById("myDIVindex").innerHTML = socontent;
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}


function doGetAllOrder() {
	
	$.ajax({
		type : "GET",
		url : "rest/orderdata",
		success : function(orders) {
			
			
			var tosummary = new String();
			for (var i = 0; i < orders.length; i++) {
				var order = orders[i];
				
			if(order.buySell == 1 ) {var buySell = "Buy";}
			else {var buySell = "Sell";}
	
			
			var toContent = (i+1) + ') Client Order ID ' + order.clOrdID
			+ '<br>Order Type ' + buySell
			+  '<br>Order Quantity ' + order.ordQty
			+ ' <br>Share Price ' + order.sharePrice + "<br><br>";


			tosummary = tosummary + toContent;
			}
			
			document.getElementById("myDIVindex").innerHTML = tosummary;
			
		
		},
		error : function(err) {
			alert("Error"+err);
		}
	});

}

