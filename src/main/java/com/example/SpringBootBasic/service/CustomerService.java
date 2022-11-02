package com.example.SpringBootBasic.service;


import com.example.SpringBootBasic.entity.Address;
import com.example.SpringBootBasic.entity.Customer;
import com.example.SpringBootBasic.entity.FoodItems;
import com.example.SpringBootBasic.exception.ExceptionService;
import com.example.SpringBootBasic.model.AddressModel;
import com.example.SpringBootBasic.model.CustomerModel;
import com.example.SpringBootBasic.model.FoodModel;
import com.example.SpringBootBasic.repository.CustomerRepository;
import com.example.SpringBootBasic.validatation.ValidateInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public String saveCustomerInformation(List<CustomerModel> customerModels) throws ExceptionService {

        try {

            for (CustomerModel customerModel : customerModels) {

                Customer customer = new Customer();

                ValidateInformation validateInformation = new ValidateInformation();

                customer.setCustId(customerModel.getCustId());
                customer.setInstitute(customerModel.getInstitute());

                if (validateInformation.validateMobileNumber(customerModel.getMobileNumber())) {
                    customer.setMobileNumber(customerModel.getMobileNumber());

                } else {
                    return "Mobile Number is not valid. Please try 10 digits Number";
                }

                if (validateInformation.validateCustomerName(customerModel.getCustName())) {
                    customer.setCustName(customerModel.getCustName());
                } else {
                    return "Customer name is null.. Please, provide Customer name ";
                }

                if (validateInformation.validateCountry(customerModel.getCountry())) {
                    customer.setCountry(customerModel.getCountry());
                } else {
                    return "Country name is null.. Please, provide country name ";
                }






                List<FoodModel> foodModels = customerModel.getList();

                    for (FoodModel foodModel : foodModels) {
                        FoodItems foodItems = new FoodItems();
                        if (validateInformation.validateItemsName(foodModel.getName())) {
                            foodItems.setName(foodModel.getName());
                        } else {
                            return "Items name is null. Please provide items name  ";
                        }

                        if (validateInformation.validateQuantity(foodModel.getQuantity())) {
                            foodItems.setQuantity(foodModel.getQuantity());
                        } else {
                            return "Items Quantity is required: Please provide at least one item";
                        }
                        customer.addFoodItems(foodItems);
                    }



                        AddressModel addressModel = customerModel.getAddress();
                        Address address = new Address();
                        address.setId(addressModel.getId());
                        address.setCity(addressModel.getCity());
                        address.setPincode(addressModel.getPincode());
                        address.setLocation(addressModel.getLocation());
                        customer.setAddress(address);




                this.customerRepository.save(customer);

            }
        } catch (Exception e) {
            System.err.println("Getting Error : " + e.getMessage());
        }
        return " Data Saved Successfully";

    }
    public String updateCustomerInfoByUserId(Integer userId, List<CustomerModel> customerModel){
        Optional<Customer> result = this.customerRepository.findById(userId);


                 if(result !=null) {
           try {
               for (CustomerModel customerModels : customerModel) {
                   Customer customer = result.get();
                   customer.setCustId(customerModels.getCustId());
                   customer.setCustName(customerModels.getCustName());
                   customer.setMobileNumber(customerModels.getMobileNumber());
                   customer.setCountry(customerModels.getCountry());


                   List<FoodModel> foodModels = customerModels.getList();

                   for (FoodModel foodModel : foodModels) {
                       FoodItems foodItems = new FoodItems();
                       foodItems.setItemId(foodModel.getItemId());
                       foodItems.setName(foodModel.getName());
                       foodItems.setQuantity(foodModel.getQuantity());

                       customer.updateFoodItems(foodItems);

                   }
                   this.customerRepository.save(customer);
               }
           }catch (Exception e){
               System.err.println("Exception occurred : " + e.getMessage());

           }

        }
        return "Save Success";
    }

    public String deleteRecords(Integer userId) {
        this.customerRepository.deleteById(userId);
                return "delete success";
    }

    public String updateCustomerInfo(CustomerModel customerModel) {
        List<Customer> customers = this.customerRepository.fetchCustomerInfoByName(customerModel.getCustName());
        if(customers !=null){
            for(Customer customer : customers){
                customer.setCountry(customerModel.getCountry());
                customer.setInstitute(customerModel.getInstitute());
                customer.setMobileNumber(customer.getMobileNumber());


                List<FoodModel> foodModels = customerModel.getList();
                List<FoodItems> foodItems = customer.getList();

                for(int i = 0;i<foodModels.size(); i++){
                    FoodModel foodModel = foodModels.get(i);
                    for(int j=0; j<foodItems.size(); j++){
                        FoodItems foodItem = foodItems.get(j);
                        if(i==j){
                            foodItem.setName(foodModel.getName());
                            foodItem.setQuantity(foodModel.getQuantity());
                            customer.addFoodItems(foodItem);
                        }
                    }
                }
                AddressModel addressModel = customerModel.getAddress();
                Address address = customer.getAddress();
                address.setCity(addressModel.getCity());
                address.setPincode(addressModel.getPincode());
                address.setLocation(addressModel.getLocation());

                customer.setAddress(address);
                this.customerRepository.save(customer);

            }
        }
        return "Customer information  updated successfully";
    }
}
