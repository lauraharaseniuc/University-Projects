//package com.example.lab10_gui;
//
//import com.example.lab10_gui.business.UserService;
//import com.example.lab10_gui.console_gui.Console;
//import com.example.lab10_gui.exceptions.RepositoryError;
//import com.example.lab10_gui.repositories.db_repositories.FriendshipDbRepository;
//import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;
//import com.example.lab10_gui.validators.UserValidator;
//
//public class Main {
//    public static void main(String[] args) {
//        UserValidator userValidator= new UserValidator();
//        try {
//            //UserFileRepository userFileRepository = new UserFileRepository("D:\\FACULTATE\\MAP\\LABORATOR_BD\\src\\main\\java\\social_network\\databases\\users.csv", "D:\\FACULTATE\\MAP\\LABORATOR_BD\\src\\main\\java\\social_network\\databases\\passwords.csv", "D:\\FACULTATE\\MAP\\LABORATOR_BD\\src\\main\\java\\social_network\\databases\\deleted_users.csv");
//            String url="jdbc:postgresql://localhost:5432/academic";
//            String username="postgres";
//            String password="postgres";
//            UserDbRepository userDbRepository= new UserDbRepository(url, username, password);
//            FriendshipDbRepository friendshipDbRepository = new FriendshipDbRepository(url, username, password);
//            try {
//                UserService userService = new UserService(userDbRepository, friendshipDbRepository, userValidator);
//                Console console = new Console(userService);
//                console.run();
//            } catch (RepositoryError re) {
//                System.out.println("An error occurred while synchronizing data from user file and friendship file.\n");
//            }
//        } finally {
//
//        }
//    }
//}
