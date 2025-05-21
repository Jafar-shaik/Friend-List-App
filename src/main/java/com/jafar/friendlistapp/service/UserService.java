package com.jafar.friendlistapp.service;

import com.jafar.friendlistapp.entity.User;
import com.jafar.friendlistapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getByUserName(String name){
        return userRepository.findUserByName(name);
    }

    public void deleteUser(String name){
        User findedUser=userRepository.findUserByName(name);
        userRepository.delete(findedUser);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
}
