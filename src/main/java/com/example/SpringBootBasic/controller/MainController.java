package com.example.SpringBootBasic.controller;

import com.example.SpringBootBasic.exception.ExceptionService;
import com.example.SpringBootBasic.model.CustomerModel;
import com.example.SpringBootBasic.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/customer")
@RestController
public class MainController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getDataFromFinalProject") // http://localhost:8085/customer/getDataFromFinalProject
    public String getData(){
        return "this is the data from final project";
    }


    @PostMapping(value = "/saveData") // http://localhost:8085/customer/saveData
    public String saveCustomerData(@RequestBody List<CustomerModel> customerModels ) throws ExceptionService {
        System.out.println("The list size is " + customerModels.size());


        String result = this.customerService.saveCustomerInformation(customerModels);
        return result;

        }

        @PutMapping("/updateDataBaseOnCustId")   //  http://localhost:8085/customer/updateDataBaseOnCustId
        public String updateDataBasedOnCustomerId(@RequestParam("userId") Integer userId , @RequestBody List<CustomerModel> customerModel){
        String result = this.customerService.updateCustomerInfoByUserId(userId ,  customerModel);
        return result;
        }

        @PutMapping("/updateCustInfo")   //  http://localhost:8085/customer/updateCustInfo
        public String updateCustomerInfo(@RequestBody CustomerModel customerModel){
        String result = this.customerService.updateCustomerInfo(customerModel);
        return  result;
        }

        @DeleteMapping("/deleteData")
    public String deleteRecords(@Param("custId") Integer userId){
        return this.customerService.deleteRecords(userId);
        }
    }

