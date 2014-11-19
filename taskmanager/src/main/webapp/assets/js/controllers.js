angular.module('TaskApp.controllers', []).
controller('TaskController', function($scope, $http, taskAPIservice) {
    $scope.taskList = [{
        uid: 1,
        name: "Task 001",
        executionLogs: [{
            id: 1,
            starDate: "01/04/2014 10:00",
            endDate: "01/04/2014 12:00"
        }, {
            id: 2,
            starDate: "01/04/2014 10:00",
            endDate: "01/04/2014 12:00"
        }],
        schedules: [{

        }],
        status: {
            code: "A",
            description: "Active"
        },
        creationDate: "30/04/2014",
        viewAttributes: {
            stars: 4,
            viewCategory: {
                id: 2,
                name: "Rojo",
                color: "#00FF00"
            }
        }
    }, {
        uid: 2,
        name: "Task 002",
        executionLogs: [{
            id: 1,
            starDate: "01/04/2014 10:00",
            endDate: "01/04/2014 12:00"
        }, {
            id: 2,
            starDate: "01/04/2014 10:00",
            endDate: "01/04/2014 12:00"
        }],
        schedules: [{

        }],
        status: {
            code: "A",
            description: "Active"
        },
        creationDate: "30/04/2014",
        viewAttributes: {
            stars: 2,
            viewCategory: {
                id: 2,
                name: "Verde",
                color: "#FF0000"
            }
        }
    }, {
        uid: 3,
        name: "Task 002",
        executionLogs: [{
            id: 1,
            starDate: "01/04/2014 10:00",
            endDate: "01/04/2014 12:00"
        }, {
            id: 2,
            starDate: "01/04/2014 10:00",
            endDate: "01/04/2014 12:00"
        }],
        schedules: [{

        }],
        status: {
            code: "A",
            description: "Active"
        },
        creationDate: "30/04/2014",
        viewAttributes: {
            stars: 1,

        }
    }];

    $scope.setColor = function(task) {
        if (angular.isDefined(task.viewAttributes) && angular.isDefined(task.viewAttributes.viewCategory) && angular.isDefined(task.viewAttributes.viewCategory.color))
            return {
                'border-left-color': task.viewAttributes.viewCategory.color
            };
    }


    /*taskAPIservice.getTasks().success(function (data) {
        //Dig into the responde to get the relevant data
        $scope.taskList = data;
    });*/

    //$http({method: 'GET', url: 'crudtasks'}).success(function(data)   {
    //$scope.taskList = data; // response data 

});
