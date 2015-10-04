'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').controller('AmountloadCtrl', function ($scope, $http) {

    $scope.transaction = '';
    var type=2;
    var amountLoadUrl = "/services/checker/create?reqType=LOAD";
    $scope.init = function() {
        $scope.transaction = '';
    };

    $scope.submit = function() {
    	console.log("*************%j",angular.toJson($scope.transaction))
    	console.log(amountLoadUrl);
        $scope.transaction.transactionType ='M2C';
        $scope.transaction.externalTransactionId='';
        $scope.transaction.transactionOrigin='MOBILE';
        $scope.transaction.productId='GENERAL';
        $scope.reqType = 2;
        $http.post(amountLoadUrl, angular.toJson($scope.transaction),{
            transformRequest: angular.identity,
            headers: {'Content-Type': "application/json"}
        })
            .success(function(data, status) {
                $scope.success = true;
                $scope.init();
            })
            .error(function(data, status) {
                $scope.message = data.exception.shortMessage;
                $scope.failure = true;
                $scope.init();
            });
    };

});