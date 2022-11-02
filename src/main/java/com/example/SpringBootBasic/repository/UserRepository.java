package com.example.SpringBootBasic.repository;

import com.example.SpringBootBasic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("select u from User u where u.firstName = ?1 and u.lastName = ?2")
    User fetchUserByName(String firstName, String lastName);

    @Query("select u from User u where u.userName =?1")
    User fetchUserByUserName(String userName);
}
