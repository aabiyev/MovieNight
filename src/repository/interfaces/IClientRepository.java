package repository.interfaces;

public interface IClientRepository {
    boolean buyTicket(int id);
    boolean returnTicket();
    void MyAccount();
}
//What do you want to do?
//        1. see all movie
//        2. buy ticket
//        3. return ticket
//        4. log out from my account
//        5. My items
//        0. Exit