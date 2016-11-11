angular.module('myApp.services').factory('Entry', function($resource) {
    return $resource('/film/:filmID'); // Note the full endpoint address
});
