package controller;

import entities.Items;
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
        return (created ? "Movie successfully created! " : "Movie creation was failed.");
    }
    public String UpdateMovie(int id){
        boolean created = repo.UpdateMovie(id);
        return (created ? "Movie which id is: " +id +" was successfully updated! " : "Movie update was failed.");
    }
    public String DeleteMovie(int id){
        boolean created = repo.DeleteMovie(id);
        return (created ? "Movie which id is: " +id +" was successfully deleted! " : "Movie delete was failed.");
    }
    public List<Movie> allMovie(){
        return repo.allMovie();
    }
    public String DeleteUsers(int id){
        boolean deleted = repo.DeleteUser(id);
        UserController.currentUser = null;
        return (deleted ? "User deleted!" : "Something went wrong...");
    }
    public boolean UserExist(int id){
        return repo.UserExist(id);
    }
    public List<Items> AllItems(){
        return repo.allitem();
    }
    public String DeleteItems(int id){
        boolean created = repo.DeleteItem(id);
        return (created ? "Item which id is: " +id +" was successfully deleted! " : "Item delete was failed.");
    }
    public String CreateItem(Items item){
        boolean created = repo.CreateItem(item);
        return (created ? "Item successfully created! " : "Item creation was failed.");
    }
    public String UpdateItem(int id){
        boolean created = repo.UpdateItem(id);
        return (created ? "Item which id is: " +id +" was successfully updated! " : "Item update was failed.");
    }

    public boolean ItemExist(int id){
        return repo.ItemExits(id);
    }
}
