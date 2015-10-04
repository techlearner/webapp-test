'use strict';

angular.module('yapp')
	.directive('comments',function(){
		return {
        templateUrl:'scripts/directives/sidenav/comments/comments.html',
        restrict: 'E',
        replace: true,
    	}
	});


