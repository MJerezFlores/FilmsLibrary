

var routingApp = angular.module("myApp", ["ngRoute"]);
routingApp.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });

    $routeProvider
        .when("/", {
            controller: "homepageController",
            templateUrl: "/serverLibrary/public/html/homepage"
        }).when("/Filmpage/:idFilm", {
            controller: "infoFilmController",
            templateUrl: "infoFilmpage.html"
        }).otherwise({
            redirectTo:'/'
    })


}]);
//
//routingApp.controller('homepageController', function($scope){
//    $scope.message = 'homepage'
//});
//
//routingApp.controller('filmpageController', function($scope){
//    $scope.message = 'filmpage'
//});
