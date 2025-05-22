package com.jafar.friendlistapp.service;

import com.jafar.friendlistapp.entity.Friends;
import com.jafar.friendlistapp.entity.User;
import com.jafar.friendlistapp.repository.FriendsRepository;
import com.jafar.friendlistapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FriendsService {
    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserRepository userRepository;

    public Friends saveFriend(Friends friends){
        return friendsRepository.save(friends);
    }

    public void deleteFriendById(ObjectId id){
        friendsRepository.deleteById(id);
    }

    @Transactional
    public List<Friends> saveFriendToUser(Friends friends, String  userName){
        User foundUser = userRepository.findUserByName(userName);
        Friends savedFriends = friendsRepository.save(friends);
        foundUser.getFriends().add(savedFriends);
        userRepository.save(foundUser);
        return foundUser.getFriends();
    }

    public List<Friends> getAllFriendsOfUser(String userName){
        User foundUser = userRepository.findUserByName(userName);
        return foundUser.getFriends();
    }

    public Friends findByFriendName(String friendName){
        return friendsRepository.findByFriendName(friendName);
    }
}
