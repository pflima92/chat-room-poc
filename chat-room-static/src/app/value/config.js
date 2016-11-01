(function(){
	
	angular.module('ChatRoom').value("config", {
		baseUrl: "http://jspare.org:8000", // Api URL
		hearthBitTime : 30 * 1000 // Time for logout inactive user
	});
})();