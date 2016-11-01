angular.module('ChatRoom').config(function ($routeProvider) {
	
	$routeProvider.when("/login", {
		templateUrl: "app/partials/login.html",
		controller: "loginCtrl"
	}).when("/chat", {
		templateUrl: "app/partials/chat.html",
		controller: "chatCtrl"
	});
});