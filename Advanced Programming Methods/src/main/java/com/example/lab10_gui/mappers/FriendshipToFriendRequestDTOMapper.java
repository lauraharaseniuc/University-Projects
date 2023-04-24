package com.example.lab10_gui.mappers;

import com.example.lab10_gui.entities.FriendRequestDTO;
import com.example.lab10_gui.entities.Friendship;
import com.example.lab10_gui.entities.User;
import com.example.lab10_gui.repositories.db_repositories.UserDbRepository;

import java.util.ArrayList;
import java.util.List;

public class FriendshipToFriendRequestDTOMapper {
    private UserDbRepository userDbRepository;

    private FriendRequestDTO convert (Friendship request) {
        User user=this.userDbRepository.get_user_on_id(request.getId().getKey2());
        if (user!=null) {
            return new FriendRequestDTO(user.getUsername(), request.getFriendshipStatus().toString());
        }
        return null;
    }

    public void setUserDbRepository(UserDbRepository userDbRepository) {
        this.userDbRepository = userDbRepository;
    }

    public List<FriendRequestDTO> convert (List<Friendship> requestList) {
        List<FriendRequestDTO> friendRequestDTOS=new ArrayList<>();
        requestList.forEach(request -> {if (convert(request)!=null) {
            friendRequestDTOS.add(convert(request));
        }
        });
        return friendRequestDTOS;
    }
}
