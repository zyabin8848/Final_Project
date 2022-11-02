package com.example.SpringBootBasic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cust_id" , nullable = true)
    private Integer custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "mobile_number")
    private Long mobileNumber;
    @Column(name = "country")
    private String country;
    @Column(name="institute")
    private String institute;


    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(nullable = true, name = "Address_Id" , referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FoodItems> list = new ArrayList<>();

    public void addFoodItems(FoodItems foodItems){
        this.list.add(foodItems);
        foodItems.setCustomer(this);
    }

    public void updateFoodItems(FoodItems foodItems){
        this.list.add(foodItems);
        foodItems.setCustomer(this);
    }



}
