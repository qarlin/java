var tasksApp = angular.module('tasksApp', ['ngResource', 'ngRoute']);

(function() {
    'use strict';

    tasksApp.config(function($routeProvider, $locationProvider) {
            $locationProvider.html5Mode(false);
            $routeProvider.when('/tasks', {
                templateUrl: 'assets/app/task/tasks.html',
                controller: 'TasksCtrl'
            });
            $routeProvider.when('/newTask', {
                templateUrl: 'assets/app/task/newTask.html',
                controller: 'NewTaskCtrl'
            });
            $routeProvider.when('/taskId', {
                templateUrl: 'assets/app/task/task.html',
                controller: 'TaskCtrl'
            });
            $routeProvider.otherwise({
                redirectTo: '/tasks'
            });
            
        });
}());
