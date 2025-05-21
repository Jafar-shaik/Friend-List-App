package com.jafar.friendlistapp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class Friends {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String friendName;

    private String gender;
    private String Status;
}
