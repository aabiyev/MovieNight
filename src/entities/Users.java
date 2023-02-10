package entities;

import Exeptions.InncorrectPassword;

public class Users {
    private int id;
    private String name;
    private String password;
    private String role;
    private double balance = 0;
    public Users(int id, String name,String password, String role) throws InncorrectPassword {
        isRightPassword(password);
        setId(id);
        setName(name);
        setPassword(password);
        setRole(role);
    }
    public Users(String name,String password, String role,double balance) throws InncorrectPassword{
        isRightPassword(password);
        setName(name);
        setPassword(password);
        setRole(role);
        setBalance(balance);
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

    public void isRightPassword(String password) throws InncorrectPassword {
        char[] pas = password.toCharArray();
        int n = password.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if((pas[i]>='A' && pas[i]<='Z') || (pas[i]>='a' && pas[i] <='z') ){
                cnt++;
            }
            if(pas[i] >='0' && pas[i]<='9'){
                cnt++;
            }
        }
        if(n>0 && n<8){
            throw new InncorrectPassword("Your password should contain at least 8 digits");
        }
        if(n != cnt){
            throw new InncorrectPassword("Your password should contain only numbers and character");
        }
    }
    @Override
    public String toString() {
        return "id = "+ id + "name = " + name + "role = "+ role+'\n';
    }
}
