package repository.interfaces;
import entities.Users;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    boolean createUser(Users users, String role) throws SQLException, ClassNotFoundException;
    Users SignIn(String name, String password);
    List<Users> getAllUsers();
    boolean exceptUser(String name);
}
