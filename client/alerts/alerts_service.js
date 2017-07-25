/**
 * Created by sai on 7/23/17.
 */

(function () {
    'use strict';
    angular.module('carTracker')
        .service('AlertsService',AlertsService);


    AlertsService.$inject=['$http','$q']
    function AlertsService($http,$q) {
        var self = this;

        self.getAlerts = function () {
            return $http.get("http://localhost:8080/api/alerts")
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                })

        }
    }

})();