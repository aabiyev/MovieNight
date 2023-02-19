package repository.interfaces;

public interface IClientRepository {
    boolean buyTicket(int id);
    boolean returnTicket();
    void MyAccount();
    void incBalance();
    boolean idExits(int id);
}