var app = angular.module("camp", []);
var chart = undefined;

app.controller("AvailabilityCtrl", function($scope, $http) {

    $scope.updateUI = function() {
        $scope.buildTable();
        if (!$scope.isTable) {
            $scope.drawChart();
        }
    }

    $scope.drawChart = function() {
        if (chart) {
            chart.destroy();
        }

        var data = {
            labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
            datasets: []
        };

        var availabilityArray = $scope.camp.availabilityHistory;
        for(var i in availabilityArray) {
            var day = availabilityArray[i];
            var dt = new Date(day.date);
            var datasetIndex = parseInt(i/7);
            var dataset = data.datasets[datasetIndex];
            if (dataset == undefined) {
                dataset = {
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: [null, null, null, null, null, null, null]
                };
                dataset.label = "Week " + (datasetIndex + 1);
                data.datasets.push(dataset);
            }
            if ($scope.tentOnly) {
                dataset.data[dt.getDay()] = day.tentOnlySitesAvailable;
            } else {
                dataset.data[dt.getDay()] = day.trailerOrTentSitesAvailable;
            }
        }
        console.log(data);



        var ctx = document.getElementById("myChart").getContext("2d");

        var options = {

                          ///Boolean - Whether grid lines are shown across the chart
                          scaleShowGridLines : true,

                          //String - Colour of the grid lines
                          scaleGridLineColor : "rgba(0,0,0,.05)",

                          //Number - Width of the grid lines
                          scaleGridLineWidth : 1,

                          //Boolean - Whether to show horizontal lines (except X axis)
                          scaleShowHorizontalLines: true,

                          //Boolean - Whether to show vertical lines (except Y axis)
                          scaleShowVerticalLines: true,

                          //Boolean - Whether the line is curved between points
                          bezierCurve : true,

                          //Number - Tension of the bezier curve between points
                          bezierCurveTension : 0.4,

                          //Boolean - Whether to show a dot for each point
                          pointDot : true,

                          //Number - Radius of each point dot in pixels
                          pointDotRadius : 4,

                          //Number - Pixel width of point dot stroke
                          pointDotStrokeWidth : 1,

                          //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
                          pointHitDetectionRadius : 20,

                          //Boolean - Whether to show a stroke for datasets
                          datasetStroke : true,

                          //Number - Pixel width of dataset stroke
                          datasetStrokeWidth : 2,

                          //Boolean - Whether to fill the dataset with a colour
                          datasetFill : true,

                          //String - A legend template
                          legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"

                      };

        chart = new Chart(ctx).Line(data, options);
    };

    $scope.buildTable = function() {
        $scope.dataTable = [];
        var total = 0;

        for (var i in $scope.data) {
            var camp = {};
            camp.name = $scope.data[i].name;
            camp.days = [[], [], [], [], [], [], []];
            camp.dailyAverages = [0, 0, 0, 0, 0, 0, 0];

            var availabilityArray = $scope.data[i].availabilityHistory;
            for(var j in availabilityArray) {
                var day = availabilityArray[j];
                var dt = new Date(day.date);
                if ($scope.tentOnly) {
                    camp.days[dt.getDay()].push(availabilityArray[j].tentOnlySitesAvailable);
                } else {
                    camp.days[dt.getDay()].push(availabilityArray[j].trailerOrTentSitesAvailable);
                }
            }
            $scope.dataTable.push(camp);

            for(var j in camp.dailyAverages) {
                total = 0;
                for (var k in camp.days[j]) {
                    total += camp.days[j][k];
                }
                camp.dailyAverages[j] = total / camp.days[j].length;
            }
        }
        console.log($scope.dataTable);
    };

    $scope.loadData = function() {
        $http.get("/data").success(function(data) {
            $scope.data = data;
            console.log($scope.data);
            $scope.camp = data[0];
            $scope.updateUI();
        }).error(function() {
            alert("Error getting data");
        });
    }
});