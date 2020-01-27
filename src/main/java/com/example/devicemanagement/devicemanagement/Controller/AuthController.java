package com.example.devicemanagement.devicemanagement.Controller;

import com.example.devicemanagement.devicemanagement.Model.AuthenticationRequest;
import com.example.devicemanagement.devicemanagement.Model.AuthenticationResponse;
import com.example.devicemanagement.devicemanagement.Security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthController {

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;



    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword(),new ArrayList<>()));

        }
        catch (BadCredentialsException e){
            return ResponseEntity.ok("Authentication Failed");
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok("Exception Occurred");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwtTokens = jwtToken.generateToken(userDetails);
        
        System.out.println(jwtTokens);
        return ResponseEntity.ok(new AuthenticationResponse(jwtTokens));

    }
}
