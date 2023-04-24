package com.example.lab10_gui.validators;


import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.ValidationError;

public class UserValidator implements Validator<User> {

    /**
     * Verifies if a user identified by 4 given attributes is valid (can be added to the user list)
     * @param username should not be empty
     * @param last_name should not be empty
     * @param first_name should not be empty
     * @param password should not be empty or have the length less than 5 characters
     * @throws ValidationError
     */
    public void validate_user_for_add(String username, String last_name, String first_name, String password) throws ValidationError {
        String message="";
        if (username.isEmpty()) {
            message=message.concat("Invalid username!\n");
        }
        if (last_name.isEmpty()) {
            message=message.concat("Invalid last name!\n");
        }
        if (first_name.isEmpty()) {
            message=message.concat("Invalid first name!\n");
        }
        if (password.isEmpty()) {
            message=message.concat("Invalid password!\n");
        }
        else if (password.length()<=4) {
            message=message.concat("Password is too short!\n");
        }
        if (message.length()>0) {
            throw new ValidationError(message);
        }
    }

    /**
     * Verifies if a given id is valid
     * @param id should be a value greater than 0
     * @throws ValidationError if the given id is a number less than or equal to 0
     */
    public void validate_id(Integer id) throws ValidationError {
        String message="";
        if (id<=0) {
            message=message.concat("Invalid id!\n");
        }
        if (message.length()>0) {
            throw new ValidationError(message);
        }
    }

    /**
     * Verifies if a friend request identified by two usernames(sender username and receiver username) is valid
     * @param sender_username should not be empty
     * @param receiver_username should not be empty
     * @throws ValidationError if at least one of the given usernames is empty
     */
    public void validate_friend_request(String sender_username, String receiver_username) throws ValidationError {
        String message="";
        if (sender_username.isEmpty()) {
            message=message.concat("Invalid sender username!\n");
        }
        if (receiver_username.isEmpty()) {
            message=message.concat("Invalid receiver username!\n");
        }
        if (message.length()>0) {
            throw new ValidationError(message);
        }
    }

    public void validate_user_for_log_in(String username, String password) throws ValidationError {
        String message="";
        if (username.isEmpty()) {
            message=message.concat("Invalid username!");
        }
        if (password.isEmpty()) {
            message=message.concat("Invalid password!");
        }
        if (message.length()>0) {
            throw new ValidationError(message);
        }
    }

    /**
     * Verifies if a given user is valid
     * @param user should not be null
     * @throws ValidationError if the user's username, last name, first name, or password is empty or the password's length is less than 5
     */
    @Override
    public void validate(User user) throws ValidationError {
        String message="";
        if (user.getUsername().isEmpty()) {
            message=message.concat("Invalid username!\n");
        }
        if (user.getLast_name().isEmpty()) {
            message=message.concat("Invalid last name!\n");
        }
        if (user.getFirst_name().isEmpty()) {
            message=message.concat("Invalid first name!\n");
        }
        if (user.getPassword().isEmpty()) {
            message=message.concat("Invalid password!\n");
        }
        else if (user.getPassword().length()<=4) {
            message=message.concat("Password is too short!\n");
        }
        if (message.length()>0) {
            throw new ValidationError(message);
        }
    }
}
