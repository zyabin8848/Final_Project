package com.example.SpringBootBasic.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Setter
@Getter
@Table(name = "User_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer userId;
    @Column
    private String userName;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String institute;

    @ManyToMany( cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JoinTable(name = "user_role" ,
            joinColumns = {@JoinColumn(name = "custId")} ,
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles  = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( nullable = true , name = "Add_id" , referencedColumnName = "id" )
    private Address address;


    public void addRoles(Role role  ) {
        this.roles.add(role);
        role.getUserList().add(this);

    }


    public void addAdd(Address address) {

    }

    public void updateRolesInfo(Role role) {
        this.roles.add(role);
        role.getUserList().add(this);
    }
}
