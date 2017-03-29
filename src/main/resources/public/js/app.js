angular.module('myApp', ["ngRoute", "ngResource", "ngCookies", "ngMessages", "ngSanitize"])

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
}).factory('FilmGeneral', function($resource) {
    return $resource('/api/films/:id', {"id":"id"}, {
        query: {method:"GET"}
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

}).factory('FilmsMain', function($resource) {
    return $resource('/api/films/main/:nickname')

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
    return $resource('/api/film/all/:nickname');

}).factory("AllFilms", function ($resource) {
    return $resource('/api/films');

}).factory("Categories", function ($resource) {
    return $resource('/api/search/categories/:nickname', {"nickname": "nickname"}, {
        get: {method:'GET', isArray:true}
    });

}).factory("ListModify", function ($resource) {
    return $resource('/api/list/:id/modify', {"id": "id"}, {
        create: {method: 'POST'},
        modify: {method: 'POST'},
        delete: {method: 'POST'}
    });

}).factory("CopyFilmPublic", function($resource) {
    return $resource('/api/films/add/:filmID/:nickname', {"filmID":"filmID", "nickname":"nickname"},{
        copy: {method: 'POST'}
    });

}).factory("Genre", function($resource){
    return $resource('/api/search/genre/:genre');

}).factory("Title", function($resource){
    return $resource('/api/search/title/:title/:nickname',{"title": "title", "nickname": "nickname"}, {
        get: {method:'GET', isArray:true}
    })

}).factory("Order", function($resource){
    return $resource('/api/search/order/:order/:nickname');

}).factory("Comments", function($resource){
    return $resource('/api/films/comments/:idFilm');

}).factory("AddComments", function($resource){
    return $resource('/api/films/comments/add');

}).factory("DeleteComment", function($resource){
    return $resource('/api/films/comments/:id', {"id": "id"}, {
        delete: {method: 'DELETE'}
    })

}).factory("auth", function($cookies,$cookieStore,$location) {
    return {
        login: function (username, password) {
            $cookies.username = username, $cookies.password = password;
            $location.path("/homepage");
        },
        logout: function () {
            $cookies.remove("userName"), $cookies.remove("passWord");
            $cookieStore.remove("username"), $cookieStore.remove("password");
            userName = "";
            passWord = "";
            $location.path("/");
        },
        setCookieData: function (username, password) {
            userName = username;
            passWord = password;
            $cookies.put("userName", username);
            $cookies.put("passWord", password);
        },
        getUserCookieData: function () {
            userName = $cookies.get("userName");
            return userName;
        },
        getPassCookieData: function () {
            passWord = $cookies.get("passWord");
            return passWord;
        },
        checkStatus: function () {
            var rutasPrivadas = ["/homepage", "/filmspage", "/infoFilmpage", "/listspage", "/profile"];
            if (this.in_array($location.path(), rutasPrivadas) && $cookies.get("userName") == undefined) {
                $location.path("/");
            }
            if (this.in_array("/login", rutasPrivadas) && $cookies.get("userName") != undefined) {
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

}).controller('homepageController', ["$scope", "MainListFilm", "auth","FilmsMain", function ($scope, MainListFilm, auth, FilmsMain) {
    auth.checkStatus()
    $scope.loaded=false;
    $scope.listMainFilms = MainListFilm.query({ nickname: auth.getUserCookieData()}).$promise.then(function () {
        $scope.loaded=true;
    })
    $scope.filmsMain = FilmsMain.query({nickname: auth.getUserCookieData()})
    $scope.listMains = MainListFilm.query({ nickname: auth.getUserCookieData()})

}]).controller('listModifyController', ["$scope","$rootScope", "ListModify","sharedData", "$templateCache","List","$route",
    function ($scope,$rootScope, ListModify, sharedData, $templateCache, List, $route) {

        $scope.listModify = new ListModify();

        $rootScope.$on("editTitleList", function (event, list) {
            $scope.editList = list;
            $scope.$apply()
        });

        $scope.addFilmInList = function (id, action) {
            $scope.listModify.action = action;
            $scope.listModify.parameter = sharedData.getData('filmID');
            $scope.listModify.$create({id: id})
        }

        $scope.editTitle = function (action) {
            $scope.listModify.action = action;
            $scope.listModify.parameter = $scope.editList.title;
            $scope.listModify.$modify({id: $scope.editList.id});
            $(function () {
                $('#myModalEditList').modal('toggle');
            });
        }

        $scope.deleteFilmOnList = function (idList, idFilm, action){
            $scope.listModify.action = action;
            $scope.listModify.parameter = ''+idFilm+''
            $scope.listModify.$modify({id: idList},
                function(){
                    var currentPageTemplate = $route.current.templateUrl;
                    $templateCache.remove(currentPageTemplate);
                    $route.reload();
            });
        }

}]).controller('filmpageController', ["$scope", "$routeParams", "Film", "sharedData", "$location",
    "auth", "User", "$templateCache","$route","ListAdd","$timeout","Comments","AddComments","DeleteComment",
    function ($scope,$routeParams, Film, sharedData, $location, auth, User, $templateCache, $route, ListAdd,
              $timeout, Comments, AddComments, DeleteComment) {

        auth.checkStatus()

    $scope.film = Film.query({id: $routeParams.filmID});
    $scope.comments = Comments.query({idFilm: $routeParams.filmID});
    sharedData.setData('filmID', $routeParams.filmID);

    $scope.list = new ListAdd();
    $scope.newComment = new AddComments();
    $scope.user = User.query({nickname: auth.getUserCookieData(), pass: auth.getPassCookieData()});

        $scope.comment = [];

        $scope.btn_add = function() {
            if($scope.txtcomment !=''){
                $scope.newComment.idFilm = $routeParams.filmID;
                $scope.newComment.nickname = auth.getUserCookieData();
                $scope.newComment.comment = $scope.txtcomment;
                $scope.newComment.$save(function () {
                    $route.reload();
                });

            }
        }

        $scope.remItem = function($index) {
            DeleteComment.delete({id: $index}, function(){
                $route.reload();
            });
        }

        $scope.isUser= function(nickname){
            if(nickname == auth.getUserCookieData()) return true;
        }

    $scope.deleteFilm = function () {
        Film.delete({id: $routeParams.filmID}, function(){
            var currentPageTemplate = $route.current.templateUrl;
            $templateCache.remove(currentPageTemplate);
            $location.path("/filmspage")
        })
    }

    $scope.addList = function() {
        $scope.list.nickname = auth.getUserCookieData();
        $scope.list.$save(function(){

        });
        $(function () {
            $('#addListModal').modal('toggle');
        });
        $timeout(function(){
            var currentPageTemplate = $route.current.templateUrl;
            $templateCache.remove(currentPageTemplate);
            $route.reload();
        }, 180);

    }

}]).controller('infoGeneralFilmController', ["$scope","FilmGeneral","$routeParams","auth", "CopyFilmPublic","Comments","AddComments","DeleteComment","User","$route",
    function($scope, FilmGeneral, $routeParams, auth, CopyFilmPublic, Comments, AddComments, DeleteComment, User, $route){

    $scope.filmShared = FilmGeneral.query({id: $routeParams.filmID});
    auth.checkStatus()
    $scope.copyFilm = new CopyFilmPublic();

    $scope.comments = Comments.query({idFilm: $routeParams.filmID});

    $scope.addToMyFilms = function(){
            $scope.copyFilm.$copy({filmID: $routeParams.filmID, nickname: auth.getUserCookieData()})
    }

    $scope.newComment = new AddComments();
    $scope.user = User.query({nickname: auth.getUserCookieData(), pass: auth.getPassCookieData()});

    $scope.comment = [];

    $scope.btn_add = function() {
        if($scope.txtcomment !=''){
            $scope.newComment.idFilm = $routeParams.filmID;
            $scope.newComment.nickname = auth.getUserCookieData();
            $scope.newComment.comment = $scope.txtcomment;
            $scope.newComment.$save(function () {
                $route.reload();
            });
        }
    }

    $scope.remItem = function($index) {
        DeleteComment.delete({id: $index}, function(){
            $route.reload();
        });
    }

    $scope.isUser= function(nickname){
        if(nickname == auth.getUserCookieData()) return true;
    }


}]).controller('pathController', ["$scope","$sce", function($scope, $sce){

    $scope.trustSrc = function (ipLocal, ipRemote, ipConection, path) {
        if (ipLocal != undefined && ipConection != undefined && path != undefined) {
            if(ipConection==ipRemote){
                console.log("Soy la local: "+'http://' + ipLocal + ':9090'+"/" + path)
                return 'http://' + ipLocal + ':9090' + "/"+path
            }
            console.log("Soy la remota: "+'http://' + ipRemote + ':9090'+"/" + path)
            return 'http://' + ipRemote + ':9090' + "/"+path
        }
    }


}]).controller('listspageController', ["$scope","$rootScope", "ListFilm", "auth","List","$route","$templateCache", function ($scope, $rootScope, ListFilm,auth, List, $route, $templateCache) {

    auth.checkStatus()
    $scope.listFilms = ListFilm.query({ nickname: auth.getUserCookieData()})

    $scope.deleteList =  function(id) {
        List.delete({ id: id}, function(){
            var currentPageTemplate = $route.current.templateUrl;
            $templateCache.remove(currentPageTemplate);
            $route.reload();
        })
    }

    $scope.modalTitle = function(list){
        $rootScope.$emit("editTitleList", list);
    }


}]).controller('addFilmController', ["$scope", "FilmAdd", "$route", "auth","$templateCache", "$location",  function($scope, FilmAdd, $route, auth, $templateCache, $location) {
            auth.checkStatus()
            $scope.film = new FilmAdd();
            $scope.addFilm = function() {
                $scope.film.nickname = auth.getUserCookieData();
                $scope.film.$save(function(){
                    var currentPageTemplate = $route.current.templateUrl;
                    $templateCache.remove(currentPageTemplate);
                    $location.path("/filmspage");
                    $route.reload();
                });

                $(function () {
                    $('#myModal').modal('toggle');
                });


            }

}]).controller('editFilmController', ["$scope", "FilmEdit",    function($scope, FilmEdit) {
    $scope.instanceFilmEdit = new FilmEdit();
    $scope.editFilm = function() { //create a new movie. Issues a POST to /api/movies
        angular.copy($scope.film, $scope.instanceFilmEdit);
        $scope.instanceFilmEdit.$update(function(){
        });
        $(function () {
            $('#myModalEdit').modal('toggle');
        });
    };
}]).controller('filmsController', ["$scope", "Films","auth", "AllFilms", function($scope, Films, auth, AllFilms) {
    auth.checkStatus()

    $scope.films = Films.query({ nickname: auth.getUserCookieData()})
    $scope.allfilms = AllFilms.query();


}]).controller('expandCollapseCtrl', function ($scope) {
        $scope.activeMyFilms = false;
        $scope.activeAllFilms = false;

}).controller('ratingFilmsController', ["$scope", "sharedData","Film", function ($scope, sharedData, Film) {

    filmID = sharedData.getData("filmID")
    film = Film.query({id: filmID});
    sharedData.setData($scope.film.id+"Rating", $scope.film.rating)


}]).controller('ratingFilmController', ["$scope", "sharedData","Film", function ($scope, sharedData, Film) {

    this.isReadonly = true;
    filmID = sharedData.getData("filmID")
    $scope.film.rating = sharedData.getData(filmID+"Rating");


    this.rateFunction = function (rating) {
        console.log('Rating selected: ' + rating);
    };


}]).controller('genreController', ["Categories", "$scope", "Genre","auth", function(Categories, $scope, Genre, auth ) {
        $scope.researchCategories = function () {
            $scope.genres = Categories.get({nickname: auth.getUserCookieData()});
        }

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
                auth.setCookieData($scope.user.nickname, $scope.user.pass);
                $location.path("/homepage")
            }else{
                $location.path("/login");
            }
        });
    }

}]).controller('searchGenreController', ["Genre", "$routeParams","$scope", function(Genre, $routeParams, $scope){
    $scope.films = Genre.query({ genre: $routeParams.genre })

}]).controller('searchOrderController', ["Order", "$routeParams","$scope","auth", function(Order, $routeParams, $scope, auth){
    $scope.films = Order.query({ order: $routeParams.order, nickname: auth.getUserCookieData() })

}]).controller('searchTextController', ["Title","$scope","sharedData","$location","$route","auth", "$http", function(Title, $scope, sharedData, $location, $route, auth, $http){

    $scope.search = {
        'title' : null
    }
    $scope.addSearch = function(){
        $scope.films = Title.get({title: $scope.search.title, nickname: auth.getUserCookieData()});
        sharedData.setData('searchFilms', $scope.films);
        $location.path("/search");
        $route.reload();
    }
}]).controller('searchTitleController', ["$scope","sharedData", function($scope, sharedData, $location){
    $scope.films = sharedData.getData('searchFilms');

}]).controller('profileController', ["$scope","auth", "$location", function($scope, auth, $location){
    auth.checkStatus()
    $scope.nickname = auth.getUserCookieData();

    $scope.logout = function() {
        auth.logout();
        $location.path("/login");
    }

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
                            filled: i < scope.ratingValue
                        });
                    }
                };
                scope.toggle = function(index) {
                    if (scope.readonly == undefined || scope.readonly === false) {
                        scope.ratingValue = index + 1;
                        rating: index + 1;
                    }
                };
                updateStars();
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
            templateUrl: "/html/layout/main.html",
            reloadOnSearch:false
        }).when("/listspage", {
            controller: "listspageController",
            templateUrl: "/html/layout/listspage.html",
            reloadOnSearch:false
        }).when("/filmpage/:filmID", {
            controller: "filmpageController",
            templateUrl: "/html/layout/infoFilmPage.html"
        }).when("/infofilm/:filmID", {
            controller: "infoGeneralFilmController",
            templateUrl: "/html/layout/infoGeneralFilm.html"
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



$('#myCarousel').carousel({
    interval: 4000
})


function carousel(id, numberOfElements){
    $('#'+id+" .item").each(function() {

        var next = $(this), elementsToShow = numberOfElements > 6? 6: numberOfElements;

        for (var i = 1; i < elementsToShow; i++) {
            next = next.next();
            if (!next.length) {
                next = $(this).siblings(':first');
            }

            next.children(':first-child')
                .clone()
                .addClass("cloneditem-"+(i))
                .appendTo($(this));
        }
    })
}


