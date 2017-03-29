package filmLibrary.model;

public class FilmPublic {
    private int idPublicFilm;
    private final String title;
    private final String synopsis;
    private final int year;
    private final String director;
    private final String actors;
    private final String genre;
    private final String urlImage;
    private final String nickname;



    private FilmPublic(Builder builder){
        this.idPublicFilm = builder.idPublicFilm;
        this.title = builder.title;
        this.synopsis = builder.synopsis;
        this.year = builder.year;
        this.director = builder.director;
        this.actors = builder.actors;
        this.genre=builder.genre;
        this.urlImage = builder.urlImage;
        this.nickname = builder.nickname;

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

    public String getUrlImage() {
        return urlImage;
    }

    public String getGenre() {
        return genre;
    }

    public String getNickname() {
        return nickname;
    }

    public int getIdPublicFilm() {return idPublicFilm;}

    public void setIdPublicFilm(int id){
        idPublicFilm = id;
    }

    public static class Builder implements FilmBuilder<FilmPublic>{
        private final int idPublicFilm;
        private final String title;
        private String synopsis;
        private int year;
        private String director;
        private String actors;
        private String urlImage;
        private String genre;
        private String nickname;


        public Builder(int idPublicFilm, String title){
            this.idPublicFilm = idPublicFilm;
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

        @Override
        public FilmBuilder rating(float rating) {
            return null;
        }

        @Override
        public FilmBuilder path(String path) {
            return null;
        }

        public Builder urlImage (String urlImage) {
            this.urlImage = urlImage;
            return this;
        }

        public Builder genre (String genre) {
            this.genre = genre;
            return this;
        }

        public Builder nickname (String nickname) {
            this.nickname = nickname;
            return this;
        }

        @Override
        public FilmBuilder idPublicFilm(int idPublicFilm) {
            return null;
        }

        @Override
        public FilmPublic build(){
            return new FilmPublic(this);
        }

    }

}
