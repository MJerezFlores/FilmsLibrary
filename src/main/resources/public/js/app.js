angular.module('myApp', ["ngRoute", "ngResource", "ngCookies", "ngMessages"])

    .directive("carouselMultipleElement", function(){
    return {
        restrict:'E',
        templateUrl : '../html/multiple-element-carousel.html',

        terminal: true,
        transclude: false,

        scope: {
            elements: '=elements',
            id: '=id'
        },
        link: function (scope, element, attrs) {
            setTimeout(function(){ carousel(scope.id, scope.elements.length); }, 500);
        }


    };


//////////////FACTORIES////////////////////

}).factory('Film', function($resource) {
    return $resource('/api/film/:id', {"id":"id"}, {
        query: {method:"GET"},
        delete: {method: 'DELETE'}
    });
}).factory('User', function($resource) {
    return $resource('/api/user/:nickname/:pass', ({"nickname":"nickname","pass": "pass"}),{
        query: {method:"GET"},
    });
}).factory('List', function($resource) {
    return $resource('/api/list/:id', {"id":"id"},{
        query: {method:"GET"},
        delete: {method: 'DELETE'}
    });
}).factory('UserAdd', function($resource) {
    return $resource('/api/user/add')

}).factory('MainListFilm', function($resource) {
    return $resource('/api/list/main/:nickname')

}).factory('ListFilm', function($resource) {
    return $resource('/api/list/all/:nickname')

}).factory('FilmAdd', function($resource) {
    return $resource('/api/film/add')

}).factory('ListAdd', function($resource) {
    return $resource('/api/list/create')

}).factory('FilmEdit', function($resource) {
    return $resource('/api/film/edit', {}, {
        update: {method: 'POST'}
    });
}).factory("Films", function ($resource) {
    return $resource('/api/film/all');

}).factory("Categories", function ($resource) {
    return $resource('/api/search/categories');

}).factory("ListModify", function ($resource) {
    return $resource('/api/list/:id/modify', {"id": "id"}, {
        create: {method: 'POST'}
    });
}).factory("Genre", function($resource){
    return $resource('/api/search/genre/:genre');

}).factory("Title", function($resource){
    return $resource('/api/search/title/:title');

}).factory("Order", function($resource){
    return $resource('/api/search/order/:order');

}).factory("auth", function($cookies,$cookieStore,$location) {
    return {
        login: function (username, password) {
            $cookies.username = username,
                $cookies.password = password;
            //mandamos a la home
            $location.path("/homepage");
        },
        logout: function () {
            //al hacer logout eliminamos la cookie con $cookieStore.remove
            $cookieStore.remove("username"),
                $cookieStore.remove("password");
            //mandamos al login
            $location.path("/");
        },
        checkStatus: function () {
            //creamos un array con las rutas que queremos controlar
            var rutasPrivadas = ["/homepage", "/filmspage", "/infoFilmpage", "/listspage"];
            if (this.in_array($location.path(), rutasPrivadas) && typeof($cookies.username) == "undefined") {
                $location.path("/");
            }
            //en el caso de que intente acceder al login y ya haya iniciado sesión lo mandamos a la home
            if (this.in_array("/login", rutasPrivadas) && typeof($cookies.username) != "undefined") {
                $location.path("/homepage");
            }
        },
        in_array: function (needle, haystack) {
            var key = '';
            for (key in haystack) {
                if (haystack[key] == needle) {
                    return true;
                }
            }
            return false;
        }
    }

    //////////////CONTROLLERS////////////////////

}).controller('homepageController', ["$scope", "MainListFilm", "$cookies", function ($scope, MainListFilm, $cookies) {
    $scope.listMainFilms = MainListFilm.query({ nickname: $cookies.username})

}]).controller('listModifyController', ["$scope", "ListModify","sharedData", "$location", function ($scope, ListModify, sharedData, $location) {
    $scope.listModify = new ListModify();

    $scope.addFilmInList = function(id, action){
        $scope.listModify.action = action;
        $scope.listModify.parameter = sharedData.getData('filmID');
        $scope.listModify.$create({id: id})
        $location.path("/filmspage");
    }
}]).controller('filmpageController', ["$scope", "$routeParams", "Film", "sharedData", "$route", function ($scope,$routeParams,
                                                                                                           Film, sharedData, $route) {
    $scope.film = Film.query({ id: $routeParams.filmID });
    sharedData.setData('filmID', $routeParams.filmID);

    $scope.deleteFilm =  function() {
        Film.delete({ id: $routeParams.filmID})
        $route.reload();
    }
}]).controller('listController', ["$scope", "List", "$route", function ($scope, List, $route) {

    $scope.deleteList =  function(id) {
        List.delete({ id: id})
        $route.reload();
    }

}]).controller('listspageController', ["$scope", "ListFilm","$cookies", function ($scope, ListFilm, $cookies) {
    $scope.listFilms = ListFilm.query({ nickname: $cookies.username})

}]).controller('addFilmController', ["$scope", "FilmAdd", "$route",  function($scope, FilmAdd, $route) {
            $scope.film = new FilmAdd();
            $scope.addFilm = function() {
                $scope.film.$save(function(){
                });
                $route.reload();
    };
}]).controller('addListController', ["$scope", "ListAdd", "$route", "$cookies" , function($scope, ListAdd, $route, $cookies) {
    $scope.list = new ListAdd();
    $scope.addList = function() {
        $scope.list.nickname = $cookies.username;
        $scope.list.$save(function(){
            $route.reload();
        });
    };
}]).controller('editFilmController', ["$scope", "FilmEdit", "$route",   function($scope, FilmEdit, $route) {
    $scope.instanceFilmEdit = new FilmEdit();
    $scope.editFilm = function() { //create a new movie. Issues a POST to /api/movies
        angular.copy($scope.film, $scope.instanceFilmEdit);
        $scope.instanceFilmEdit.$update(function(){
        });
        $route.reload();
    };
}]).controller('filmsController', ["$scope", "Films", function($scope, Films) {
    $scope.films = Films.query();

}]).controller('ratingController', function () {
    this.rating2 = 2;
    this.isReadonly = true;
    this.rateFunction = function (rating) {
        console.log('Rating selected: ' + rating);
    };

}).controller('genreController', ["Categories", "$scope", "Genre", function(Categories, $scope, Genre ) {
    $scope.genres = Categories.query();

}]).controller('RegisterController', ["UserAdd", "$scope", "$location", function(UserAdd, $scope, $location) {
    $scope.user = new UserAdd();
    $scope.addUser = function () {
        $scope.user.$save(function () {
        });
        $location.path("/");
    }
}]).controller('LoginController', ["User", "$scope", "auth", "$location", function(User, $scope, auth, $location){
    $scope.loginUser = function(){
        $scope.userLogged = User.query({nickname:$scope.user.nickname, pass:$scope.user.pass});
        $scope.userLogged.$promise.then(function(data) {
            if (data){
                auth.login($scope.user.nickname, $scope.user.pass);
            }else{
                $location.path("/login");
            }
        });
    }

}]).controller('searchGenreController', ["Genre", "$routeParams","$scope", function(Genre, $routeParams, $scope){
    $scope.films = Genre.query({ genre: $routeParams.genre })

}]).controller('searchOrderController', ["Order", "$routeParams","$scope", function(Order, $routeParams, $scope){
    $scope.films = Order.query({ order: $routeParams.order })

}]).controller('searchTextController', ["Title","$scope","sharedData","$location","$route", function(Title, $scope, sharedData, $location, $route){
    $scope.addSearch = function(){
        $scope.films = Title.query({title: $scope.search.title});
        console.log($scope.films)
        sharedData.setData('searchFilms', $scope.films);
        $location.path("/search");
        $route.reload();
    }
}]).controller('searchTitleController', ["$scope","sharedData", function($scope, sharedData, $location){
    $scope.films = sharedData.getData('searchFilms');

}]).controller('profileController', ["$scope", "$cookies", function($scope, $cookies){
    $scope.nickname = $cookies.username;

    //////////////////SERVICE//////////////
}]).service('sharedData', function () {
    var property = {};

    return {
        getData: function (key) {
            return property[key];
        },
        setData: function (key, value) {
            property[key] = value;
        }
    };

    //////////////////DIRECTIVE//////////////
}).directive('starRating', function() {
        return {
            restrict: 'EA',
            template:
            "<ul class='rating' ng-class='!{readonly: readonly}'>" +
            "  <li ng-repeat='star in stars' ng-class='star' ng-click='toggle($index)'>" +
            "    <i class='fa fa-star'></i>" + //&#9733
            "  </li>" +
            "</ul>",
            scope: {
                ratingValue: '=ngModel',
                max: '=?', // optional (default is 5)
                onRatingSelect: '&?',
                readonly: '=?',
            },
            link: function(scope, element, attributes) {
                if (scope.max == undefined) {
                    scope.max = 5;
                }
                function updateStars() {
                    scope.stars = [];
                    for (var i = 0; i < scope.max; i++) {
                        scope.stars.push({
                            filled: i < scope.ratingValue/2
                        });
                    }
                };
                scope.toggle = function(index) {
                    if (scope.readonly == undefined || scope.readonly === false){
                        scope.ratingValue = index + 1;
                        scope.onRatingSelect({
                            rating: index + 1
                        });
                    }
                };
                scope.$watch('ratingValue', function(oldValue, newValue) {
                    if (newValue) {
                        updateStars();
                    }
                });
            }
        };
//////////////CONFIG////////////////////

}).config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });


    $routeProvider
        .when("/",{
            controller: "LoginController",
            templateUrl: "/html/layout/login.html"
        }).when("/register",{
            controller: "RegisterController",
            templateUrl: "/html/layout/register.html"
        }).when("/homepage", {
            controller: "homepageController",
            templateUrl: "/html/layout/main.html"
        }).when("/listspage", {
            controller: "listspageController",
            templateUrl: "/html/layout/listspage.html"
        }).when("/filmpage/:filmID", {
            controller: "filmpageController",
            templateUrl: "/html/layout/infoFilmPage.html"
        }).when("/filmspage", {
            controller: "filmsController",
            templateUrl: "/html/layout/filmsPage.html"
        }).when("/search/genre/:genre", {
            controller: "searchGenreController",
            templateUrl: "/html/layout/searchGenre.html"
        }).when("/search/order/:order", {
            controller: "searchOrderController",
            templateUrl: "/html/layout/searchOrder.html"
        }).when("/search", {
            controller: "searchTitleController",
            templateUrl: "/html/layout/search.html"
        }).when("/profile", {
            controller: "profileController",
            templateUrl: "/html/layout/profile.html"
        }).otherwise({
            redirectTo:'/'
        })

}]);

function carousel(id, numberOfElements){
    $('#'+id+" .item").each(function(){
        var itemToClone = $(this),
            elementsToShow = numberOfElements>6? 6: numberOfElements;

        for (var i=1;i<elementsToShow;i++) {
            itemToClone = itemToClone.next();

            // wrap around if at end of item collection
            if (!itemToClone.length) {
                itemToClone = $(this).siblings(':first');
            }

            // grab item, clone, add marker class, add to collection
            itemToClone.children(':first-child').clone()
                .addClass("cloneditem-"+(i))
                .appendTo($(this));
        }
    });
}



