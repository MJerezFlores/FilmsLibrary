angular.module('myApp.services', []).factory('Film', function($resource) {
    return $resource('/api/film/:filmID', { filmID: '@_filmID' }, {
        update: {
            method: 'PUT'
        }
    });
});