package com.example.SpringBootBasic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel {
    private int userId;
    private String userName;
    private String firstName; // this variable must match with the .json file
    private String lastName;
    private String email;
    private String password;

    private AddressModel address;


    private List<RoleModel> role;
    private String institute;
}