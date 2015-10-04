var yapController = angular.module('yappay', ['yapServices']);
//var yapController = angular.module('yappay', []);
yapController.controller('RestClientCtrl', ['$scope','RestClient', 
  function($scope, RestClient) {
	
	$scope.customer = {
			"pid" : null,
			"name": null,
			"specialDate": null,
			"yapCode" : null,
		  	"businessType":"IIFL",
		  	"businessId":null,
		  	"entityType":"CUSTOMER",
		  	"encryption":false
	};	
	
	$scope.showGrowlError = false;
	
	$scope.submit = function(){	
		RestClient.save($scope.customer, function(data){
			alert('Saved successfully.');
		});
	};    
  }]);

/*
phonecatControllers.controller('RegisterCtrl',['$http', '$scope', function($http, $scope){
	
	$scope.customer = {
			"pid" : null,
			"name": null,
			"specialDate": null,
			"yapCode" : null,
		  	"businessType":"IIFL",
		  	"businessId":null,
		  	"entityType":"CUSTOMER",
		  	"encryption":false
	};	
	
	$scope.showGrowlError = false;
	
	$scope.submit = function(){	
	
	
	$http({
	    url: '/yappay/api/appln',
	    method: "POST",
	    headers: {
	        "Content-Type": "application/json"
	    },
	    data: $scope.customer
	}).success(function(response){
	    $scope.result = response;
	    $scope.customer = {};
	    $scope.showGrowlError = false;
	}).error(function(data){
		$scope.showGrowlError = true;
		//alert( "failure message: " + JSON.stringify({data: data}));
	});

});

*/
