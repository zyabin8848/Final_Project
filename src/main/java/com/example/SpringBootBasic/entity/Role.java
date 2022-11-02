package com.example.SpringBootBasic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name ="Role_info")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;
    @Column(name = "role_name")
    private String name;
    @Column(name = "dept_name")
    private String dept;

    @ManyToMany(mappedBy = "roles" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> userList = new HashSet<User>();


}
