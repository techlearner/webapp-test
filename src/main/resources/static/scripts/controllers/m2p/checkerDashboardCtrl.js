'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */

angular.module('yapp')
.controller('CheckerDashboardCtrl', function($scope, $state, $http) {

  $scope.$state = $state;
  $scope.date = new Date();
  $scope.currentPage = 0;
  $scope.numPerPage = 5;
  $scope.maxSize = 5;
  $scope.success = false;
  $scope.failure = false;
  $scope.totalElements = 0;
  
  
  $scope.init = function(){
      $http.get('/services/checker?pageNo='+$scope.currentPage+'&pageSize='+$scope.numPerPage).
          success(function(data) {
        	  $scope.parentMakerLoadRequests = data.result;
              $scope.totalElements = data.pagination.totalElements;
          }) .error(function(data, status) {
              $scope.message = data.exception.shortMessage;
              $scope.failure = true;
          });
  };
  
  $scope.$watch('currentPage', function() {
      $scope.checkerLoadRequests = $scope.init();
  });
  
  $scope.download = function(fileName){
      $http.get('/services/getFile?filePath='+fileName).
          success(function(data) {
              $scope.uploadList = data.result;
              $scope.totalElements = data.pagination.totalElements;
          });
  }
 
	  
  $scope.takeAction = function(index) {
	 var parentMakerLoadRequest = $scope.parentMakerLoadRequests[index];
	 if (parentMakerLoadRequest != null) {
		 var checkerAction = parentMakerLoadRequest.requestStatus;
		 var id = parentMakerLoadRequest.id;
		 var url = "/services/checker/"+id+"/"+checkerAction;
		 
		 $http.post(url, {
	         
	     })
	     	.success(function(data, status) {
	                $scope.success = true;
	                $scope.init();
	         })
	         .error(function(data, status) {
	                $scope.message = data.exception.shortMessage;
	                $scope.failure = true;
	                $scope.init();
	          });
	 }
  }


});
