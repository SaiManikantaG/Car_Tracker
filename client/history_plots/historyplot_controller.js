/**
 * Created by sai on 7/24/17.
 */
(function () {
    'use strict';

    angular.module('carTracker')
        .controller('HistoryPlotController', HistoryPlotController);

    HistoryPlotController.$inject = ['HistoryPlotService', 'VehicleService', '$scope'];

    function HistoryPlotController(HistoryPlotService, VehicleService, $scope) {
        var historyPlotVm = this;
        historyPlotVm.plotData = [];

        VehicleService.getVehicles()
            .then(function (response) {
                historyPlotVm.vehicles = response;
                console.log(response);
            }, function (error) {
                console.log(error);
            });

        $scope.plot = function () {
            console.log($scope.vin);
            console.log($scope.signal);
            HistoryPlotService.getHistory($scope.vin, $scope.signal)
                .then(function (response) {
                    historyPlotVm.plotData = response;
                    console.log(response);

                    $(document).ready(function () {
                            Highcharts.stockChart(document.getElementById("plotContainer"), {
                                exporting: {
                                    enabled: false
                                },
                                rangeSelector: {
                                    enabled: true,
                                    inputEnabled: false

                                },
                                title: {
                                    text: ''
                                },
                                xAxis: {
                                    minRange: 1
                                },
                                series: [{
                                    name: 'Signal Value',
                                    data: historyPlotVm.plotData,
                                    tooltip: {
                                        valueDecimals: 2
                                    }
                                }]
                            });
                        }
                    );


                }, function (error) {
                    console.log(error);
                })
        }
    }

})();