$(document).ready(function(){
	$( "#submit" ).click(function() {		
		deliverPath();
	});
});

function deliverPath(){
	var path = $("#input_text").val();

	$.ajax({ 
	    type:   "POST",
	    url:    "rest/admin", 
	    data: JSON.stringify(path),
	    contentType: "application/json",
	    success: function(trade){
	    	alert("Successfully deliver the path, path = " + path);
	    },
	    error:   function(err){
	    	alert("ERROR!!! The error is " + err);
	    } 
	});
}