package com.example.devicemanagement.devicemanagement.Service;

import com.example.devicemanagement.devicemanagement.Model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
public class UserAuthService {
    @Autowired
   private JdbcTemplate jdbcTemplate;


    String query="SELECT * FROM users where username=?";
//
    public MyUserDetails executeQuery(String username){
      try {
          MyUserDetails myUserDetails = jdbcTemplate.queryForObject(query, new Object[]{username}, (ResultSet resultset, int row) -> {
              MyUserDetails myUserDetails1 = new MyUserDetails();
              myUserDetails1.setUsername(resultset.getString("username"));
              myUserDetails1.setPassword(resultset.getString("password"));
              return myUserDetails1;
          });
          return myUserDetails;
      }
      catch (EmptyResultDataAccessException e){
          return new MyUserDetails();
      }

    }

    }

