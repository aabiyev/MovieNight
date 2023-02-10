package entities;
public class Client extends Users{
    public Client(String name, String password,double balance) throws Exception {
        super(name,password,"client",balance);

    }


}
