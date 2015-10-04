'use strict';

/**
 * @ngdoc function
 * @name yapp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of yapp
 */
angular.module('yapp').directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
angular.module('yapp').controller('ApproveAmountRefundCtrl', function ($scope, $http) {
    $scope.document = {};
    $scope.filteredTodos = [];
    $scope.currentPage = 0;
    $scope.numPerPage = 5;
    $scope.maxSize = 5;
    $scope.success = false;
    $scope.failure = false;
    $scope.totalElements = 0;

    $scope.$watch('currentPage', function() {
        $scope.uploadList = $scope.init();
    });

    $scope.download = function(id){
        $http.get('/services/getFile/id').
            success(function(data) {
                $scope.uploadList = data.result;
                $scope.totalElements = data.pagination.totalElements;
            });
    }

    $scope.init = function(){
        $http.get('/services/uploadList?pageNo='+$scope.currentPage+'&pageSize='+$scope.numPerPage).
            success(function(data) {
                $scope.uploadList = data.result;
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
        var uploadUrl = "/services/import";
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
});