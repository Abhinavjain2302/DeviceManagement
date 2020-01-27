package com.example.devicemanagement.devicemanagement.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Device {
    private int id;
    private String type;
    private Timestamp listed_on;
    private String platform;

    public  Device(){}
}
