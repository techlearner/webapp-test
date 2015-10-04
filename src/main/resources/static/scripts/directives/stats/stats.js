'use strict';

angular.module('yapp')
  .directive('stats',function(){
    return {
        templateUrl:'scripts/directives/stats/stats.html',
        restrict: 'E',
        replace: true,
        scope: {
          'icon': '@',
          'value': '@',
          'text': '@',
          'bgclass': '@'
        }
      }
  });


