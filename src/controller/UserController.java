package controller;
import entities.Users;
import repository.interfaces.IUserRepository;

import java.util.List;

public class UserController {
    private final IUserRepository repo;
    public static Users currentUser;

    public UserController(IUserRepository repo){
        this.repo = repo;
    }

    public String createUsers(String name,String password, String role) throws Exception {
        Users users = new Users(name, password, role);
        currentUser = users;
        boolean created = repo.createUser(users,role);
        return (created ? "Admin was created!" : "Admin creation was failed.");
    }
    public String createUsers(String name,String password, String role, double balance, int age) throws Exception {
        Users users = new Users(name, password,role,balance,age);
        currentUser = users;
        boolean created = repo.createUser(users,role);
        return (created ? "Client was created!" : "Client creation was failed.");
    }

    public boolean SignIn(String name, String password){
        currentUser = repo.SignIn(name,password);
        if( currentUser==null){
            return false;
        }
        else return true;

    }
    public void LogOut(){
        currentUser = null;
    }
    public String getAllUsers(){
        List<Users> users1 = repo.getAllUsers();
        return users1.toString();
    }
    public boolean UserExist(String name){
        return repo.exceptUser(name);
    }
}