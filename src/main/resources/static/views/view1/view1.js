'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	console.log('Testing ... ');
  $routeProvider.when('/view1', {
    templateUrl: 'resources/views/view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', [function() {

}]);