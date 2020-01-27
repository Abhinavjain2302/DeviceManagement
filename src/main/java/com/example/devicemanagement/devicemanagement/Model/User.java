package com.example.devicemanagement.devicemanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
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


//    public User(String username, String password, String name, Timestamp timestamp,Department department){
//        this.username=username;
//        this.password=password;
//        this.name=name;
//        this.timestamp=timestamp;
//        this.department=department;
//    }
}
