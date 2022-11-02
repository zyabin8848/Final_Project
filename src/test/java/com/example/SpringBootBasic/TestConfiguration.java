package com.example.SpringBootBasic;

import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.repository.UserRepository;
import com.example.SpringBootBasic.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
@Profile("test")
public class TestConfiguration {
    @Bean
    public UserService getUserService() {
        return new UserService();
    }

}