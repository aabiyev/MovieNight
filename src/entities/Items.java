package entities;

public class Items {
    private  int id;
    private static int id_gen;
    private String name;
    private double price;
    private int itemNum;
    public Items(String name,double price,int itemNum){
        id=id_gen++;
        setName(name);
        setPrice(price);
        setItemNum(itemNum);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        itemNum--;
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", itemNum=" + itemNum +
                '}';
    }
}
