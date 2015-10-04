'use strict';

angular.module('yapp')
	.directive('footer',function(){
		return {
        templateUrl:'scripts/directives/footer/footer.html',
        restrict: 'E',
        replace: true,
    	}
	});


