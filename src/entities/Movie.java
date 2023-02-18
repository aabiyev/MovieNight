package entities;
public class Movie {
    private int id;
    private String movie;
    private String movie_about;
    private String genre;
    private int rating;
    private double price;
    private double priceForChildren;
    public Movie(int id, String movie, String movie_about, double price, double priceForChildren, int rating, String genre) {
        this.id = id;
        this.movie = movie;
        this.movie_about = movie_about;
        this.price = price;
        setPriceForChildren(priceForChildren);
        setRating(rating);
        setGenre(genre);
    }
    public Movie( String movie, String movie_about, double price, double priceForChildren, int rating, String genre) {
        this.movie = movie;
        this.movie_about = movie_about;
        this.price = price;
        setPriceForChildren(priceForChildren);
        setRating(rating);
        setGenre(genre);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public double getPriceForChildren() {
        return priceForChildren;
    }

    public void setPriceForChildren(double priceForChildren) {
        this.priceForChildren = priceForChildren;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getMovie_about() {
        return movie_about;
    }

    public void setMovie_about(String movie_about) {
        this.movie_about = movie_about;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", movie_about='" + movie_about + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                "+, price=" + price +
                ", priceForChildren=" + priceForChildren +
                '}'+'\n';
    }
}
