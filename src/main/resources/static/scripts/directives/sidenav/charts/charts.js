'use strict';

angular.module('yapp')
	.directive('charts',function(){
		return {
        templateUrl:'scripts/directives/sidenav/charts/charts.html',
        restrict: 'E',
        replace: true,
    	}
	});


