/**
 * Created by sai on 7/24/17.
 */
(function () {
    'use strict';

    angular.module('carTracker')
        .service('HistoryPlotService',HistoryPlotService)

    HistoryPlotService.$inject =['$http','$q'];
    function HistoryPlotService($http,$q) {

        var self = this;

        self.getHistory = function (vin,signal) {

            return $http.get("http://localhost:8080/api/readings/"+ vin+ "/"+signal)
                .then(function (response) {
                    return response;
                },function (error) {
                    return $q.reject(error);
                })

        }

    }
})();