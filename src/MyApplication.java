
import java.util.Scanner;

import controller.AdminController;
import controller.ClientController;
import controller.UserController;
import entities.Movie;

import static java.lang.System.exit;

public class MyApplication {
    private final UserController controller;
    private final AdminController cont;
    private final ClientController con;
    private final Scanner scanner;
    public MyApplication(UserController controller, AdminController cont, ClientController con){
        this.controller = controller;
        this.cont=cont;
        this.con = con;
        scanner = new Scanner(System.in);

    }

    public void start(){
            System.out.println("""
        Welcome to the Movie night
        selecr option:
        1. Sigh up
        2. Log in
        3. All Users
        0. Exit
        Enter option (1-3):
        """);
            try {

                int option = scanner.nextInt();
                switch (option){
                    case 1 -> createUserMenu();
                    case 2 -> SignIn();
                    case 3 -> getAllUsersMenu();
                    default -> exit(0);
                }
            } catch (Exception e) {
                System.out.println((e.getMessage()));
                scanner.nextLine();
            }
        }
    public void forAdmin(){
        System.out.println("""
                What do you want to do?
                1. Create movie
                2. Update movie
                3. Delete movie
                4. log out from my account
                5. All movie
                0. Exit
                Enter option(1-5)""");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 -> createMovie();
            case 2 -> updateMovie();
            case 3 -> deleteMovie();
            case 4 -> logOut();
            case 5 -> AllMovie();
            default -> exit(0);
        }
    }
    public void createMovie(){
        String movie= null;
       try {
            System.out.println("Please input name of the movie!");
            movie = scanner.nextLine();
        }
       catch (Exception e){
           e.printStackTrace();
       }
       String mov = scanner.nextLine();
        System.out.println("Please input data about movie!");
        String movie_about = scanner.nextLine();
        System.out.println("Please input price");
        double price = scanner.nextDouble();
        System.out.println(cont.createMovie(new Movie(mov,movie_about,price)));
        forAdmin();
    }
    public void updateMovie(){
        System.out.println("Please input movie's id which you want to update");
        int id = scanner.nextInt();
        System.out.println(cont.UpdateMovie(id));
        forAdmin();
    }
    public void deleteMovie(){
        System.out.println("Please input movie's id which you want to delete");
        int id = scanner.nextInt();
        System.out.println(cont.DeleteMovie(id));
        forAdmin();
    }
    public void AllMovie(){
        System.out.println(cont.allMovie());
        forAdmin();
    }
    public  void logOut(){
        controller.LogOut();
        System.out.println("Successfully logged out");
        start();
    }
    public void forClient(){
        System.out.println("""
                What do you want to do?
                1. see all movie
                2. buy ticket
                3. return ticket
                4. log out from my account
                5. My items
                0. Exit""");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 -> allMovie();
            case 2 -> buyTicket();
            case 3 -> returnTicket();
            case 4 -> logOut();
            case 5 -> MyAccount();
            default -> exit(0);
        }
    }
    public  void allMovie(){
        System.out.println(cont.allMovie());
        forClient();
    }
    public void buyTicket(){
        System.out.println("Please input movie's id");
        int id = scanner.nextInt();
        System.out.println("Please input date (12/02/2023");
        String date = scanner.next();
        if(date.length()!=10) {
            System.out.println("Incorrect date");
            forClient();
        }
        else {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt((date.substring(3,5)));
            int year = Integer.parseInt(date.substring(6,9));
            DateExist(day,month,year);
        }
        System.out.println("""
Please input movie session
  10:30
  12:40
  14:50
  17:00
  19:10
""");
        String time = scanner.next();
        if(time.length() != 5) {
            System.out.println("Incorrect time");
            forClient();
        }
        else {
            int hour = Integer.parseInt(time.substring(0,2));
            int minute = Integer.parseInt(time.substring(3,5));
            TimeExist(hour,minute);
            ;
        }
        System.out.println(con.buyTicket(id));
        forClient();
    }
    public void returnTicket(){
        System.out.println(con.returnTicket());
        forClient();
    }
    public void MyAccount(){
        con.Myaccount();
        forClient();
    }
    public void SignIn(){
        System.out.println("Please input your username");
        String name = scanner.next();
        System.out.println("Please input your password");
        String pass = scanner.next();
        boolean exist = controller.SignIn(name,pass);
        if(exist){
            System.out.println("Successfully logged in!");
            String role = UserController.currentUser.getRole();
            if(role.equalsIgnoreCase("admin")){
                forAdmin();
            }
            else forClient();
        }
        else{
            System.out.println("There is no such account, try again");
            SignIn();
        }

    }
    public void getAllUsersMenu(){
        String response = controller.getAllUsers();
        System.out.println(response);
        start();
    }

    public void createUserMenu() throws Exception {
        String response = null;
        System.out.println("Please enter your name");
        String name = scanner.next();
        System.out.println("Please enter your password");
        String password = scanner.next();
        System.out.println("Please enter your role admin/client");
        String choice = scanner.next();
        if(choice.equalsIgnoreCase("admin")){
        response = controller.createUsers(name, password, choice);
        forAdmin();
        } else if (choice.equalsIgnoreCase("client")) {
            System.out.println("Please input your balance");
            double balance = scanner.nextDouble();
             response = controller.createUsers(name, password, choice, balance);
             forClient();
        }else {
            System.out.println("Not correct role, please try again");
            createUserMenu();
        }
        System.out.println(response);
    }
    public boolean TimeExist(int hour, int minute){
        return (hour <= 24 && hour >= 0) && (minute <= 59 && minute >= 0);
    }

    public boolean DateExist(int day, int month, int year){
        if(day<=0 || month <= 0 || year <= 0) return false;
        int feb = 28 ;
        if(year%4==0){
            if(!(year%100 == 0)){
                feb=29;
            } else if (year%400==0) {
                feb=29;
            }
        }

        switch (month){
            case 1:
                if(day<=31){
                    return true;
                }
            case 2:
                if (day<=feb){
                    return true;
                }
            case 3:
                if (day <= 31) return true;
            case 4:
                if (day <= 30) return true;

            case 5:
                if (day <= 31) return true;

            case 6:
                if (day <= 30) return true;
            case 7:
                if (day <= 31) return true;
            case 8:
                if (day <= 31) return true;
            case 9:
                if (day <= 30) return true;
            case 10:
                if (day <= 31) return true;
            case 11:
                if (day <= 30) return true;

            case 12:
                if (day <= 31) return true;
        }


        return false;
    }
}

