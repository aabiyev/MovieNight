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
        try {
            con = db.getConnection();
            String sql = "INSERT INTO movies (movie, movie_about, price, price_forChildren,3D) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, movie.getMovie());
            st.setString(2, movie.getMovie_about());
            st.setDouble(3, movie.getPrice());
            st.setDouble(4,movie.getPriceForChildren());
            st.setBoolean(5,movie.isDdd());
            st.execute();
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
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
    public List<Movie> allMovie() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT * FROM movies";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Movie> movies = new LinkedList<>();
            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("id"), rs.getString("movie"), rs.getString("movie_about"), rs.getDouble("price"), rs.getDouble("price_forChildren"),rs.getBoolean("3D"));
                movies.add(movie);
            }
            return movies;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
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
        try {
            con = db.getConnection();
            System.out.println("Please input new data");
            double neww = in.nextDouble();
            String sql = " UPDATE movies SET price = (?) WHERE id = (?) ";
            PreparedStatement st = con.prepareStatement(sql);
            st.setDouble(1, neww);
            st.setInt(2, id);

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
    public boolean DeleteMovie(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = " DELETE FROM movies WHERE id = (?) ";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

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
    public boolean DeleteUser(int id) {
        Connection con = null;
        try{
            con = db.getConnection();

            PreparedStatement st = con.prepareStatement("DELETE FROM movie_users WHERE id=(?)");
            st.setInt(1,id);

            st.execute();
            return true;
        }  catch (SQLException throwables) {
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
