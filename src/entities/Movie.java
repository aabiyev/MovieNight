package entities;
public class Movie {
    private int id;
    private String movie;
    private String movie_about;
    private double price;
    private double priceForChildren;
    private boolean ddd;
    public Movie(int id, String movie, String movie_about, double price, double priceForChildren,boolean ddd) {
        this.id = id;
        this.movie = movie;
        this.movie_about = movie_about;
        this.price = price;
        setPriceForChildren(priceForChildren);
        setDdd(ddd);
    }
    public Movie( String movie, String movie_about, double price, double priceForChildren,boolean ddd) {
        this.movie = movie;
        this.movie_about = movie_about;
        this.price = price;
        setPriceForChildren(priceForChildren);
        setDdd(ddd);
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

    public boolean isDdd() {
        return ddd;
    }

    public void setDdd(boolean ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movie='" + movie + '\'' +
                ", movie_about='" + movie_about + '\'' +
                ", price=" + price +
                ", price for children=" + priceForChildren +
                ", is 3d=" + ddd+
                '}'+'\n';
    }
}
