/**
 * Created by sai on 7/23/17.
 */

(function () {
    'use strict';
    angular.module('carTracker')
        .controller('AlertsController',AlertsController);


    AlertsController.$inject =['AlertsService','$scope'];
    function AlertsController(AlertsService,$scope) {
        var alertsVm = this;
        $scope.date = new Date();

        AlertsService.getAlerts()
            .then(function (response) {
                alertsVm.alerts = response;
                console.log(response);
            },function (error) {
                console.log(error);
            });

    }


})();
