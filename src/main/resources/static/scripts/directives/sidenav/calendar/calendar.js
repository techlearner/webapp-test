'use strict';

angular.module('yapp')
	.directive('calendar',function(){
		return {
        templateUrl:'scripts/directives/sidenav/calendar/calendar.html',
        restrict: 'E',
        replace: true,
    	}
	});


