package entities;
public class Items {
    private int id;
    private String name;
    private double price;

    public Items(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Items(String name,double price){
        setName(name);
        setPrice(price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "id="+id+", good="+name+", price="+price+'\n';
    }

}
