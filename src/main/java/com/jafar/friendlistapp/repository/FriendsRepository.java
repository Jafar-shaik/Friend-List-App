package com.jafar.friendlistapp.repository;

import com.jafar.friendlistapp.entity.Friends;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendsRepository extends MongoRepository<Friends, ObjectId> {
    Friends findByFriendName(String friendName);
}
