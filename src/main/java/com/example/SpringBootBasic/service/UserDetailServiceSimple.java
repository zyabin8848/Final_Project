package com.example.SpringBootBasic.service;

import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class UserDetailServiceSimple implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userRepository.fetchUserByUserName(userName);
        UserDetails userDetails = new MyUserDetailService(user);

        return userDetails;
    }
}
