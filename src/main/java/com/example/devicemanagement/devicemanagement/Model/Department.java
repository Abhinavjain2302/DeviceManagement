package com.example.devicemanagement.devicemanagement.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Department {
    private int id;
    private String name;

    public Department(){}

    public  Department(int id){
        this.id=id;
    }

}
