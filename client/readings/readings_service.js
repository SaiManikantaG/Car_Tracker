/**
 * Created by sai on 7/23/17.
 */
(function () {
    'use strict';
    angular.module('carTracker')
        .service('ReadingsService',ReadingsService);


    ReadingsService.$inject=['$http','$q']
    function ReadingsService($http,$q) {
        var self = this;

        self.getReadings = function () {
            return $http.get("http://localhost:8080/api/readings")
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error);
                })

        }
    }

})();