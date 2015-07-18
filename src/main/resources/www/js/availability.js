var app = angular.module("camp", []);

app.controller("AvailabilityCtrl", function($scope, $http) {
    $scope.loadData = function() {
        $http.get("/data").success(function(data) {
            $scope.data = data;
        }).error(function() {
            alert("Error getting data");
        });
    }
});