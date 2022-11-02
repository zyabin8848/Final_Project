package com.example.SpringBootBasic.service;

import com.example.SpringBootBasic.entity.Address;
import com.example.SpringBootBasic.entity.Role;
import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.model.AddressModel;
import com.example.SpringBootBasic.model.RoleModel;
import com.example.SpringBootBasic.model.UserModel;
import com.example.SpringBootBasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


   public String saveUserData(List<UserModel> userModels){
       try{
           for(UserModel userModel : userModels){
               User user = new User();
               user.setUserName(userModel.getUserName());
               user.setFirstName(userModel.getFirstName());
               user.setLastName(userModel.getLastName());
               user.setEmail(userModel.getEmail());
             String encryptedPwd = "";

             if(userModel.getPassword()!=null){
                 encryptedPwd = this.bCryptPasswordEncoder.encode(userModel.getPassword());
                 System.out.println("Encrypted Passwrd: " + encryptedPwd);
             }
               user.setPassword(encryptedPwd);

               user.setInstitute(userModel.getInstitute());

               List<RoleModel> roleModels = userModel.getRole();

               for(RoleModel roleModel : roleModels) {
                   Role role = new Role();
                   role.setDept(roleModel.getDept());
                   role.setName(roleModel.getName());
                   user.addRoles(role);
               }

               Address address = new Address();
               AddressModel addressModel = userModel.getAddress();

               address.setId(addressModel.getId());
               address.setCity(addressModel.getCity());
               address.setPincode(addressModel.getPincode());
               address.setLocation(addressModel.getLocation());

               user.setAddress(address);

               this.userRepository.save(user);

           }

       } catch (Exception e){
           System.err.println("Err : " + e.getMessage());
       }
       finally {
           System.out.println("Save Success");
       }
       return "success";
   }


    public User fetchUserInfoBasedOnUserId(Integer userId){
       User user = new User();
       Optional<User> result = this.userRepository.findById(userId); // optional is from java8 new features
       if ( result !=null && !result.isEmpty()){
           user = result.get();
       }
       return user;
    }

    public UserModel populateUserInfo(User user){
       UserModel userModel = new UserModel();
       userModel.setUserId(user.getUserId());
       userModel.setUserName(user.getUserName());

       userModel.setPassword(user.getPassword());

       userModel.setFirstName(user.getFirstName());
       userModel.setLastName(user.getLastName());
       userModel.setEmail(user.getEmail());
       userModel.setInstitute(user.getInstitute());

       List<RoleModel> roleModels = new ArrayList<>();
        List<Role> roleList = user.getRoles();
       for(Role role : roleList){
           RoleModel roleModel = new RoleModel();

           roleModel.setDept(role.getDept());
           roleModel.setName(role.getName());
           roleModels.add(roleModel);

       }userModel.setRole(roleModels);

       AddressModel addressModel = new AddressModel();
       Address address = user.getAddress();
       addressModel.setId(address.getId());
       addressModel.setCity(address.getCity());
       addressModel.setPincode(address.getPincode());
       addressModel.setLocation(address.getLocation());

       userModel.setAddress(addressModel);

       return userModel;
    }

    public String updateUserInfoBasedOnUserId(Integer userId, UserModel userModel) {
       Optional<User> result = this.userRepository.findById(userId);

       if(result!=null){

           try {
               User user = result.get();
               user.setUserId(userModel.getUserId());
               user.setUserName(userModel.getUserName());
               user.setPassword(userModel.getPassword());
               user.setFirstName(userModel.getFirstName());
               user.setLastName(userModel.getLastName());
               user.setEmail(userModel.getEmail());
               user.setInstitute(userModel.getInstitute());

               List<RoleModel> roleModels = userModel.getRole();
               List<Role> roles = user.getRoles();
               for (RoleModel roleModel : roleModels) {
                   Role role = new Role();
                   role.setRoleId(roleModel.getId());
                   role.setName(roleModel.getName());
                   role.setDept(roleModel.getDept());
                   user.updateRolesInfo(role);

               }
               this.userRepository.save(user);
           }catch (Exception e){
               System.err.println("Exception:::" + e.getMessage()) ;
           }
       }
       return "data update Successfully";
    }

    public String updateUserInfo(UserModel userModel){
       User user = this.userRepository.fetchUserByName(userModel.getFirstName() , userModel.getLastName());
       if (user !=null){
         try {
             user.setUserId(userModel.getUserId());
             user.setUserName(userModel.getUserName());
             user.setFirstName(userModel.getFirstName());
             user.setPassword(userModel.getPassword());
             user.setEmail(userModel.getEmail());
             user.setInstitute(userModel.getInstitute());

             List<RoleModel> roleModels = userModel.getRole();
             List<Role> roles = user.getRoles();

             for (int i = 0; i < roleModels.size(); i++) {
                 RoleModel roleModel = roleModels.get(i);
                 for (int j = 0; j < roles.size(); j++) {
                     if (i == j) {
                         Role role = roles.get(j);
                         role.setRoleId(roleModel.getId());
                         role.setName(roleModel.getName());
                         role.setDept(roleModel.getDept());
                         user.addRoles(role);
                         break;

                     }
                 }
             }
             this.userRepository.save(user);
         }catch (Exception e)   {
             System.err.println("Err : " + e.getMessage());
         }
       }
       return "Success";
    }
}




