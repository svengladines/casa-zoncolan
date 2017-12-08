var $jq = jQuery.noConflict();

var getParameter = function (url, key) {
	var parameter = null;
	var urlParts = url.split('?');
	if ( urlParts.length > 1 ) { 
		var sURLVariables = urlParts[1].split('&');
		    if ( sURLVariables.length > 0 ) {
			    for (var i = 0; i < sURLVariables.length; i++) {
					var sParameterName = sURLVariables[i].split('=');
					if (sParameterName[0] == key) {
					    parameter = sParameterName[1];
					}
			    }
		    }
		}
	return parameter;
};