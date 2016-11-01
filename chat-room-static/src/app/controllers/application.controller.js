(function () {
  'use strict';

  angular.module('ChatRoom').controller("appCtrl", function($rootScope, $scope, $location, $timeout, $route, $window, auth, contextApi, config) {
	  
	  var _load = function(){
		  
		  $scope.isLogged = contextApi.isLogged();
		  if(!$scope.isLogged){
			  
			  $location.path('/login');
			  return;
		  }  
		  
		  $scope.profile = contextApi.getProfile();
		  $location.path('/chat');
	  }, 
	  _logout = function(){
		  auth.signout();
		  contextApi.clear(function(err){
				$rootScope.$broadcast("change.login");
				$location.path('/login');
		  });
	  };
	  
	  $scope.logout = _logout;
	  $rootScope.$on("change.login", function(){
		  
		  _load();
	  });
	  _load();

	  // Application loaded
	  $('#wrapper').removeClass('hidden');
	  $('.loading').addClass('hidden');
	  
	  // Check if user are online
	  var pacemaker = moment();
	  var isOnline = true;
	  
	  $(window).focus(function() {
		  isOnline = true;
	  })
	  .blur(function() {
		  isOnline = false;
	  });
	  
	  setInterval(function () {
	        $scope.$apply(function () {
	        	if(isOnline){
	        		
	        		pacemaker = moment();
	        	}
	        	
	        	var diff = moment().diff(pacemaker);
	        	if(contextApi.isLogged() && diff > config.hearthBitTime){
	        		
	        		_logout();
	        	}
	        });
	  }, 1000);
  });
})();
