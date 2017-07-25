/**
 * Created by sai on 7/23/17.
 */

(function () {
    'use strict';


    angular.module('carTracker', ['ngRoute'])
        .config(moduleConfig);


    moduleConfig.$inject = ['$routeProvider']
    function moduleConfig($routeProvider) {
        $routeProvider
            .when('/vehicles', {
                templateUrl: "vehicles/vehicles_template.html"
            })
            .when('/readings', {
                templateUrl: "readings/readings_template.html"
            })
            .when('/alerts', {
                templateUrl: "alerts/alerts_template.html"
            })
            .when('/historyplots', {
                templateUrl: "history_plots/historyplot_template.html"
            })
            .otherwise({redirectTo: '/vehicles'});

    }
})();
