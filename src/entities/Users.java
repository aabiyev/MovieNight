package entities;

public class Users {
    private int id = 0;
    private String name;
    private String password;
    private String role;
    private int age;
    private double balance = 0;
    public Users(String name,String password,String role)  {
        setName(name);
        setPassword(password);
        setRole(role);
    }
    public Users(String name,String password, String role,double balance,int age){
        setName(name);
        setPassword(password);
        setRole(role);
        setBalance(balance);
        setAge(age);
    }
    public Users(int id,String name, String role){
        setId(id);
        setName(name);
        setRole(role);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id = "+ id + ", name = " + name + ", role = "+ role+'\n';
    }
}
