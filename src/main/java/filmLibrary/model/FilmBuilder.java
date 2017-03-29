package filmLibrary.model;

public interface FilmBuilder<Type> {

    FilmBuilder synopsis (String synopsis);

    FilmBuilder year (int year);

    FilmBuilder director (String director);

    FilmBuilder actors (String actors);

    FilmBuilder rating (float rating);

    FilmBuilder path (String path);

    FilmBuilder urlImage (String urlImage);

    FilmBuilder genre (String genre);

    FilmBuilder nickname (String nickname);

    FilmBuilder idPublicFilm (int idPublicFilm);

    Type build();
}
