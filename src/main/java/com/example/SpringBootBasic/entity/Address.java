package com.example.SpringBootBasic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
   @Column
    private String city;
   @Column
    private String pincode;
   @Column
    private String location;

    @OneToOne(mappedBy = "address")
   private Customer customer;




}
