package com.jafar.friendlistapp.repository;

import com.jafar.friendlistapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByName(String name);
}
