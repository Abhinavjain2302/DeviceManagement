package com.example.devicemanagement.devicemanagement.Controller;

import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import com.example.devicemanagement.devicemanagement.Model.User;
import com.example.devicemanagement.devicemanagement.Service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api("User")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/create")
    @ApiOperation(value = "To Create new User",response = String.class)
    public ResponseEntity<ResponseModel> createUser(@ApiParam(value = "User details for creating new user",required = true) @RequestBody User user){
     return  userService.saveUser(user);
    }

    @GetMapping("/get")
    @ApiOperation(value = "List of all users",responseContainer = "List",response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code=200,message = "Successfully Retrieved Users"),
            @ApiResponse(code = 403,message = "Forbidden")
    })
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/update/{username}")
    @ApiOperation(value = "To Create new User",responseContainer = "List",response = User.class)
    public ResponseEntity<ResponseModel> updateUser(
            @ApiParam(value = "Provided username will be used to update particular user",required = true) @PathVariable String username,
            @ApiParam(value = "User details which needed to be updated",required = true) @RequestBody User user){
        return  userService.updateUser(username,user);
    }

    @DeleteMapping("/delete/{username}")
    @ApiOperation(value = "Delete a particular user from database",response = String.class)
    public List<User> deleteUser(@ApiParam(value = "User id needed to delete particular user from database",required = true) @PathVariable String username){
        return userService.deleteUser(username);

    }

    @GetMapping("/usersPerDept/{dept_id}")
    @ApiOperation(value = "List of all Users per Department",responseContainer = "List",response = User.class)
    public List<User> usersPerDept(@ApiParam("Department id to get all Users for that Department") @PathVariable int dept_id){
       return userService.usersPerDept(dept_id);
    }


}
