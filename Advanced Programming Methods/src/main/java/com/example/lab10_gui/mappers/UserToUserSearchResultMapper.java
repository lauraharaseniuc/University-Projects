package com.example.lab10_gui.mappers;

import com.example.lab10_gui.entities.Friendship;
import com.example.lab10_gui.entities.FriendshipStatus;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.entities.UserSearchResult;
import com.example.lab10_gui.repositories.db_repositories.FriendshipDbRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserToUserSearchResultMapper {
    private FriendshipDbRepository friendshipDbRepository;
    private User userWhoSearches;

    private UserSearchResult convert (User user) {
        FriendshipStatus friendshipStatus=FriendshipStatus.FORBIDDEN;
        if (!Objects.equals(userWhoSearches.getId(), user.getId())) {

            Friendship friendship = this.friendshipDbRepository.friendship_exists(userWhoSearches, user);
            if (friendship!=null) {
                friendshipStatus = friendship.getFriendshipStatus();
            } else {
                friendshipStatus = FriendshipStatus.NON_EXISTENT;
            }
            return new UserSearchResult(user.getId(), user.getLast_name(), user.getFirst_name(), user.getUsername(), friendshipStatus);
        }
        return new UserSearchResult(user.getId(), user.getLast_name(), user.getFirst_name(), user.getUsername(), friendshipStatus);
    }

    public void setFriendshipDbRepository(FriendshipDbRepository friendshipDbRepository) {
        this.friendshipDbRepository = friendshipDbRepository;
    }

    public void setUserWhoSearches(User userWhoSearches) {
        this.userWhoSearches = userWhoSearches;
    }


    public List<UserSearchResult> convert (List<User> userList) {
        List<UserSearchResult> userSearchResultList= new ArrayList<>();
        userList.forEach(user -> userSearchResultList.add(this.convert(user)));
        return userSearchResultList;
    }
}
