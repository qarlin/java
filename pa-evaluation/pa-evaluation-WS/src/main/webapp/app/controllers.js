angular.module('myApp.controllers', ['ngResource','myApp.services'])
 .controller('LibraryListController', function($scope, $state, $window, Library) {
  $scope.libraries = Library.query(); //fetch all movies. Issues a GET to /api/movies

  $scope.deleteLibrary = function(library) { // Delete a movie. Issues a DELETE to /api/movies/:id
    if (popupService.showPopup('Really delete this?')) {
      library.$delete(function() {
        $window.location.href = ''; //redirect to home
      });
    }
  };

}).controller('LibraryViewController', function($scope, $stateParams, Library) {
  $scope.library = Library.get({ id: $stateParams.id }); //Get a single movie.Issues a GET to /api/movies/:id

}).controller('LibraryCreateController', function($scope, $state, $stateParams, Library) {
  $scope.library = new Library();  //create new movie instance. Properties will be set via ng-model on UI

  $scope.addLibrary = function() { //create a new movie. Issues a POST to /api/movies
    $scope.library.$save(function() {
      $state.go('libraries'); // on success go back to home i.e. movies state.
    });
  };

}).controller('LibraryEditController', function($scope, $state, $stateParams, Library) {
  $scope.updateLibrary = function() { //Update the edited movie. Issues a PUT to /api/movies/:id
    $scope.library.$update({ id: $stateParams.id }, function() {
      $state.go('libraries'); // on success go back to home i.e. movies state.
    });
  };

  $scope.loadLibrary = function() { //Issues a GET request to /api/movies/:id to get a movie to update
    $scope.library = Library.get({ id: $stateParams.id });
  };

  $scope.loadLibrary(); // Load a movie which can be edited on UI
});