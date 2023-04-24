//package com.example.lab10_gui.console_gui;
//
//
//import com.example.lab10_gui.business.UserService;
//import com.example.lab10_gui.exceptions.RepositoryError;
//import com.example.lab10_gui.exceptions.ValidationError;
//
//import java.sql.SQLException;
//import java.sql.SQLOutput;
//import java.util.Scanner;
//
//public class Console {
//    private final UserService userService;
//    private final Scanner scanner=new Scanner(System.in);
//
//    public Console(UserService userService)  {
//        this.userService=userService;
//    }
//
//
//    /**
//     * Displays the application's menu
//     */
//    private void display_menu() {
//        System.out.println("----------------------------------------MENU----------------------------------------");
//        System.out.println("0 Exit the app.");
//        System.out.println("1 Add a user.");
//        System.out.println("2 Delete a user.");
//        System.out.println("3 Display the number of communities.");
//        System.out.println("4 Display the most sociable community.");
//        System.out.println("5 Display app menu.");
//        System.out.println("6 Display all users.");
//        System.out.println("8 Log in as user.");
//        System.out.println("----------------------------------------MENU----------------------------------------");
//    }
//
//    /**
//     * Requires user input in order to add a new user
//     */
//    private void add_new_user() {
//        System.out.println("Adding new user...");
//
//        System.out.print("USERNAME: ");
//        this.scanner.nextLine();
//        String username=this.scanner.nextLine();
//
//        System.out.print("LAST NAME: ");
//        String last_name=this.scanner.nextLine();
//
//        System.out.print("FIRST NAME: ");
//        String first_name=this.scanner.nextLine();
//
//        System.out.print("PASSWORD: ");
//        String password=this.scanner.nextLine();
//
//        try {
//            this.userService.add_user(username, last_name, first_name, password);
//            System.out.println("User added!");
//        }
//        catch (ValidationError ve) {
//            System.out.println("ERROR occurred while adding the user..." + ve.getMessage());
//        } catch (RepositoryError e) {
//            System.out.println("RepoError: "+e.getMessage());
//        } catch (SQLException e) {
//            System.out.println("Database Error: "+e.getMessage());
//        }
//    }
////
////    /**
////     * Requires user input in order to add a new friendship
////     */
////
//    /**
//     * Requires user input in order to remove a user
//     */
//    private void remove_user() {
//        System.out.print("Type the id of the user to be removed: ");
//        this.scanner.nextLine();
//
//        Integer id=this.scanner.nextInt();
//        try {
//            this.userService.remove_user(id);
//            System.out.println("User successfully removed!\n");
//        }
//        catch(ValidationError | RepositoryError ve) {
//            System.out.println("ERROR occurred while adding the user..." + ve.getMessage());
//        } catch (SQLException e) {
//            System.out.println("DatabaseError: "+e.getMessage());
//        }
//    }
////
////    /**
////     * Requires user input in order to remove a friendship
////     */
////
//    /**
//     * Displays the number of communities in the network
//     */
//    private void get_number_of_communities() {
//        System.out.println("Number of communities: "+this.userService.get_number_of_communities());
//    }
//
//    private void get_most_sociable_community() {
//        System.out.println("THE MOST SOCIABLE COMMUNITY IS FORMED BY: "+ this.userService.get_most_sociable_community());
//    }
//
//    private void user_log_in() {
//        System.out.println("LOG IN: ");
//        System.out.println("USERNAME: ");
//        this.scanner.nextLine();
//        String username=this.scanner.nextLine();
//        System.out.println("PASSWORD: ");
//        String password=this.scanner.nextLine();
//        try {
//            this.userService.log_in_user(username, password);
//            ConsoleUserMode consoleUserMode= new ConsoleUserMode(this.userService, username);
//            consoleUserMode.run_in_user_mode();
//        } catch (RepositoryError | ValidationError re) {
//            System.out.println(re.getMessage());
//        } catch (SQLException e) {
//            System.out.println("Invalid combination of username and password!");
//        }
//    }
//
//    public void display_all_users() {
//        this.userService.get_all_users().forEach(System.out::println);
//    }
//    public void run() {
//        this.display_menu();
//
//        int command;
//        boolean running=true;
//        while(running) {
//            System.out.print(">>>> ");
//            command=this.scanner.nextInt();
//            switch (command) {
//                case 0 -> {
//                    System.out.println("exit app...");
//                    running = false;
//                }
//                case 1 -> this.add_new_user();
//                case 2 -> this.remove_user();
//                case 3 -> this.get_number_of_communities();
//                case 4 -> this.get_most_sociable_community();
//                case 5 -> this.display_menu();
//                case 6-> this.display_all_users();
//                case 8-> this.user_log_in();
//                default -> System.out.println("not a valid command...");
//            }
//        }
//    }
//
//}