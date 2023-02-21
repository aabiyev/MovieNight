package repository.interfaces;
import entities.Items;
import entities.Movie;

import java.util.List;

public interface IMovieRepository {
    boolean createMovie(Movie movie);
    List<Movie> allMovie();
    boolean UpdateMovie(int id);
    boolean DeleteMovie(int id);
    boolean DeleteUser(int id);
    boolean UserExist(int id);
    boolean CreateItem (Items item);
    boolean DeleteItem(int id);
    boolean UpdateItem(int id);
    List<Items> allitem();
    boolean ItemExits(int id);



}
