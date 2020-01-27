package com.example.devicemanagement.devicemanagement.Model;

import com.example.devicemanagement.devicemanagement.Service.UserAuthService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;;

import java.util.Arrays;
import java.util.Collection;

@Getter
@Setter
public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String username;
    private String password;
   // private List<SimpleGrantedAuthority> authorityList;

@Autowired
public UserAuthService userAuthService;

    public MyUserDetails(String username,String password){

       this.username=username;
       this.password=password;

    }

    public MyUserDetails(){

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
