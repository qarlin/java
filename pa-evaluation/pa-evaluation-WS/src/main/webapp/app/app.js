var app = angular.module('myApp',['ui.router', 'angularModalService', 'ngResource', 'myApp.controllers', 'myApp.services']);

app.config(function($stateProvider,$httpProvider) {
  $stateProvider.state('libraries', { // state for showing all movies
    url: '/lib/',
    templateUrl: 'partials/libraries.html',
    controller: 'LibraryListController'
  }).state('viewLibrary', { //state for showing single movie
    url: '/lib/:id',
    templateUrl: 'partials/library-view.html',
    controller: 'LibraryViewController'
  }).state('newLibrary', { //state for adding a new movie
    url: '/lib/add/',
    templateUrl: 'partials/library-add.html',
    controller: 'LibraryCreateController'
  }).state('editLibrary', { //state for updating a movie
    url: '/lib/edit/:id',
    templateUrl: 'partials/library-edit.html',
    controller: 'LibraryEditController'
  });
}).run(function($state) {
  $state.go('libraries'); //make a transition to movies state when app starts
});