var routingApp = angular.module("app", ["ngRoute"]);
routingApp.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });

    $routeProvider.when("/homepage", {
        controller: "homepageController",
        templateUrl: "homepage.html"
    }).when("/filmpage", {
        controller: "filmpageController",
        templateUrl: "filmpage.html"
    }).otherwise({
        redirectTo:'/homepage'
    })
}]);

routingApp.controller('homepageController', function($scope){
    $scope.message = 'homepage'
});

routingApp.controller('filmpageController', function($scope){
    $scope.message = 'filmpage'
});
