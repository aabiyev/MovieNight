package repository.interfaces;
import entities.Movie;

import java.util.List;

public interface IMovieRepository {
    boolean createMovie(Movie movie);
    List<Movie> allMovie();
    boolean UpdateMovie(int id);
    boolean DeleteMovie(int id);
}
