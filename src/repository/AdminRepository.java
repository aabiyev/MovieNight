package repository;

import data.interfaces.IDB;
import entities.Movie;
import repository.interfaces.IMovieRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminRepository implements IMovieRepository {
    private final IDB db;
    private static Scanner in = new Scanner(System.in);
    public AdminRepository(IDB db) {
        this.db = db;
    }


    @Override
    public boolean createMovie(Movie movie) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "INSERT INTO movies (movie, movie_about, price) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,movie.getMovie());
            st.setString(2, movie.getMovie_about());
            st.setDouble(3,movie.getPrice());

            st.execute();
            return true;
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

    @Override
    public List<Movie> allMovie() {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql = "SELECT * FROM movies";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Movie> movies = new LinkedList<Movie>();
            while (rs.next()){
                Movie movie = new Movie(rs.getInt("id"), rs.getString("movie"),rs.getString("movie_about"),rs.getDouble("price"));
                movies.add(movie);
            }
            return movies;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e) {
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
    public boolean UpdateMovie(int id) {
        Connection con = null;
        try{
            con = db.getConnection();
            System.out.println("Please input new data");
            double neww = in.nextDouble();
            String sql =" UPDATE movies SET price = (?) WHERE id = (?) ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, neww);
            st.setInt(2, id);

            st.execute();
            return true;
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
    public boolean DeleteMovie(int id) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql =" DELETE FROM movies WHERE id = (?) ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            st.execute();
            return true;
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
    public boolean TimeExist(int hour, int minute){
        return (hour <= 24 && hour >= 0) && (minute <= 59 && minute >= 0);
    }

    public boolean DateExist(int day, int month, int year){
        int feb = 28 ;
        if(year%4==0){
            if(!(year%100 == 0)){
                feb=29;
            } else if (year%400==0) {
                feb=29;
            }
        }
        if(month<=12&&day<=31)
        {
            switch (month){
                case 1:
                    if(day<=31){
                        return true;
                    }
                case 2:
                    if (day<=feb){
                        return true;
                    }
                case 3:
                    if (day<=31){
                        return true;
                    }
                case 4:
                    if (day <= 30)
                    {
                        return true;
                    }
                case 5:
                    if (day <= 31)
                    {return true;
                    }
                case 6:
                    if (day <= 30) return true;

                case 7:
                    if (day <= 31)return true;


                case 8:
                    if (day <= 31) return true;


                case 9:
                    if (day <= 30)
                    {return true;}
                case 10:
                    if (day <= 31)
                    {return true;}
                case 11:
                    if (day <= 30)
                    {
                        return true;
                    }
                case 12:
                    if (day <= 31)
                    {
                        return true;
                    }

            }

        }
        return false;
        }
}
