package com.example.lab10_gui.repositories.db_repositories;


import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.graph_algorithms.CommunitiesGraph;
import com.example.lab10_gui.repositories.CrudRepository;

import javax.xml.transform.Result;
import java.sql.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserDbRepository implements CrudRepository<Integer, User> {
    private final String url;
    private final String username;
    private final String password;

    public UserDbRepository(String url, String username, String password) {
        this.url=url;
        this.username=username;
        this.password=password;
    }


    @Override
    public User add(User entity) {
        return null;
    }

    @Override
    public User delete(Integer integer) {
        return null;
    }

    public void add_user (String username, String lastName, String firstName, String password) throws SQLException{
        String sqlCommand="INSERT INTO USERS (username, last_name, first_name) values (?,?,?)";
        String sqlCommandInsertPassword="INSERT INTO PASSWORDS (password) values (?)";
        try (Connection connection= DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement preparedStatement=connection.prepareStatement(sqlCommand);
             PreparedStatement preparedStatementInsertPassword=connection.prepareStatement(sqlCommandInsertPassword)) {
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,firstName);
            preparedStatementInsertPassword.setString(1,password);

            preparedStatement.executeUpdate();
            preparedStatementInsertPassword.executeUpdate();

        }
    }

    public void remove_user(Integer id) throws SQLException, RepositoryError {
        String sqlCommandDeleteUser="DELETE FROM USERS WHERE id=?";
        String sqlCommandDeleteFriendship="DELETE FROM FRIENDSHIPS WHERE (id_user1 = ? or id_user2=?)";
        String sqlCommandDeletePassword="DELETE FROM PASSWORDS WHERE id=?";
        try (Connection connection=DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement preparedStatementDeleteFromUsers=connection.prepareStatement(sqlCommandDeleteUser);
            PreparedStatement preparedStatementDeleteFromFriendships=connection.prepareStatement(sqlCommandDeleteFriendship);
            PreparedStatement statementDeletePassword=connection.prepareStatement(sqlCommandDeletePassword)) {

            preparedStatementDeleteFromFriendships.setInt(1,id);
            preparedStatementDeleteFromFriendships.setInt(2,id);
            preparedStatementDeleteFromFriendships.executeUpdate();

            preparedStatementDeleteFromFriendships.setInt(1,id);
            preparedStatementDeleteFromFriendships.setInt(2,id);
            preparedStatementDeleteFromFriendships.executeUpdate();

            statementDeletePassword.setInt(1,id);
            statementDeletePassword.executeUpdate();

            preparedStatementDeleteFromUsers.setInt(1,id);
            int rows_deleted=preparedStatementDeleteFromUsers.executeUpdate();
            if (rows_deleted==0) {
                throw new RepositoryError("No user with the given id exists!\n");
            }
        }
    }

    public User get_user_on_id (Integer id) {
        String sqlCommand="SELECT * FROM USERS WHERE id=?";
        try (
        Connection connection=DriverManager.getConnection(this.url, this.username,this.password);
        PreparedStatement statement=connection.prepareStatement(sqlCommand)) {

            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String last_name = resultSet.getString("last_name");
                String first_name = resultSet.getString("first_name");
                String password = resultSet.getString("password");
                return new User(userId, username, last_name, first_name, password);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int number_of_communities() {
        CommunitiesGraph communitiesGraph = new CommunitiesGraph();

        Set<AbstractMap.SimpleEntry<Integer,User>> users= new HashSet<>();
        this.getAllEntities().forEach(x->users.add(new AbstractMap.SimpleEntry<>(x.getId(),x)));

        return communitiesGraph.get_number_of_communities(users);
    }

    public List<String> most_sociable_community() {
        CommunitiesGraph communitiesGraph = new CommunitiesGraph();
        Set<AbstractMap.SimpleEntry<Integer,User>> users= new HashSet<>();
        this.getAllEntities().forEach(x->users.add(new AbstractMap.SimpleEntry<>(x.getId(),x)));

        return communitiesGraph.get_longest_path(users);
    }
    @Override
    public Iterable<User> getAllEntities() {
        Set<User> users = new HashSet<>();
        try (Connection connection= DriverManager.getConnection(this.url,this.username, this.password);
             PreparedStatement statement=connection.prepareStatement("SELECT * FROM USERS");
             ResultSet resultSet=statement.executeQuery()
             ) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String password = resultSet.getString("password");

                User user = new User(id, username, lastName, firstName, password);
                users.add(user);
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return users;
    }

    public User log_in_user(String username, String password) throws RepositoryError, SQLException {
        String sqlGetUser="SELECT * FROM USERS WHERE username=? and password=?";
        Connection connection= DriverManager.getConnection(this.url, this.username, this.password);
        PreparedStatement statement=connection.prepareStatement(sqlGetUser);

        statement.setString(1,username);
        statement.setString(2,password);
        ResultSet user=statement.executeQuery();

        if (user.next()) {
            return new User(user.getInt("id"), user.getString("username"), user.getString("last_name"), user.getString("first_name"),user.getString("password"));
        }
        else {
            throw new RepositoryError("No user with the given username and password was found!\n");
        }
    }

    public Optional<User> getUserOnUsername (String username) {
        AtomicReference<User> foundUser = new AtomicReference<>();
        this.getAllEntities().forEach(user -> {
            if (Objects.equals(user.getUsername(), username)) {
                foundUser.set(user);
            }
        });
        return Optional.ofNullable(foundUser.get());
    }
}
