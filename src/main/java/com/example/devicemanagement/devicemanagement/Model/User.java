package com.example.devicemanagement.devicemanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
public class User {
    private String username;
    private  String password;
    private String name;
    private Timestamp timestamp;
    private Department department;

    public User(){

    }

}
