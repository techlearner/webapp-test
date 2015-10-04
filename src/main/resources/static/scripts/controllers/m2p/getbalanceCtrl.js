'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').controller('GetBalanceCtrl', function ($scope, $http) {

    $scope.submit = function() {
        $http.get('/services/customer/balance?entityId=' + $scope.entityId).
            success(function (data) {
                $scope.balance = data.result[0].balance;
                $scope.totalElements = data.pagination.totalElements;
            })
                .error(function(data, status) {
                    $scope.message = data.exception.detailMessage;
                    $scope.failure = true;
                    $scope.init();
                });
    };
});