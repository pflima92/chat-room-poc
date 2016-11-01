(function() {
	'use strict';

	angular.module('ChatRoom').factory('entitlementsInterceptor', function($q, $window, $location, $localStorage) {
		return {

			// optional method
			'responseError' : function(rejection) {
				
				if(rejection.status === 401 || rejection.status === 403){
	
					delete $localStorage.token;
			  		delete $localStorage.profile;
			  		delete $localStorage.messages;
					$location.path('/login');
				}

				return $q.reject(rejection);
			}
		};
	});
	
	angular.module('ChatRoom').config(function($httpProvider){

		$httpProvider.interceptors.push('entitlementsInterceptor');
	});
})();