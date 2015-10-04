'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp')
  .controller('LoginCtrl', function($scope, $location, $rootScope, $http) {

    $scope.submit = function() {
      
    	$http.get('/services/loggedinUser').
    	 success(function(data) {
    		 $rootScope.username = data;
         }) .error(function(data, status) {
         });
      $location.path('/dashboard');

      return false;
    }

  });
