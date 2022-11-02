package com.example.SpringBootBasic.controller;

import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.model.UserModel;
import com.example.SpringBootBasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUserData") // http://localhost:8085/user/saveUserData
    public String saveUserInfo(@RequestBody List<UserModel> userModels) {
        System.out.println("Size of the given List " + userModels.size());

        String result = this.userService.saveUserData(userModels);
       return result;
    }

    @GetMapping(value = "/fetchUserData", produces = MediaType.APPLICATION_JSON_VALUE) // http://localhost:8085/user/fetchUserData
    public UserModel fetchUserInfo(@RequestParam(name = "userId") Integer userId) {
        User retrivedUser = this.userService.fetchUserInfoBasedOnUserId(userId);
        UserModel finalUserModel = this.userService.populateUserInfo(retrivedUser);

        return finalUserModel;
    }

    @PutMapping("/updateUserInfoBasedOnUserId")  // http://localhost:8085/user/updateUserInfoBasedOnUserId
    public String updateUserInfo(@RequestParam("userId") Integer userId , @RequestBody UserModel userModel){
        String result = this.userService.updateUserInfoBasedOnUserId(userId , userModel);
        return  result;
    }

    @PutMapping("/updateUserInfo")   //http://localhost:8085/user/updateUserInfo
    public String updateUserInfo(@RequestBody UserModel userModel){
        String result = this.userService.updateUserInfo(userModel);
        return result;
    }

}
