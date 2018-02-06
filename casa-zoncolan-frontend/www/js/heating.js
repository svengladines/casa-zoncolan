var heatingURL = function ( ) {
	return url ( "/heating" );
	
};

var heatingStatusURL = function ( ) {
	return url ( "/heating/status" );
	
};

var loadHeating = function( ) {
    	
	getHeating( { renderHeating } );
    	
};

var getHeating = function ( callbacks ) {
	
	var url 
		= heatingURL( ); 
	
	$jq.ajax( {
		type: "get",
		url: url,
		dataType: "json",
	    processData: false,
		success: function( returned ) {
			if ( callbacks ) {
				for ( var i in callbacks ) {
					var callback = callbacks[ i ];
					callback( returned );	
				}
				
			}
			else {
				// success( button, statusElement, "Opgeslagen" );
			}
		},
		error: function(  jqXHR, textStatus, errorThrown ) {
			console.log( errorThrown );
		}
	});
	
};
	
var turnOff = function ( callback ) {
	
	var url 
		= heatingStatusURL( );
	
	var status 
		= "off";
	
	$jq.ajax( {
		type: "PUT",
		url: url,
		dataType: "json",
		contentType: "application/json;charset=\"utf-8\"",
	    processData: false,
		data: JSON.stringify( status ),
		success: function( returned ) {
			if ( callback ) {
				callback( returned );
			}
			else {
				// success( button, statusElement, "Opgeslagen" );
			}
		},
		error: function(  jqXHR, textStatus, errorThrown ) {
			$jq("#error").html( errorThrown );
		}
	});
	
};

var turnOn = function ( callback ) {
	
	var url 
		= heatingStatusURL( );
	
	var status 
		= "on";
	
	$jq.ajax( {
		type: "PUT",
		url: url,
		dataType: "json",
		contentType: "application/json;charset=\"utf-8\"",
	    processData: false,
		data: JSON.stringify( status ),
		success: function( returned ) {
			if ( callback ) {
				callback( returned );
			}
			else {
				// success( button, statusElement, "Opgeslagen" );
			}
		},
		error: function(  jqXHR, textStatus, errorThrown ) {
			$jq("#error").html( errorThrown );
		}
	});
	
};
	
// ### rendering

var renderHeating = function( heating ) {
	
	var current = $jq("#currentTemperature");
	current.html( heating.currentTemperature );
	
	var target = $jq("#targetTemperature");
	var html = target.html( heating.targetTemperature );
	
	// after rendering, bind events
	
	$jq("#turnOffBtn").click( function( e ) {
	    
		turnOff( loadHeating );
		
	});

	$jq("#turnOnBtn").click( function( e ) {
	    
		turnOn( loadHeating );
		
	});
			
};