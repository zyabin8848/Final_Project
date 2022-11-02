package com.example.SpringBootBasic.repository;

import com.example.SpringBootBasic.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer ,Integer> {


    @Query("select c from Customer c where c.custName =?1")
    List<Customer> fetchCustomerInfoByName(String custName);
}
