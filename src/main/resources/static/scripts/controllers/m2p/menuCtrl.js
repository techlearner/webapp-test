'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').controller('MenuCtrl', function ($scope, $http) {

    $scope.init = function(){
        $http.get('/services/loggedinUser').
    	success(function(data, status) {
    		if(data === "senthil@m2p.in") {
    			$scope.admin = true;
    		} else {
    			$scope.admin = false;
    		}
    	});
    };
    
    $scope.init();

});