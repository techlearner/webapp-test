'use strict';

angular.module('yapp')
	.directive('sidenav',function(){
		return {
	        templateUrl:'scripts/directives/sidenav/sidenav.html',
	        restrict: 'E',
	        replace: true,
	        controller: function($scope){

	        	$scope.tabActive = [];

	        	$scope.$watch('tabActive', function(){

	        		if($scope.perfectSCrollbarObj) {
	        			setTimeout(function(){
		        			$scope.perfectSCrollbarObj.perfectScrollbar('update');
	        			}, 100);
	        		}

	        	}, true);

	        },
	        link: function(scope, el, attrs){

	        	setTimeout(function(){

		        	scope.perfectSCrollbarObj = el.find('.tab-content').perfectScrollbar();

	        	}, 0);


	        }
    	}
	});


