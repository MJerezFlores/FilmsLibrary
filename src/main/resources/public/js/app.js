angular.module('app', ['ui.router', 'ngResource', 'app.controllers', 'app.services']);

angular.module('app').config(function($stateProvider) {
    $stateProvider.state('films', { // state for showing all movies
        url: '/films',
        templateUrl: 'homepage.html',
        controller: 'homepageController'
    }).state('viewFilm', { //state for showing single movie
        url: '/film/:filmID',
        templateUrl: 'infoFilmpage.html',
        controller: 'filmpageController'

    //}).state('newMovie', { //state for adding a new movie
    //    url: '/movies/new',
    //    templateUrl: 'partials/movie-add.html',
    //    controller: 'MovieCreateController'
    //}).state('editMovie', { //state for updating a movie
    //    url: '/movies/:id/edit',
    //    templateUrl: 'partials/movie-edit.html',
    //    controller: 'MovieEditController'
    });
}).run(function($state) {
    $state.go('films'); //make a transition to movies state when app starts
});
