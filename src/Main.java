import controller.AdminController;
import controller.ClientController;
import controller.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repository.AdminRepository;
import repository.ClientRepository;
import repository.UserRepository;
import repository.interfaces.IClientRepository;
import repository.interfaces.IMovieRepository;
import repository.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        IDB db = new PostgresDB();
        IUserRepository repo = new UserRepository(db);
        IMovieRepository rep = new AdminRepository(db);
        IClientRepository repoo = new ClientRepository(db);
        UserController controller = new UserController(repo);
        AdminController cont  = new AdminController(rep);
        ClientController con = new ClientController(repoo);
        MyApplication app = new MyApplication(controller,cont, con);
        app.start();
    }
}