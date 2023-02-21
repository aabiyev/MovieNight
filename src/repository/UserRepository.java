package repository;
import data.interfaces.IDB;
import entities.Users;
import repository.interfaces.IUserRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class UserRepository implements IUserRepository {
    private final IDB db;
    private static Scanner in = new Scanner(System.in);
    public UserRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createUser(Users users, String role) {
        boolean created = false;
        while (true) {
            if (role.equalsIgnoreCase("admin")) {
                System.out.println("Please, enter secret word: ");
                String secret = in.next();
                if (secret.equalsIgnoreCase("KhaimuldinsBEST")) {
                    create(users, "admin");
                    created = true;
                    break;
                } else {
                    System.out.println("""
                            Secret word is incorrect!
                            1 Try again
                            2 Go as a client
                            0 Exit
                            """);
                    int choice = in.nextInt();
                    switch (choice) {
                        case 1 -> createUser(users, "admin");
                        case 2 -> {
                            create(users, "client");
                            created = true;
                        }
                        default -> exit(0);
                    }
                }
            } else if (role.equalsIgnoreCase("client")) {

                create(users, "client");
                created = true;
                break;
            } else {
                System.out.println("Please enter ADMIN or CLIENT");
                String str = in.next();
                createUser(users, str);
                created = true;
                break;
            }

        }
        return created;
    }
    @Override
    public Users SignIn(String name,String password) {
          Connection con = null;
          String role = null;
          boolean exist = false;
          Users user=null ;
          try{
              con = db.getConnection();
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery("SELECT * FROM movie_users");
              while(rs.next()){
                  if((rs.getString("name").equals(name)&& rs.getString("password").equals(password))){
                     if(rs.getString("role").equals("admin")){
                         try{
                             user = new Users(rs.getString("name"), rs.getString("password"),rs.getString("role"));
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     } else if (rs.getString("role").equals("client")) {
                      try {
                             user = new Users( rs.getString("name"), rs.getString("password"),rs.getString("role"), rs.getDouble("balance"),rs.getInt("age"));
                         } catch (Exception e) {
                          e.printStackTrace();
                      }
                     }
                  }
              }
          } catch (SQLException throwables) {
        throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
          }
         return user;
    }
    @Override
    public List<Users> getAllUsers() {
        Connection con = null;
        try {
            con = db.getConnection();

            String sql = "SELECT id,name,role FROM movie_users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Users> users = new LinkedList<>();
            while (rs.next()){
                Users user = new Users(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"));
                users.add(user);
            }
                return users;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public boolean exceptUser(String name) {
        Connection con = null;
        try{
            con = db.getConnection();
            Statement st  = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT  * FROM movie_users");
            while(rs.next()){
                if(Objects.equals(name, rs.getString("name"))){
                    return true;
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return false;
    }

    public void create(Users users,String role){
        Connection con = null;
        String sql;
        try {
            con = db.getConnection();
            sql = "INSERT INTO movie_users(name, password, role, balance, age) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, users.getName());
            st.setString(2, users.getPassword());
            st.setString(3, role);
            st.setDouble(4, users.getBalance());
            st.setInt(5,users.getAge());

            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}