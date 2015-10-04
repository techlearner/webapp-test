'use strict';

angular.module('yapp')
	.directive('menu',function(){
		return {
	        templateUrl:'scripts/directives/sidenav/menu/menu.html',
	        restrict: 'E',
	        replace: true,

        	controller: function($scope, $http){

	        	$scope.selectedMenu = 'dashboard';
				$scope.showingSubNav = 0;

				$scope.showSubNav = function(x){

					if(x==$scope.showingSubNav)
						$scope.showingSubNav = 0;
					else
						$scope.showingSubNav = x;
				};
				
				$scope.multiCheck = function(y){

					if(y==$scope.multiCollapseVar)
						$scope.multiCollapseVar = 0;
					else
						$scope.multiCollapseVar = y;
				};
				
				$scope.init = function(){
			        $http.get('services/loggedinUser').
			            success(function(data, status, headers, config) {
			            	console.log("data"+data.result);
			            	if (data != null && data.result!=null&&data.result=== 'senthil@m2p.in'){
			            		$scope.admin = true;
			            	} else {
			            		$scope.admin = false;
			            	}
			            }) .error(function(data, status, headers, config) {
			            	console.log("exception "+data);
			            });
			    };
				
				$scope.init();

	        },
    	}
	});