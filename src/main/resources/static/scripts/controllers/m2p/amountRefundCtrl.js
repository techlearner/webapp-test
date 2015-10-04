'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').controller('AmountRefundCtrl', function ($scope, $http) {

    $scope.transaction = '';
    var uploadUrl = "/services/checker/create?reqType=REFUND";
    $scope.init = function() {
        $scope.transaction = '';
    };

    $scope.submit = function() {
        $scope.amountRefund.transactionType ='C2M';
        $scope.amountRefund.transactionOrigin='LOAD';
        $scope.amountRefund.productId='GENERAL';
        console.log("***** %j",JSON.parse(JSON.stringify($scope.amountRefund)));
        $http.post(uploadUrl, JSON.parse(JSON.stringify($scope.amountRefund)), {
            transformRequest: angular.identity,
            headers: {'Content-Type': "application/json"}
        })
            .success(function(data, status) {
                $scope.message = data.result.txnId;
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