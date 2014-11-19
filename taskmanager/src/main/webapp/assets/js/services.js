angular.module('TaskApp.services', []).
  factory('taskAPIservice', function($http) {

    var taskAPI = {};

    taskAPI.getTasks = function() {
      return $http({
        method: 'GET', 
        url: 'crudtasks'
      });
    };

    return taskAPI;
  });