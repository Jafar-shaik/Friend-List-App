package com.jafar.friendlistapp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Friends {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String friendName;
    private String gender;
    private String Status;
}
