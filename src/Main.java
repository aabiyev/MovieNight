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
        IUserRepository userRepository = new UserRepository(db);
        IMovieRepository adminRepository = new AdminRepository(db);
        IClientRepository clientRepository = new ClientRepository(db);
        UserController userController = new UserController(userRepository);
        AdminController adminController  = new AdminController(adminRepository);
        ClientController clientController = new ClientController(clientRepository);
        MyApplication app = new MyApplication(userController,adminController, clientController);
        app.start();
    }
}
