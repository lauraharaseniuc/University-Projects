/*package com.example.lab10_gui.console_gui;


import com.example.lab10_gui.business.UserService;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.exceptions.ValidationError;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleUserMode {
    private final UserService userService;
    private final Scanner scanner=new Scanner(System.in);

    private final String username;

    public ConsoleUserMode(UserService userService, String username) {
        this.userService = userService;
        this.username=username;
    }

    private void display_user_mode_menu() {
        System.out.println("----------------------------------------MENU----------------------------------------");
        System.out.println("0 Log out.");
        System.out.println("1 Add a friend.");
        System.out.println("2 Remove a friend.");
        System.out.println("----------------------------------------MENU----------------------------------------");
    }

    private void add_friend() {
        System.out.print("Send friend request to: ");
        this.scanner.nextLine();
        String receiver_username = this.scanner.nextLine();
        try {
            this.userService.add_new_friend(username, receiver_username);
            System.out.println("You're friends now!\n");
        }
        catch (ValidationError ve) {
            System.out.println("Validation Error occurred while processing the friend request: "+ve.getMessage());
        }
        catch (RepositoryError re) {
            System.out.println("Repository Error occurred while processing the friend request: "+re.getMessage());
        } catch (SQLException e) {
            System.out.println("DatabaseError: "+e.getMessage());
        }
    }

    private void remove_friend() {
        System.out.print("Which friend should be removed? ");
        this.scanner.nextLine();
        String removed_username=this.scanner.nextLine();

        try {
            this.userService.remove_friend(this.username, removed_username);
            System.out.println("You are no longer friends!");
        }
        catch (ValidationError ve) {
            System.out.println("Validation Error: "+ve.getMessage());
        }
        catch (RepositoryError re) {
            System.out.println("Repository Error: "+re.getMessage());
        } catch (SQLException e) {
            System.out.println("DatabaseError: "+e.getMessage());
        }
    }

    public void run_in_user_mode() {
        this.display_user_mode_menu();

        int command;
        boolean running=true;
        while(running) {
            command=this.scanner.nextInt();
            switch(command) {
                case 0 -> {
                    System.out.println("Logging out...");
                    running=false;
                }
                case 1-> this.add_friend();
                case 2-> this.remove_friend();
                default -> System.out.println("Not a valid command...");
            }
        }
    }
}
*/