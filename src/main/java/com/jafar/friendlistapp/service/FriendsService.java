package com.jafar.friendlistapp.service;

import com.jafar.friendlistapp.entity.Friends;
import com.jafar.friendlistapp.entity.User;
import com.jafar.friendlistapp.repository.FriendsRepository;
import com.jafar.friendlistapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveFriendToUser(Friends friends, String  userName){
        User foundUser = userRepository.findUserByName(userName);
        Friends savedFriends = friendsRepository.save(friends);
        foundUser.getFriends().add(savedFriends);
        userRepository.save(foundUser);
    }

    public List<Friends> getAllFriendsOfUser(String userName){
        User foundUser = userRepository.findUserByName(userName);
        return foundUser.getFriends();
    }

    public void deleteFriendOfUser(ObjectId friendId , String userName){
        User foundUser = userRepository.findUserByName(userName);
        List<Friends>  friendsList=foundUser.getFriends();
        friendsList.removeIf(x -> x.getId().equals(friendId));
        userRepository.save(foundUser);
        friendsRepository.deleteById(friendId);
    }
}
