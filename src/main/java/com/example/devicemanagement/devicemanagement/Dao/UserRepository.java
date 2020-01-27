package com.example.devicemanagement.devicemanagement.Dao;

import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import com.example.devicemanagement.devicemanagement.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    ResponseEntity<ResponseModel> saveUser(User user);
    List<User> deleteUser(String username);
    List<User> getUsers();
    Object updateUser(String username, User user);
    List<User> usersPerDept(int dept_id);

}
