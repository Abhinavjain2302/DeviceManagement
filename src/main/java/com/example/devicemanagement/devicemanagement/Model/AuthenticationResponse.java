package com.example.devicemanagement.devicemanagement.Model;


import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken){
        this.jwtToken=jwtToken;
    }

}
