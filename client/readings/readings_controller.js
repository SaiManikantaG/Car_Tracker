/**
 * Created by sai on 7/23/17.
 */
/**
 * Created by sai on 7/23/17.
 */

(function () {
    'use strict';
    angular.module('carTracker')
        .controller('ReadingsController',ReadingsController);


    ReadingsController.$inject =['ReadingsService','$scope'];
    function ReadingsController(ReadingsService,$scope) {
        var readingsVm = this;

        ReadingsService.getReadings()
            .then(function (response) {
                readingsVm.readings = response;
                console.log(response);
            },function (error) {
                console.log(error);
            });
        }

})();