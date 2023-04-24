package com.example.lab10_gui.mappers;

import com.example.lab10_gui.entities.Friendship;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;

import java.util.ArrayList;
import java.util.List;

public class FriendshipToUserMapper {
    private UserDbRepository userDbRepository;

    private User convert (Friendship friendship) {
        User user=this.userDbRepository.get_user_on_id(friendship.getId().getKey2());
        if (user!=null) {
            return user;
        }
        return null;
    }

    public void setUserDbRepository(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
    }

    public List<User> convert (List<Friendship> friendships) {
        List<User> friends=new ArrayList<>();
        friendships.forEach(friendship -> { if (convert(friendship)!=null)
            friends.add(convert(friendship));
        });
        return friends;
    }
}
