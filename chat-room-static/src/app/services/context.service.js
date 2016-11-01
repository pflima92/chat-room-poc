(function () {
  'use strict';

  angular.module('ChatRoom').factory("contextApi", function ($http, $localStorage, config) {
	  
	  	var _isLogged = function(){
	  		
	  		return !$localStorage.profile ? false : true;
	  	},
	  	_setProfile = function(data, callback){
	  		
	  		var token = data.token,
	  			profile = data.profile;
	  		
	  		$http.post(config.baseUrl + "/signin/" + token, profile)
				.then(function(result){
					
					$localStorage.token = data.token;
			  		$localStorage.profile = data.profile;
					
					callback(null);
				}, function(err){
					callback(err);
				});
	  	},
	  	_getProfile = function(){
	  		
	  		return angular.copy($localStorage.profile);
	  	},
	  	_clear = function(callback){
	  		
	  		var dispatch = function(err){
	  			delete $localStorage.token;
		  		delete $localStorage.profile;
		  		delete $localStorage.messages;
		  		callback(err);
	  		};
	  		
	  		var token = $localStorage.token;
	  		
	  		$http.post(config.baseUrl + "/signout/" + token)
			.then(function(result){
				
				dispatch(null);
			}, function(err){
				dispatch(err);
			});
	  	},
	  	_getToken = function(){
	  		
	  		return $localStorage.token;
	  	},
	  	_getMessages = function(){
	  		
	  		return $localStorage.messages;
	  	},
	  	_setMessages = function(messages){
	  		
	  		$localStorage.messages = messages;
	  	};
	  	
		return {
			
			isLogged : _isLogged,
			setProfile : _setProfile,
			getProfile : _getProfile,
			clear : _clear,
			getToken : _getToken,
			getMessages : _getMessages,
			setMessages : _setMessages
		};
	});
})();
