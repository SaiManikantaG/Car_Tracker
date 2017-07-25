/**
 * Created by sai on 7/23/17.
 */

(function () {
    'use strict';
    angular.module('carTracker')
        .controller('VehicleController',VehicleController);


    VehicleController.$inject =['VehicleService','$scope'];
    function VehicleController(VehicleService,$scope) {
        var vehicleVm = this;

        VehicleService.getVehicles()
            .then(function (response) {
                vehicleVm.vehicles = response;
                console.log(response);
            },function (error) {
                console.log(error);
            });

      /*  $scope.testmethod = function () {
            console.log($scope.test);
        }*/

    }

})();