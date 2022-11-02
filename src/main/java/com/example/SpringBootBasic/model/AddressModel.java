package com.example.SpringBootBasic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {
    private int id;
    private String city;
    private String pincode;
    private String location;
}
