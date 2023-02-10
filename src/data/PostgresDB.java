package data;
import data.interfaces.IDB;

import java.sql.*;

public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionURl = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con= DriverManager.getConnection(connectionURl,"postgres", "Just_arys7");
            return con;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
