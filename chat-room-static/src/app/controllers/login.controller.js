(function () {
  'use strict';

  angular.module('ChatRoom').controller("loginCtrl", function($rootScope, $scope, $window, $location, auth, contextApi) {
  
	$scope.login = function(){
	  
		auth.signin({popup: true}, function(profile, token){
			
			var data = {
				token : token,
				profile : profile
			};
			
			contextApi.setProfile(data, function(err){
				
				//TODO Validate on error
				if(err) return;
				
				$rootScope.$broadcast("change.login");
				$location.path('/chat');
			});
		}, function(err){
		
			console.info(err);
		});
	}
  });
})();
