'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').controller('RegisterCtrl', function ($scope, $http) {

    $scope.customer = '';
    var customerregisterUrl = "/services/checker/create?reqType=REGISTRATION";
        $scope.init = function() {
            $scope.customer = '';
        };

        $scope.submit = function() {
            $scope.customer.businessId = $scope.customer.contactNo;
            $scope.customer.entityType = 'CUSTOMER';
            console.log(JSON.stringify("******** customer req"+JSON.stringify($scope.customer)));
            $http.post(customerregisterUrl, angular.toJson($scope.customer), {
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