(function () {
  'use strict';

  angular.module('ChatRoom').factory("chatApi", function ($http, $localStorage, config) {
	  	
	  	var _listProfiles = function(callback){
	  		
	  		return $http.get(config.baseUrl + "/users")
			.then(function(result){
				
				callback(null, result.data);
			}, function(err){
				callback(err);
			});
	  	},
	  	_getMessages = function(callback){
	  		
	  		var token = $localStorage.token;
	  		
	  		return $http.get(config.baseUrl + "/chat/" + token)
			.then(function(result){
				
				callback(null, result.data);
			}, function(err){
				callback(err);
			});
	  	},
	  	_sendMessage = function(message, callback){
	  		
	  		var token = $localStorage.token;
	  		var data = {
	  			"message" : message
	  		};
	  		
	  		return $http.post(config.baseUrl + "/chat/" + token, data)
			.then(function(result){
				
				callback(null, result.data);
			}, function(err){
				callback(err);
			});
	  	};

		return {
			
			listProfiles : _listProfiles,
			listMessages : _getMessages,
			sendMessage : _sendMessage
		};
	});
})();
