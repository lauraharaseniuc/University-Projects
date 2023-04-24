package com.example.lab10_gui.repositories.db_repositories;

import com.example.lab10_gui.entities.Friendship;
import com.example.lab10_gui.entities.FriendshipStatus;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.keys.TwoDIntegerKey;
import com.example.lab10_gui.repositories.CrudRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendshipDbRepository implements CrudRepository<TwoDIntegerKey, Friendship> {
    private final String url;
    private final String username;
    private final String password;

    public FriendshipDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Friendship add(Friendship entity) {
        return null;
    }

    @Override
    public Friendship delete(TwoDIntegerKey twoDIntegerKey) {
        return null;
    }

    @Override
    public Iterable<Friendship> getAllEntities() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM FRIENDSHIPS");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id_user1");
                Integer id2 = resultSet.getInt("id_user2");
                LocalDate friends_from = resultSet.getDate("friends_from").toLocalDate();
                FriendshipStatus friendshipStatus = FriendshipStatus.valueOf(resultSet.getString("status"));

                Friendship friendship = new Friendship(id1, id2, friends_from, friendshipStatus);
                friendships.add(friendship);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    public List<Friendship> get_all_friendships_belonging_to_a_user(User user) {
        List<Friendship> friendships = new ArrayList<>();
        String acceptedStatus = FriendshipStatus.ACCEPTED.toString();
        try {
            String sqlCommand = "SELECT * FROM FRIENDSHIPS WHERE id_user1=? and status=?";
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sqlCommand);

            statement.setInt(1, user.getId());
            statement.setString(2, acceptedStatus);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
//            Integer id= resultSet.getInt("id");
//            String username=resultSet.getString("username");
//            String lastName=resultSet.getString("lastName");
//            String firstName=resultSet.getString("firstName");
//            String password=resultSet.getString("password");
                Integer id1 = resultSet.getInt("id_user1");
                Integer id2 = resultSet.getInt("id_user2");
                LocalDate friends_from = resultSet.getDate("friends_from").toLocalDate();
                FriendshipStatus friendshipStatus = FriendshipStatus.valueOf(resultSet.getString("status"));

                Friendship friendship = new Friendship(id1, id2, friends_from, friendshipStatus);
                friendships.add(friendship);

            }
        } catch (SQLException e) {
        } catch (IllegalArgumentException e) {
        }
        return friendships;
    }

    public List<Friendship> get_all_friend_requests_belonging_to_a_user(User user)  {
        List<Friendship> requests = new ArrayList<>();
        String sqlCommand = "SELECT * FROM FRIENDSHIPS WHERE id_user1=?";
        try {
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            PreparedStatement statement = connection.prepareStatement(sqlCommand);

            statement.setInt(1, user.getId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id_user1");
                Integer id2 = resultSet.getInt("id_user2");
                LocalDate friends_from = resultSet.getDate("friends_from").toLocalDate();
                FriendshipStatus friendshipStatus = FriendshipStatus.valueOf(resultSet.getString("status"));

                Friendship friendship = new Friendship(id1, id2, friends_from, friendshipStatus);
                requests.add(friendship);
            }
        } catch (SQLException e) {
        }

        return requests;
    }

    public void remove_friend_request(Integer senderId, Integer receiverId) throws SQLException {
        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
        String sqlCommand = "DELETE FROM FRIENDSHIPS WHERE id_user1=? and id_user2=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);

        preparedStatement.setInt(1, senderId);
        preparedStatement.setInt(2, receiverId);
        preparedStatement.executeUpdate();

        preparedStatement.setInt(1, receiverId);
        preparedStatement.setInt(2, senderId);
        preparedStatement.executeUpdate();
    }

    public void send_friend_request(Integer senderId, Integer receiverId) throws SQLException, RepositoryError {
        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

        String sqlCommandInsertFriendship = "INSERT INTO FRIENDSHIPS(id_user1, id_user2, friends_from, status) VALUES (?,?,?,?)";
        PreparedStatement statementDeleteFriendships = connection.prepareStatement(sqlCommandInsertFriendship);

        LocalDate friendsFrom = LocalDate.now();
        statementDeleteFriendships.setInt(1, senderId);
        statementDeleteFriendships.setInt(2, receiverId);
        statementDeleteFriendships.setDate(3, Date.valueOf(friendsFrom));
        statementDeleteFriendships.setString(4, FriendshipStatus.SENT.toString());
        statementDeleteFriendships.executeUpdate();

        statementDeleteFriendships.setInt(1, receiverId);
        statementDeleteFriendships.setInt(2, senderId);
        statementDeleteFriendships.setDate(3, Date.valueOf(friendsFrom));
        statementDeleteFriendships.setString(4, FriendshipStatus.PENDING.toString());
        statementDeleteFriendships.executeUpdate();
    }

    public void remove_friend(Integer senderId, Integer receiverId) throws SQLException, RepositoryError {
        Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

        String sqlCommandDeleteFriendship = "DELETE FROM FRIENDSHIPS WHERE (id_user1=? and id_user2=?)";
        PreparedStatement statementDeleteFriendship = connection.prepareStatement(sqlCommandDeleteFriendship);
        statementDeleteFriendship.setInt(1, senderId);
        statementDeleteFriendship.setInt(2, receiverId);
        statementDeleteFriendship.executeUpdate();

        statementDeleteFriendship.setInt(1, receiverId);
        statementDeleteFriendship.setInt(2, senderId);
        statementDeleteFriendship.executeUpdate();
    }

    public void accept_friend_request(Integer senderId, Integer receiverId) throws SQLException {
        String acceptedStatus = FriendshipStatus.ACCEPTED.toString();
        String sqlCommand = "UPDATE FRIENDSHIPS SET status=? WHERE id_user1=? and id_user2=?";
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(sqlCommand)) {
            statement.setString(1, acceptedStatus);
            statement.setInt(2, receiverId);
            statement.setInt(3, senderId);
            statement.executeUpdate();

            statement.setString(1, acceptedStatus);
            statement.setInt(2, senderId);
            statement.setInt(3, receiverId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Friendship friendship_exists(User user1, User user2) {
        String sqlFindFriendship = "SELECT * FROM FRIENDSHIPS WHERE (id_user1=? and id_user2=?)";
        try (Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(sqlFindFriendship);
        ) {
            statement.setInt(1, user1.getId());
            statement.setInt(2, user2.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id_user1 = resultSet.getInt("id_user1");
                Integer id_user2 = resultSet.getInt("id_user2");
                LocalDate friends_from = resultSet.getDate("friends_from").toLocalDate();
                FriendshipStatus friendshipStatus = FriendshipStatus.valueOf(resultSet.getString("status"));
                return new Friendship(id_user1, id_user2, friends_from, friendshipStatus);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
