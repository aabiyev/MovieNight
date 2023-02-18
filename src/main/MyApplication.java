package main;
import java.util.Objects;
import java.util.Scanner;

import Exeptions.IncorrectPassword;
import controller.AdminController;
import controller.ClientController;
import controller.UserController;
import entities.Movie;

import static java.lang.System.exit;
public class MyApplication {
    private final UserController controller;
    private final AdminController adminController;
    private final ClientController clientController;
    private final Scanner scanner;
    public MyApplication(UserController controller, AdminController adminController, ClientController clientController){
        this.controller = controller;
        this.adminController = adminController;
        this.clientController = clientController;
        scanner = new Scanner(System.in);
    }

    public void start(){
            System.out.println("""
        Welcome to the Movie night
        select option:
        1. Sign up
        2. Sign in
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
    public void getAllUsersMenu() throws Exception {
        String response = controller.getAllUsers();
        if(response == null){
            System.out.println("There is no accounts, create new ");
            createUserMenu();
        }
        else {
            System.out.println(response);
            start();
        }
    }

    public void createUserMenu() throws Exception {
        String response = null;
        System.out.println("Please enter your name");
        String name = scanner.next();
        System.out.println("Please enter your password");
        String password = scanner.next();
        isRightPassword(password);
        System.out.println("Please enter your role admin/client");
        String choice = scanner.next();
        if(choice.equalsIgnoreCase("admin")){
            response = controller.createUsers(name, password, choice);
            forAdmin();
        } else if (choice.equalsIgnoreCase("client")) {
            System.out.println("Please input your balance");
            double balance = scanner.nextDouble();
            System.out.println("Please input your age");
            int age = scanner.nextInt();
            response = controller.createUsers(name, password, choice, balance,age);
            forClient();
        }else {
            System.out.println("Not correct role, please try again");
            createUserMenu();
        }
        System.out.println(response);
    }
    public void forAdmin()  {
        System.out.println("""
                What do you want to do?
                1. Create movie
                2. Update movie
                3. Delete movie
                4. log out from my account
                5. All movie
                6. All users
                7. Ban users
                0. Exit
                Enter option(1-7)""");
            try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> createMovie();
                case 2 -> updateMovie();
                case 3 -> deleteMovie();
                case 4 -> logOut();
                case 5 -> AllMovie();
                case 6 -> AllUsers();
                case 7 -> DeleteUser();
                default -> exit(0);
            }

        }catch (Exception e) {
                System.out.println((e.getMessage()));
                scanner.nextLine();
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
        System.out.println("Please input genre");
        String genre = scanner.nextLine();
        System.out.println("Please input rating");
        int rating = scanner.nextInt();
        System.out.println("Please input price");
        double price = scanner.nextDouble();
        System.out.println("Please input price for children");
        double priceForChildren = scanner.nextDouble();
        System.out.println(adminController.createMovie(new Movie(mov,movie_about,price,priceForChildren, rating, genre)));
        forAdmin();
    }
    public void updateMovie(){
        System.out.println("Please input movie's id which you want to update");
        int id = scanner.nextInt();
        System.out.println(adminController.UpdateMovie(id));
        forAdmin();
    }
    public void deleteMovie(){
        System.out.println("Please input movie's id which you want to delete");
        int id = scanner.nextInt();
        System.out.println(adminController.DeleteMovie(id));
        forAdmin();
    }
    public void AllMovie(){
        System.out.println(adminController.allMovie());
        forAdmin();
    }
    public void AllUsers(){
        String response = controller.getAllUsers();
        System.out.println(Objects.requireNonNullElse(response, "There is no accounts "));
        forAdmin();
    }
    public  void logOut(){
        controller.LogOut();
        System.out.println("Successfully logged out");
        start();
    }
    public void DeleteUser(){
        System.out.println("Please input users id");
        int id = scanner.nextInt();
        adminController.DeleteUsers(id);
        forAdmin();
    }
    public void forClient(){
        System.out.println("""
                What do you want to do?
                1. see all movie
                2. buy ticket
                3. return ticket
                4. log out from my account
                5. My items
                6. Delete my account
                0. Exit
                Enter option(1-6)""");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 -> allMovie();
            case 2 -> buyTicket();
            case 3 -> returnTicket();
            case 4 -> logOut();
            case 5 -> MyAccount();
            case 6 -> {
                adminController.DeleteUsers(UserController.currentUser.getId());
                if(UserController.currentUser == null){
                    System.out.println("User successfully deleted");
                }
            }
            default -> exit(0);
        }
    }
    public  void allMovie(){
        if(adminController.allMovie().isEmpty()){
            System.out.println("There is no movie, sorry for this");
        }
        else {
            System.out.println(adminController.allMovie().toString());
        }
        forClient();
    }
    public void buyTicket(){
        System.out.println("Please input movie's id");
        int id = scanner.nextInt();
        System.out.println("Please input date (29/02/2024)");
        String date = scanner.next();
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt((date.substring(3,5)));
        int year = Integer.parseInt(date.substring(6,9));
        if(date.length()!=10) {
            System.out.println("Incorrect date");
            forClient();
        }
        else {
            if(!dateExist(day,month,year)){
                System.out.println("Incorrect date");
                forClient();
            }
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
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(3,5));
        if(time.length() != 5) {
            System.out.println("Incorrect time");
            forClient();
        }
        else {
            if(!TimeExist(hour,minute)){
                System.out.println("Incorrect time");
                forClient();
            }
            ;
        }
       if(dateExist(day,month,year) && TimeExist(hour,minute)) {
            System.out.println(clientController.buyTicket(id));
        }
        forClient();
    }
    public void returnTicket(){
        System.out.println(clientController.returnTicket());
        System.out.println("**********************");
        forClient();
    }
    public void MyAccount(){
        clientController.Myaccount();
        System.out.println("********************");
        forClient();
    }
    public boolean TimeExist(int hour, int minute){
        return (hour <= 24 && hour >= 0) && (minute <= 59 && minute >= 0);
    }

    public boolean dateExist(int day, int month, int year){
        if(day<=0 || month <= 0 || year <= 0) return false;
        int feb = 28 ;
        if(year%4==0){
            if(year%100 != 0){
                feb=29;
            } else if (year%400==0) {
                feb=29;
            }
        }
        return switch (month) {
            case 2 -> day < feb;
            case 1, 3, 5, 7, 8, 10, 12 -> day <= 31;
            case 4, 6, 9, 11 -> day <= 30;
            default -> false;
        };
    }
    public void isRightPassword(String password) throws IncorrectPassword {
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
            throw new IncorrectPassword("Your password should contain at least 8 digits");
        }
        if(n != cnt){
            throw new IncorrectPassword("Your password should contain only numbers and character");
        }
    }
}

