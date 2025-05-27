package com.jafar.friendlistapp.entity;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
@JsonPropertyOrder({"id", "name","friends"})
@Data
public class User {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String name;
    private String password;
    private List<String > roles ;

    @DBRef
    private List<Friends> friends=new ArrayList<>();
}
