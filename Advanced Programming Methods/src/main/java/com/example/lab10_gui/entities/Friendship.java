package com.example.lab10_gui.entities;


import com.example.lab10_gui.keys.TwoDIntegerKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Friendship extends Entity<TwoDIntegerKey> {
    private final LocalDate friendsFrom;
    private FriendshipStatus friendshipStatus;

    public Friendship (Integer id_user1, Integer id_user2, LocalDate friends_from,FriendshipStatus friendshipStatus) {
        this.setId(new TwoDIntegerKey(id_user1, id_user2));
        this.friendsFrom=friends_from;
        this.friendshipStatus=friendshipStatus;
    }

    public Friendship (Integer id_user1, Integer id_user2) {
        this.setId(new TwoDIntegerKey(id_user1, id_user2));
        this.friendsFrom=LocalDate.now();
    }

    public LocalDate getFriendsFrom() {
        return friendsFrom;
    }

    @Override
    public String toString() {
        return "Friendship{" +"id user1:"+ this.getId().getKey1()+", "+
                "id user2: "+ this.getId().getKey2()+" , "+
                "friendsFrom=" + friendsFrom +
                '}';
    }

    public FriendshipStatus getFriendshipStatus() {
        return friendshipStatus;
    }

    public void setFriendshipStatus(FriendshipStatus friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }
}
