package com.jafar.friendlistapp.controller;


import com.jafar.friendlistapp.entity.User;
import com.jafar.friendlistapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User saved = userService.saveUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUsers() {
        List<User> userlist = userService.getAllUser();
        if (!userlist.isEmpty()) {
            return new ResponseEntity<>(userlist, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User foundUser = userService.getByUserName(userName);
        if (foundUser != null) {
            if (user.getName() != null && !user.getName().equals(foundUser)) {
                foundUser.setName(user.getName());
            }
            User updatedUser = userService.saveUser(foundUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {
        User foundUser = userService.getByUserName(userName);
        if (foundUser != null) {
            userService.deleteUser(userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable ObjectId id){
        userService.deleteById(id);
    }
}
