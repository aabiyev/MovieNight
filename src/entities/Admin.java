package entities;
public class Admin extends Users{
    public Admin(String name, String password) throws Exception {
        super(name, password, "Admin",0);
    }



}
