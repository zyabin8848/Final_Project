package com.example.SpringBootBasic.validatation;

public class ValidateInformation {
    public boolean validateMobileNumber(Long mobileNumber){
        String mobNumber = String.valueOf(mobileNumber);
        if(mobNumber !=null&& mobNumber.length()==10){
            return true;
        }
        return false;

    }

    public boolean validateCustomerName(String name){
        if( name !=null){
            return true;
        } return false;
    }
    public boolean validateCountry(String country)  {
        if(country!=null){
            return true;
        }
        return false;

    }
    public boolean validateItemsName(String name){
        if(name !=null) {
            return true;
        }else {
            return false;
        }
    }
    public boolean validateQuantity(Integer quantity){
        if(quantity !=0 ){
            return true;
        } else  {
            return false;
        }
    }
}
