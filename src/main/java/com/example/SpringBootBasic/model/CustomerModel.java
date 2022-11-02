package com.example.SpringBootBasic.model;


import com.example.SpringBootBasic.entity.FoodItems;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter

public class CustomerModel {
    private Integer custId;
    private String custName;
    private Long mobileNumber;
    private String country;
    private String institute;
    private AddressModel address;
    private ArrayList<FoodModel> list;
}
