angular.module('myApp', []).controller('formController', ['$scope', function($scope) {
        $scope.film = {};

        $scope.create = function(film) {
            $scope.film = angular.copy(film);
            console.log($scope.film);
        };
}]).controller('infoFilmController', ['$scope', function($scope) {

    $scope.fields = {
        "id":9,
        "title": "en busca del valle encantado III",
        "synopsis": "la peli es toda bonita, hay que verla",
        "genre": "comedia",
        "year": 1990,
        "director": "",
        "actors": "",
        "rating":9.0,
        "urlImage": "http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg" ,
        "path" : "asd"
    };

    $scope.updateRadioChoices = function(field, choice) {
        $.each(field.choices, function(i, val) {
            if (val.id != choice.id) val.selected = false;
        });
    }
}]).controller('mainListController', ['$scope', function($scope) {
    $scope.listMainFilms = [
        {   "id":55,
            "nickname":"Juan",
            "title":"Aserejé",
            "films":[]},
        {   "id":56,
            "nickname":"Juan",
            "title":"Acción",
            "films":[{"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"https://quealucine.files.wordpress.com/2011/04/oso.jpg"},
                {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
        {   "id":57,
            "nickname":"Juan",
            "title":"Comedia",
            "films":[{"id":11,"title":"Suicide Squad","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
        {   "id":58,"nickname":"Juan",
            "title":"Animacion",
            "films":[{"id":12,"title":"Totoro", "year":0,"rating":0.0, "urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
        {   "id":59,
            "nickname":"Juan",
            "title":"Terror",
            "films":[{"id":13,"title":"One Piece","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
            {"id":14, "title":"Super Mario", "year":0, "rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
        {   "id":60,
            "nickname":"Juan",
            "title":"Drama",
            "films":[]}
    ]
}]).controller('listsController', ['$scope', function($scope) {
        $scope.listFilms = [
            {   "id":55,
                "nickname":"Juan",
                "title":"Aserejé",
                "films":[]},
            {   "id":56,
                "nickname":"Juan",
                "title":"Acción",
                "films":[{"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"https://quealucine.files.wordpress.com/2011/04/oso.jpg"},
                    {"id":10,"title":"Sailor Moon","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
            {   "id":57,
                "nickname":"Juan",
                "title":"Comedia",
                "films":[{"id":11,"title":"Suicide Squad","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
            {   "id":58,"nickname":"Juan",
                "title":"Animacion",
                "films":[{"id":12,"title":"Totoro", "year":0,"rating":0.0, "urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
            {   "id":59,
                "nickname":"Juan",
                "title":"Terror",
                "films":[{"id":13,"title":"One Piece","year":0,"rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"},
                    {"id":14, "title":"Super Mario", "year":0, "rating":0.0,"urlImage":"http://orig02.deviantart.net/532f/f/2010/017/9/4/solito_by_masklin8.jpg"}]},
            {   "id":60,
                "nickname":"Juan",
                "title":"Drama",
                "films":[]}
        ]
}]).directive("carouselMultipleElement", function(){
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
    }
}).service('sharedFilm', function () {
    var property;

    return {
        getFilm: function () {
            return property;
        },
        setFilm: function(value) {
            property = value;
            console.log(property);
        }
    };
}).controller('obtainFilmController', function obtainFilm($scope, sharedFilm) {

    $scope = sharedFilm.getProperty();

});





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
