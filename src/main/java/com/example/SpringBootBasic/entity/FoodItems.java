package com.example.SpringBootBasic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FoodItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Integer itemId;
    @Column
    private String name;
    @Column
    private Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}




