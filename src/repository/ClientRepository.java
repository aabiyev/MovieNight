package repository;

import controller.UserController;
import data.interfaces.IDB;
import repository.interfaces.IClientRepository;
import java.sql.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class ClientRepository implements IClientRepository {
    private final IDB db;
    private static Scanner in = new Scanner(System.in);
    public ClientRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean buyTicket(int id) {
        Connection con = null;
        try{
            con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM movies");
            PreparedStatement statement = con.prepareStatement("UPDATE movie_users SET balance=(?), ticket_id=(?) WHERE name=(?)");
            double newBalance = 0;
            while(rs.next()){
                try{
                if(id == rs.getInt("id")) {
                    if (UserController.currentUser.getAge() >= rs.getInt("rating")) {
                        if (UserController.currentUser.getAge() >= 18) {
                            newBalance = UserController.currentUser.getBalance() - rs.getDouble("price");
                        }
                    } else {
                        newBalance = UserController.currentUser.getBalance() - rs.getDouble("children");
                    }
                    if(newBalance < 0) {
                        System.out.println("""
                                      You don't have enough funds
                                      1. Replenish the balance
                                      0. Exit""");
                        int choice = in.nextInt();
                        switch (choice) {
                            case 1 -> {
                                incBalance();
                                buyTicket(id);
                            }
                            case 2 -> exit(0);
                        }
                    } else{
                    statement.setDouble(1, newBalance);
                    statement.setInt(2, rs.getInt("id"));
                    statement.setString(3, UserController.currentUser.getName());
                    statement.execute();
                    UserController.currentUser.setBalance(newBalance);
                    UserController.currentUser.setId(id);
                    return true;
                    }
                }
                else{
                      System.out.println("You are too young for this film.");
                  }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException throwables) {
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

    @Override
    public boolean returnTicket() {
        Connection con = null;
        try{
            con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM movies");
            PreparedStatement statement = con.prepareStatement("UPDATE movie_users SET balance=(?), ticket_id=(?) WHERE name=(?)");
            double newBalance = 0;
            while(rs.next()){
                try{
                    if(UserController.currentUser.getId() == rs.getInt("id")){

                      if(UserController.currentUser.getAge() >=18)  {
                            newBalance = UserController.currentUser.getBalance() + rs.getDouble("price");
                        }
                      else {
                          newBalance = UserController.currentUser.getBalance() + rs.getDouble("children");
                      }
                        statement.setDouble(1, newBalance);
                        statement.setInt(2, 0);
                        statement.setString(3, UserController.currentUser.getName());
                        statement.execute();
                        UserController.currentUser.setBalance(newBalance);
                        UserController.currentUser.setId(0);
                        return true;
                    }
                } catch (SQLException e){
                    e.printStackTrace();
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

    @Override
    public void MyAccount() {
        Connection con = null;
        try{
           con = db.getConnection();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM items");
           Statement stt = con.createStatement();
           ResultSet rss = stt.executeQuery("SELECT * FROM movies");
           String response = "Name = " + UserController.currentUser.getName() + ", balance =  " + UserController.currentUser.getBalance();
           while( rss.next()){
               if(UserController.currentUser.getId() == rss.getInt("id")){
                   response = response + ", ticket = " + rss.getString("movie");
                  if(UserController.currentUser.getItem_id() != 0) {
                       while (rs.next()) {
                           if (UserController.currentUser.getItem_id() == rs.getInt(1)) {
                               response = response + ", item = "+ rs.getString("name");
                           }
                       }
                   }
               }
           }
            System.out.println(response);
        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void incBalance(){
        Connection con = null;
        double newBalance = 0;
        try{
            System.out.println("Please, input additional balance:");
            newBalance = in.nextDouble();
            con = db.getConnection();

            PreparedStatement st = con.prepareStatement("UPDATE movie_users SET balance=(?) WHERE name=(?)");
            st.setDouble(1,newBalance+UserController.currentUser.getBalance());
            st.setString(2,UserController.currentUser.getName());
            UserController.currentUser.setBalance(newBalance);
            st.execute();

        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public boolean idExits(int id){
        Connection con = null;
        try{
            con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM movies");

            while (rs.next()){
                if(rs.getInt("id") == id){
                    return true;
                }
            }


        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean buyItem(int id) {
        Connection con = null;
        try{
            con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM items");
            PreparedStatement statement = con.prepareStatement("UPDATE movie_users SET balance=(?), item_id=(?) WHERE name=(?)");
            double balance = 0;
            while(rs.next()) {
                if(id == rs.getInt(1)) {
                    balance = UserController.currentUser.getBalance() - rs.getDouble("price");
                    if(balance < 0) {
                        System.out.println("""
                                          You don't have enough funds
                                          1. Replenish the balance
                                          0. Exit""");
                        int choice = in.nextInt();
                        switch (choice) {
                            case 1 -> {
                                incBalance();
                                buyItem(id);
                            }
                            case 2 -> exit(0);
                        }
                    }
                    else{
                    statement.setDouble(1,balance);
                    statement.setInt(2, id);
                    statement.setString(3,UserController.currentUser.getName());
                    UserController.currentUser.setBalance(balance);
                    UserController.currentUser.setItem_id(id);
                    statement.execute();
                    return true;}
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
        return false;
     }
    }

