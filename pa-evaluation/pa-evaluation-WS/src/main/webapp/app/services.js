angular.module("myApp.services",[]).factory("Library", function($resource) {
	return $resource('http://localhost:8080/library/:id', { id : '@id' }, {
		update : {
			method : "PUT"
		},
		remove : {
			method : "DELETE"
		}
	});
});
