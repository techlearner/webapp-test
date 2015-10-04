/**
 * 
 */

var yapServices = angular.module('yapServices', ['ngResource']);

yapServices.factory('RestClient', function($resource){	
	return $resource('http://localhost:8082/yappay/api/services');
} );
		