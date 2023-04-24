package com.example.lab10_gui.repositories;


import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.utils.IntegerIdGenerator;

import java.io.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserFileRepository extends InMemoryRepository<Integer, User> {
    private final String userFilePath;
    private final String passwordFilePath;

    private final String deletedUserFilePath;
    private final IntegerIdGenerator idGenerator;

    public UserFileRepository(String userFilePath, String passwordFilePath, String deletedUserFilePath) {
        super();
        this.userFilePath = userFilePath;
        this.passwordFilePath = passwordFilePath;
        this.deletedUserFilePath=deletedUserFilePath;
        this.idGenerator = new IntegerIdGenerator();
        this.read_all_users();
    }

    /**
     * Extracts a user from two given arrays of Strings
     *
     * @param user_fields     String array that contains the id, username, last name and first name of a user
     * @param password_fields String array that contains the id and password of a user
     * @return the user built from the attributes extracted from the given arrays of Strings
     */
    private User extractUser(String[] user_fields, String[] password_fields) throws RuntimeException{
        if (user_fields.length<4 || password_fields.length<2) {
            throw new RuntimeException("Date corupte in fisier!\n");
        }
        Integer id;
        try {
        id = Integer.parseInt(user_fields[0]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Date corupte in fisier!\n");
        }
        String username = user_fields[1];
        String lastName = user_fields[2];
        String firstName = user_fields[3];
        String password = password_fields[1];
        return new User(id, username, lastName, firstName, password);
    }

    /**
     * Reads all the users from the file and stores them into the memory
     */
    private void read_all_users() throws RuntimeException{
        try {
            String splitBy = ",";
            BufferedReader br_user = new BufferedReader(new FileReader(this.userFilePath));
            BufferedReader br_password = new BufferedReader(new FileReader(this.passwordFilePath));

            String user_line = "";
            String password_line = "";

            int last_id = -1;

            user_line = br_user.readLine();
            password_line = br_password.readLine();
            while (user_line != null && password_line!=null) {
                String[] user_info = user_line.split(splitBy);
                String[] password_info = password_line.split(splitBy);

                User user = this.extractUser(user_info, password_info);
                this.add(user);

                last_id = user.getId();

                user_line = br_user.readLine();
                password_line = br_password.readLine();

                if ((user_line!=null && password_line==null) || (user_line==null && password_line!=null)) {
                    throw new RuntimeException("Eroare la sincronizarea datelor din fisierele "+this.userFilePath+" si "+this.passwordFilePath+"\n");
                }
            }
            this.idGenerator.setIdCount(last_id);
        } catch (IOException e) {
            throw new RuntimeException("Eroare la citirea datelor din fisier!\n");
        }
    }

    /**
     * Appends a new line to the end of the file containing a given user's information
     *
     * @param user a valid user whose info should be appended to the end of the file
     */
    private void append_to_file(User user) {
        try {
            BufferedWriter bw_user = new BufferedWriter(new FileWriter(this.userFilePath, true));
            PrintWriter pw_user = new PrintWriter(bw_user);
            pw_user.append(String.valueOf(user.getId()) + "," + user.getUsername() + "," + user.getLast_name() + "," + user.getFirst_name() + "\n");
            pw_user.close();

            BufferedWriter bw_password = new BufferedWriter(new FileWriter(this.passwordFilePath, true));
            PrintWriter pw_password = new PrintWriter(bw_password);
            pw_password.append(String.valueOf(user.getId()) + "," + user.getPassword() + "\n");
            pw_password.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stores all the users from the memory to the file
     */
    private void write_all_to_file() {
        try {
            BufferedWriter bw_user = new BufferedWriter(new FileWriter(this.userFilePath));
            PrintWriter pw_user = new PrintWriter(bw_user);

            BufferedWriter bw_password = new BufferedWriter(new FileWriter(this.passwordFilePath));
            PrintWriter pw_password = new PrintWriter(bw_password);

            for (Map.Entry<Integer, User> entry : this.getAll()) {
                User user = entry.getValue();
                pw_user.write(String.valueOf(user.getId()) + "," + user.getUsername() + "," + user.getLast_name() + "," + user.getFirst_name() + "\n");
                pw_password.write(String.valueOf(user.getId()) + "," + user.getPassword() + "\n");

            }
            pw_user.close();
            pw_password.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a user identified by a given username exists in the repository
     *
     * @param username a String that identifies a potential user
     * @return true , if a user with the given username exists in the repository
     * @return false, if no user with the given username exists in the repository
     */
    private boolean user_exists(String username) {
        /*for (User user: this.getAll()) {
            if (!user.isDeleted() &&  Objects.equals(user.getUsername(), username)) {
                return true;
            }
        }*/
        for (Map.Entry<Integer, User> entry : this.getAll()) {
            if (Objects.equals(entry.getValue().getUsername(), username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a user's id in the repository, based on a given username
     *
     * @param username String that uniquely identifies a user
     * @return the user's id in the repository's internal representation , if a user with the given username exists
     * -1, if there is no user with the given username
     */
    private Integer get_user_id(String username) {
        /*Iterator<User> iterator= this.getAll().iterator();
        int index=0;
        while(iterator.hasNext()) {
            User user=iterator.next();
            if (!user.isDeleted() && Objects.equals(user.getUsername(), username)) {
                return index;
            }
            index++;
        }
        return -1;*/
        for (Map.Entry<Integer, User> entry : this.entities.entrySet()) {
            if (Objects.equals(entry.getValue().getUsername(), username)) {
                return entry.getKey();
            }
        }
        return null;
    }


    /**
     * Verifies if two users identified by their usernames can form a friendship (the usernames belong to real users in the repository)
     *
     * @param sender_username   String that uniquely identifies a user
     * @param receiver_username String that uniquely identifies a user
     * @throws RepositoryError , if at least one of the given username does not belong to any user in the repository or
     *                         if they identify the same user
     */
    private void validate_friendship(String sender_username, String receiver_username) throws RepositoryError {
        if (Objects.equals(sender_username, receiver_username)) {
            throw new RepositoryError("A friendship can only exist between users with different usernames!\n");
        }
        if (!this.user_exists(sender_username)) {
            throw new RepositoryError("No user with the given username: " + sender_username + " was found!\n");
        }
        if (!this.user_exists(receiver_username)) {
            throw new RepositoryError("No user with the given username: " + receiver_username + " was found!\n");
        }
    }

    /**
     * Adds a new user to the repository
     *
     * @param username   indicates a username for the user to be added (String)
     * @param last_name  indicates a last name for the user to be added (String)
     * @param first_name indicates a first name for the user to be added (String)
     * @param password   indicates the user's password (String)
     * @throws RepositoryError if a user with the same username already exists in the repository
     */
    public void add_user(String username, String last_name, String first_name, String password) throws RepositoryError {
        Integer id = this.idGenerator.generateId();
        User user = new User(id, username, last_name, first_name, password);

        if (this.user_exists(username)) {
            throw new RepositoryError("A user with the given username already exists!\n");
        }
        this.add(user);
        this.append_to_file(user);
    }

    /**
     * Removes a user identified by its id from the repository. The user's friendships are also removed.
     *
     * @param id indicates a user's id in the repository (Integer)
     * @throws RepositoryError if no user with the given id was found in the repository
     */
    public void remove_user(Integer id) throws RepositoryError {
        if (this.delete(id) == null) {
            throw new RepositoryError("No user with the given username was found!\n");
        } else {
            for (Map.Entry<Integer, User> entry: this.getAll()) {
                entry.getValue().getFriends().removeIf(user -> Objects.equals(user.getId(),id));
            }
            //this.friendships.removeIf(friendship -> (Objects.equals(friendship.getUser1().getId(), id)));
            //this.friendships.removeIf(friendship -> (Objects.equals(friendship.getUser2().getId(), id)));
            this.write_all_to_file();
        }
    }


    public void add_friend_on_id(Integer userId1, Integer userId2) throws RepositoryError {
        User user1 = this.getEntity(userId1);
        User user2 = this.getEntity(userId2);
        if (user1 == null) {
            throw new RepositoryError("No user with the given id(" + userId1 + ") was found!\n");
        }
        if (user2 == null) {
            throw new RepositoryError("No user with the given id(" + userId2 + ") was found!\n");
        }
        this.getEntity(userId1).getFriends().add(this.getEntity(userId2));
    }

    /**
     * Adds a new friendship between two users identified by their usernames
     *
     * @param sender_username   String that uniquely identifies a user
     * @param receiver_username String that uniquely identifies a user
     * @throws RepositoryError if at least one of the given usernames does not belong to any user in the repository
     *                         or if they identify the same user
     */
    public Map.Entry<Integer,Integer> get_friendship_id_as_pair(String sender_username, String receiver_username) throws RepositoryError {
        this.validate_friendship(sender_username, receiver_username);

        Integer sender_id = this.get_user_id(sender_username);
        Integer receiver_id = this.get_user_id(receiver_username);

        return new AbstractMap.SimpleEntry<Integer,Integer>(sender_id, receiver_id);
    }

    public void add_friend_in_friends_list(String sender_username, String receiver_username) {
        Integer sender_id = this.get_user_id(sender_username);
        Integer receiver_id = this.get_user_id(receiver_username);

        User sender = this.entities.get(sender_id);
        User receiver = this.entities.get(receiver_id);

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

    }

    /**
     * Removes an existing friendship between two users identified by their usernames
     *
     * @param sender_username   String that uniquely identifies a user
     * @param receiver_username String that uniquely identifies a user
     * @throws RepositoryError if at least one of the given usernames does not belong to any user in the repository
     *                         or if they identify the same user
     */
    public Map.Entry<Integer,Integer> get_removed_friendship_as_id_pair(String sender_username, String receiver_username) throws RepositoryError {
        this.validate_friendship(sender_username, receiver_username);

        Integer sender_id = this.get_user_id(sender_username);
        Integer receiver_id = this.get_user_id(receiver_username);

        User sender = this.entities.get(sender_id);
        User receiver = this.entities.get(receiver_id);

        if (!sender.getFriends().removeIf(user -> (Objects.equals(user.getUsername(), receiver_username)))) {
            throw new RepositoryError("Can't remove friend since friendship doesn't exist!\n");
        }
        receiver.getFriends().removeIf(user -> (Objects.equals(user.getUsername(), sender_username)));

        return new AbstractMap.SimpleEntry<Integer,Integer>(sender_id,receiver_id);
        //this.friendships.removeIf(friendship -> ((Objects.equals(friendship.getUser1(), sender) && Objects.equals(friendship.getUser2(), receiver)) || (Objects.equals(friendship.getUser1(), receiver) && Objects.equals(friendship.getUser2(), sender))));
    }

    /**
     * Returns the number of communities formed by users in the repository and their friends
     *
     * @return the number of communities formed by the users
     */
//    public int number_of_communities() {
//        CommunitiesGraph communitiesGraph = new CommunitiesGraph();
//        return communitiesGraph.get_number_of_communities(this.getAll());
//    }

    /**
     * Returns a list containing the users that are part of the most sociable community
     *
     * @return list with the usernames belonging to the users that are a part of the most sociable community
     */
//    public List<String> most_sociable_community() {
//        CommunitiesGraph communitiesGraph = new CommunitiesGraph();
//        return communitiesGraph.get_longest_path(this.getAll());
//    }

    public void log_in_user(String username, String password) throws RepositoryError {
        Integer userId = this.get_user_id(username);
        if (userId == null) {
            throw new RepositoryError("No user with the given username(" + username + ") exists!\n");
        }
        if (!Objects.equals(this.getEntity(userId).getPassword(), password)) {
            throw new RepositoryError("Incorrect password!\n");
        }
    }
}

