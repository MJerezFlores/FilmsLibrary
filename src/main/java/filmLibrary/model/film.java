package filmLibrary.model;

public class Film {
    private final int id;
    private final String title;
    private final String synopsis;
    private final int year;
    private final String director;
    private final String actors;
    private final float rating;
    private final String urlImage;
    private final String path;

    private Film(Builder builder){
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

    public int getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
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

    public static class Builder {
        private final String title;
        private final int id;
        private String synopsis;
        private int year;
        private String director;
        private String actors;
        private float rating;
        private String urlImage;
        private String path;

        public Builder(int id, String title){
            this.id = id;
            this.title = title;
        }

        public Builder synopsis (String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public Builder year (int year) {
            this.year = year;
            return this;
        }

        public Builder director (String director) {
            this.director = director;
            return this;
        }

        public Builder actors (String actors) {
            this.actors = actors;
            return this;
        }

        public Builder rating (float rating) {
            this.rating = rating;
            return this;
        }

        public Builder path (String path) {
            this.path = path;
            return this;
        }

        public Builder urlImage (String urlImage) {
            this.urlImage = urlImage;
            return this;
        }

        public Film build(){
            return new Film(this);
        }

    }

}
