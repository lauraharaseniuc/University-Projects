package com.example.lab10_gui.business;

import com.example.lab10_gui.entities.Friendship;
import com.example.lab10_gui.entities.Message;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.exceptions.ValidationError;
import com.example.lab10_gui.observer_pattern.ChangeEventType;
import com.example.lab10_gui.observer_pattern.Observable;
import com.example.lab10_gui.observer_pattern.Observer;
import com.example.lab10_gui.observer_pattern.UserEntityChangeEvent;
import com.example.lab10_gui.repositories.db_repositories.FriendshipDbRepository;
import com.example.lab10_gui.repositories.db_repositories.MessageDbRepository;
import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;
import com.example.lab10_gui.validators.UserValidator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements Observable<UserEntityChangeEvent> {
    private final UserDbRepository userDbRepository;
    private final FriendshipDbRepository friendshipDbRepository;
    private final MessageService messageService;
    private final UserValidator userValidator;
    private final List<Observer<UserEntityChangeEvent>> observers=new ArrayList<>();

    public UserService(UserDbRepository userDbRepository, FriendshipDbRepository friendshipDbRepository, MessageService messageService, UserValidator userValidator) throws RepositoryError {
        this.userDbRepository = userDbRepository;
        this.friendshipDbRepository =friendshipDbRepository;
        this.messageService=messageService;
        this.userValidator = userValidator;
        //this.bind_users_and_friendships();
    }

    public FriendshipDbRepository getFriendshipDbRepository() {
        return friendshipDbRepository;
    }

    public UserDbRepository getUserDbRepository() {
        return userDbRepository;
    }


    /**
     * Adds a new user based on a given username, last name, first name and password
     * @param username String that should uniquely identify a user
     * @param last_name String that indicates the user's last name
     * @param first_name String that indicates the user's first name
     * @param password String that indicates the user's password
     * @throws ValidationError if no valid user can be created with the given information
     * @throws RepositoryError if a user with the given information already exists
     */
    public void add_user(String username, String last_name, String first_name, String password) throws ValidationError, RepositoryError, SQLException {
        this.userValidator.validate_user_for_add(username, last_name, first_name, password);
        this.userDbRepository.add_user(username, last_name, first_name, password);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.ADD));
    }

    /**
     * Removes a user based on its id
     * @param id Integer that uniquely identifies a user
     * @throws ValidationError if the given id is not valid (if it is a negative number)
     * @throws RepositoryError if no user with the given id exists
     */
    public void remove_user(Integer id) throws ValidationError, RepositoryError, SQLException {
        this.userValidator.validate_id(id);
        this.userDbRepository.remove_user(id);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.DELETE));
        //this.friendshipFileRepository.remove_all_friendships_belonging_to_a_user(id);
    }

    /**
     * Adds a new friendship between two users identified by their usernames
     * @param sender_username String that uniquely identifies a user
     * @param receiver_username String that uniquely identifies a user
     * @throws ValidationError if the given usernames are not valid (at least one of them is empty)
     * @throws RepositoryError if a friendship between the users identified by their usernames already exists
     */
    public void send_friend_request(Integer senderId, Integer receiverId) throws ValidationError, RepositoryError, SQLException {
        //this.userValidator.validate_friend_request(sender_username, receiver_username);
        this.friendshipDbRepository.send_friend_request(senderId,receiverId);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.ADD_FRIEND));
    }

    public void remove_friend_request (Integer senderId, Integer receiverId) throws SQLException {
        this.friendshipDbRepository.remove_friend_request(senderId,receiverId);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.REMOVE_FRIEND_REQUEST));
    }

    /**
     * Removes a friendship between two users identified by their usernames
     * @param remover_username String that uniquely identifies a user
     * @param removed_username String that uniquely identifies a user
     * @throws ValidationError if the given usernames are not valid (at least one of them is empty)
     * @throws RepositoryError if a friendship between the users identified by their usernames does not exist (and thus can not be removed)
     */
    public void remove_friendship(Integer senderId, Integer receiverId) throws ValidationError, RepositoryError, SQLException {
        //this.userValidator.validate_friend_request(removed_username, removed_username);
        this.friendshipDbRepository.remove_friend(senderId,receiverId);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.REMOVE_FRIEND));
    }

    public void accept_friend_request(Integer senderId, Integer receiverId) throws SQLException {
        this.friendshipDbRepository.accept_friend_request(senderId, receiverId);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.ACCEPT_FRIEND_REQUEST));
    }

    /**
     * Returns the number of communities formed within the network
     * @return the number of communities formed by the users and their friendships
     */
    public int get_number_of_communities() {
        return this.userDbRepository.number_of_communities();
    }

    public List<String> get_most_sociable_community() {
        return this.userDbRepository.most_sociable_community();
    }

    public List<Friendship> get_friend_requests_belonging_to_a_user(User user) {
        return this.friendshipDbRepository.get_all_friend_requests_belonging_to_a_user(user);
    }
    public Iterable<User> get_all_users() {
        return this.userDbRepository.getAllEntities();
    }

    public List<Message> get_all_messages_between_two_users(User sender, User receiver) {
        if (receiver==null) {
            System.out.println("2----------------------------------------------------");
        }
        return this.messageService.get_all_messages_between_two_users(sender,receiver);
    }

    public User log_in_user(String username, String password) throws ValidationError, RepositoryError, SQLException {
        this.userValidator.validate_user_for_log_in(username,password);
        return this.userDbRepository.log_in_user(username,password);
    }

    public List<Friendship> get_all_friends_belonging_to_a_user (User user) {
        return this.friendshipDbRepository.get_all_friendships_belonging_to_a_user(user);
    }

    public void send_message (User sender, User receiver, String message) {
        this.messageService.send_message(sender,receiver,message);
        this.notifyObserver(new UserEntityChangeEvent(ChangeEventType.SEND_MESSAGE));
    }

    public Optional<User> getUserOnUsername(String username) {
        return this.userDbRepository.getUserOnUsername(username);
    }

    public MessageService getMessageService() {
        return messageService;
    }

    @Override
    public void addObserver(Observer<UserEntityChangeEvent> e) {
        this.observers.add(e);
    }

    @Override
    public void removeObserver(Observer<UserEntityChangeEvent> e) {
        this.observers.remove(e);
    }

    @Override
    public void notifyObserver(UserEntityChangeEvent userEntityChangeEvent) {
        this.observers.forEach(observer-> observer.update(userEntityChangeEvent));
    }
}
