(function () {
  'use strict';

  angular.module('ChatRoom').controller("chatCtrl", function($scope, $uibModal, chatApi, contextApi) {
	  
	  var _refreshProfiles = function(){
		  
		  chatApi.listProfiles(function(err, profiles){
			  
			  $scope.profiles = profiles;
		  });
	  },
	  _refreshMessages = function(){
		  
		  chatApi.listMessages(function(err, messages){
			  
			  var contextMessages = contextApi.getMessages() || [];
			  
			  var sessionMessages = contextMessages.concat(messages);
			  if(messages.length){
				
				  $('.panel-body-chat').stop().animate({
			  		  scrollTop: $('.panel-body-chat')[0].scrollHeight
			  	  }, 800);
			  }
		  	  
		  	  $scope.messages = sessionMessages;
		  	  contextApi.setMessages(sessionMessages);
		  });
	  },
	  _sendMessage = function(){
		  
		  var message = $scope.message;
		  if(message === '') return;
		  
		  chatApi.sendMessage(message, function(){
			  
			  $scope.message = '';
		  });
	  }, 
	  _getContextMessages = function(){
		  
		  return contextApi.getMessages() || [];
	  },
	  _formatDateTime = function(dateTime){
		  var targetMoment = moment().locale('pt-br');
		  targetMoment.date(dateTime[2]);
		  targetMoment.month(dateTime[1] - 1);
		  targetMoment.year(dateTime[0]);
		  targetMoment.hour(dateTime[3]);
		  targetMoment.minute(dateTime[4]);
		  targetMoment.second(dateTime[5]);
		  return targetMoment.format('L') + ' ' + targetMoment.format('LTS');   
	  }

	  $scope.profiles = [];
	  $scope.messages = _getContextMessages();
	  $scope.send = _sendMessage;
	  $scope.formatDateTime = _formatDateTime;
	  
	  setInterval(function () {
	        $scope.$apply(function () {
	        	
	        	if(contextApi.isLogged()){
	        		
	        		_refreshProfiles();
	        		_refreshMessages();
	        	}
	        });
	  }, 1000);
  });
})();
