'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').controller('AmountLoadUploadCtrl', function ($scope, $state,$http, $rootScope) {
	$scope.parentMakerLoadRequests = {};
	 $scope.$state = $state;
	  $scope.date = new Date();
    $scope.filteredTodos = [];
    $scope.currentPage = 1;
    $scope.numPerPage = 5;
    $scope.maxSize = 5;
    $scope.success = false;
    $scope.failure = false;
    $scope.totalElements = 0;

    $scope.$watch('currentPage', function() {
        $scope.parentMakerLoadRequests = $scope.init();
    });

    $scope.download = function(fileName){
        $http.get('/services/getFile?filePath='+fileName).
            success(function(data) {
                $scope.uploadList = data.result;
                $scope.totalElements = data.pagination.totalElements;
            });
    }

    $scope.init = function(){
        $http.get('/services/checker?pageNo='+$scope.currentPage+'&pageSize='+$scope.numPerPage).
            success(function(data) {
            	$scope.parentMakerLoadRequests = data.result;
                $scope.totalElements = data.pagination.totalElements;
            }) .error(function(data, status) {
                $scope.message = data.exception.shortMessage;
                $scope.failure = true;
            });
    };

    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log('file is ' );
        console.dir(file);
        var uploadUrl = "/services/import/load";
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
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
    
    $scope.init();
});