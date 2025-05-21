package com.jafar.friendlistapp.controller;

import com.jafar.friendlistapp.entity.Friends;
import com.jafar.friendlistapp.entity.User;
import com.jafar.friendlistapp.service.FriendsService;
import com.jafar.friendlistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {
    @Autowired
    private FriendsService friendsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;


    @PostMapping("/create/{userName}")
    public ResponseEntity<?> createFriendToUser(@RequestBody Friends friends, @PathVariable String userName){
        List<Friends> friendsList=friendsService.saveFriendToUser(friends,userName);
        return new ResponseEntity<>(friendsList, HttpStatus.OK);
    }

    @GetMapping("/getall/{userName}")
    public ResponseEntity<?> getAllFriendsOfUser(@PathVariable String userName){
        List<Friends> friendsList=friendsService.getAllFriendsOfUser(userName);
        if (!friendsList.isEmpty()){
            return new ResponseEntity<>(friendsList,HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{userName}/{friendName}")
    public ResponseEntity<?> updateFriendOfUser(@PathVariable String userName,
                                                @PathVariable String friendName,
                                                @RequestBody Friends updatedFriend){
        User foundUser = userService.getByUserName(userName);
        Friends foundFriend= friendsService.findByFriendName(friendName);
        if(foundUser != null && foundFriend!=null){
            boolean isFriendLinked =foundUser.getFriends()
                    .stream()
                    .anyMatch(friends -> friends.getFriendName()
                    .equals(foundFriend.getFriendName()));

            if (isFriendLinked){
                foundFriend.setFriendName(updatedFriend.getFriendName());
                foundFriend.setGender(updatedFriend.getGender());
                foundFriend.setStatus(updatedFriend.getStatus());
            }
            Friends savedFriend = friendsService.saveFriend(foundFriend);
            return new ResponseEntity<>(savedFriend,HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{userName}/{friendName}")
    public ResponseEntity<?> deleteFriendOfUser(@PathVariable String userName, @PathVariable String friendName){
        User foundUser = userService.getByUserName(userName);
        Friends foundfriend = friendsService.findByFriendName(friendName);
        if(foundUser != null && foundfriend!= null){
            boolean removed = foundUser.getFriends().removeIf(friends -> friends.getId().equals(foundfriend.getId()));
            if(removed){
                userService.saveUser(foundUser);
                friendsService.deleteFriendById(foundfriend.getId());
                return new ResponseEntity<>("Friend removed from user",HttpStatus.OK);
            }else {return new ResponseEntity<>("Friend is not linked to the user",HttpStatus.NOT_FOUND);}
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
