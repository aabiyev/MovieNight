package controller;

import entities.Movie;
import repository.interfaces.IMovieRepository;

import java.util.List;

public class AdminController {
    private final IMovieRepository repo;

    public AdminController(IMovieRepository repo) {
        this.repo = repo;
    }
    public String createMovie(Movie movie){
        boolean created = repo.createMovie(movie);
        return (created ? "Movie successfully created " : "Movie creation was failed");
    }
    public String UpdateMovie(int id){
        boolean created = repo.UpdateMovie(id);
        return (created ? "Movie which id is " +id +" was successfully updated " : "Movie update was failed");
    }
    public String DeleteMovie(int id){
        boolean created = repo.DeleteMovie(id);
        return (created ? "Movie which id is " +id +" was successfully deleted " : "Movie delete was failed");
    }
    public List<Movie> allMovie(){
        return repo.allMovie();
    }
    public String DeleteUsers(int id){
        boolean deleted = repo.DeleteUser(id);
        UserController.currentUser = null;
        return (deleted ? "User deleted" : "Something went wrong");
    }
}
