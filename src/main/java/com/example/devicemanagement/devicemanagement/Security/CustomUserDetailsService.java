package com.example.devicemanagement.devicemanagement.Security;

import com.example.devicemanagement.devicemanagement.Model.MyUserDetails;
import com.example.devicemanagement.devicemanagement.Service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    JwtToken jwtToken;

    @Autowired
    public UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetails= userAuthService.executeQuery(username);
        return new MyUserDetails(username,myUserDetails.getPassword());

          }
}
