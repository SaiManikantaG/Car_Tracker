/**
 * Created by sai on 7/23/17.
 */
(function () {
    'use strict';
    angular.module('carTracker')
        .service('VehicleService',VehicleService);


    VehicleService.$inject=['$http','$q']
    function VehicleService($http,$q) {
        var self = this;

        self.getVehicles = function () {
            return $http.get("http://localhost:8080/api/vehicles")
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                })

        }
    }

})();