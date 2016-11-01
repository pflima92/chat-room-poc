'use strict';
var app = angular.module('ChatRoom', [
	'ngRoute',
	'ngStorage',
	'ui.bootstrap',
	'ui.utils.masks',
	'auth0'
]);

app.config( function myAppConfig (authProvider) {
	
	authProvider.init({
	    domain: 'pflima92.auth0.com',
	    clientID: '5y73K6akd65iHu4FMfuAqoH7VKogD7b6'
	});
});