package filmLibrary.model;

import java.time.Year;

public class Film {
    private final int id;
    private final String title;
    private final String synopsis;
    private final Year year;
    private final String director;
    private final String[] actors;
    private final float rating;
    private final String urlImage;
    private final String path;

    private Film(FilmBuilder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.synopsis = builder.synopsis;
        this.year = builder.year;
        this.director = builder.director;
        this.actors = builder.actors;
        this.rating = builder.rating;
        this.urlImage = builder.urlImage;
        this.path = builder.path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Year getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDirector() {
        return director;
    }

    public String[] getActors() {
        return actors;
    }

    public float getRating() {
        return rating;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getPath() {
        return path;
    }

    public static class FilmBuilder {
        private final String title;
        private final int id;
        private String synopsis;
        private Year year;
        private String director;
        private String[] actors;
        private float rating;
        private String urlImage;
        private String path;

        public FilmBuilder(int id, String title){
            this.id = id;
            this.title = title;
        }

        public FilmBuilder synopsis (String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public FilmBuilder year (Year year) {
            this.year = year;
            return this;
        }

        public FilmBuilder director (String director) {
            this.director = director;
            return this;
        }

        public FilmBuilder actors (String[] actors) {
            this.actors = actors;
            return this;
        }

        public FilmBuilder rating (float rating) {
            this.rating = rating;
            return this;
        }

        public FilmBuilder path (String path) {
            this.path = path;
            return this;
        }

        public FilmBuilder urlImage (String urlImage) {
            this.urlImage = urlImage;
            return this;
        }
    }
}