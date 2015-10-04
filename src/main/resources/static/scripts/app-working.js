'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.view1',
  'myApp.view2'
]).
config(['$routeProvider', function($routeProvider) {
      /*$routeProvider.whenGET("/", {

        templateUrl : "resources/views/view1/view1.html",
        controller: function(){

        }

          });
	console.log('Testing ... ');*/
  $routeProvider.otherwise({redirectTo: '/view1'});
}]);