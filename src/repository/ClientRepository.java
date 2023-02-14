package repository;

import controller.UserController;
import data.interfaces.IDB;
import repository.interfaces.IClientRepository;

import java.sql.*;
import java.util.Scanner;

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
                if(id == rs.getInt("id")){
                    newBalance = UserController.currentUser.getBalance() - rs.getDouble("price");
                    if(newBalance<0) return false;
                    else{
                    statement.setDouble(1, newBalance);
                    statement.setInt(2,rs.getInt("id"));
                    statement.setString(3,UserController.currentUser.getName());
                    statement.execute();
                    UserController.currentUser.setBalance(newBalance);
                    UserController.currentUser.setId(id);
                    return true;
                    }
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
                        newBalance = UserController.currentUser.getBalance() + rs.getDouble("price");
                        statement.setDouble(1, newBalance);
                        statement.setInt(2,0);
                        statement.setString(3,UserController.currentUser.getName());
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
           Statement stt = con.createStatement();
           ResultSet rss = stt.executeQuery("SELECT  * FROM movies");
           while( rss.next()){
               if(UserController.currentUser.getId() == rss.getInt("id")){
                   System.out.println(" name = " + UserController.currentUser.getName() +" balance = "+ UserController.currentUser.getBalance() + " ticket = "+rss.getString("movie"));
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
    }
    public void incBalance(){
        System.out.println("");
    }

}
