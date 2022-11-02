package com.example.SpringBootBasic;

import com.example.SpringBootBasic.entity.Address;
import com.example.SpringBootBasic.entity.Role;
import com.example.SpringBootBasic.entity.User;
import com.example.SpringBootBasic.model.AddressModel;
import com.example.SpringBootBasic.model.RoleModel;
import com.example.SpringBootBasic.model.UserModel;
import com.example.SpringBootBasic.repository.UserRepository;
import com.example.SpringBootBasic.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class SpringBootBasicApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUserDetailsTest() {
        UserService userService1 = Mockito.mock(UserService.class);
        UserRepository userRepository1 = Mockito.mock(UserRepository.class);
        UserModel userModel = new UserModel();

        userModel.setUserName("Test");
        userModel.setEmail("test@abc.com");
        userModel.setPassword("test@123");
        userModel.setFirstName("Check");
        userModel.setLastName("Data");
        List<RoleModel> roleModels = new ArrayList<>();
        RoleModel roleModel = new RoleModel();
        roleModel.setDept("Science");
        roleModel.setName("User");

        userModel.setRole(roleModels);

        AddressModel addressModel     = new AddressModel();
        addressModel.setId(1001);
        addressModel.setPincode("2345");
        addressModel.setCity("irving");
        addressModel.setLocation("east");


         List<UserModel> userModels = new ArrayList<>();
         userModels.add(userModel);

         User user = new User();
         user.setUserId(1001);
         user.setUserName(userModel.getUserName());
         user.setPassword(userModel.getPassword());
         user.setFirstName(userModel.getFirstName());
         user.setLastName(userModel.getLastName());
         user.setEmail(userModel.getEmail());


         Mockito.when(userRepository1.save(Mockito.any(User.class))).thenReturn(user);
        String s = this.userService.saveUserData(userModels);
         Assert.assertEquals(s, "success");
    }

   /* @Test
    public void retriveNameIsCorrect(){
    Mockito.when(userModel.getUserName()).thenReturn("zyabin");
    String testName = userModel.getFirstName();
    Assert.assertEquals("zyabin", testName);*/


    @Test
    public void fetchDetailsForSelectUserTest(){
        UserRepository userRepository1 = Mockito.mock(UserRepository.class);
        User user = new User();
        user.setUserId(1);
        user.setUserName("test@123");
        user.setFirstName("test");
        user.setLastName("case");
        user.setEmail("test@124.com");
        user.setPassword("test@#123");
        user.setInstitute("zorba");

        Address address = new Address();
        address.setLocation("east");
        address.setCity("irving");
        address.setId(1001);
        address.setPincode("76040");

        Role role = new Role();
        role.setRoleId(1002);
        role.setDept("science");
        role.setName("admin");

        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoles(roleList);

        Mockito.when(userRepository1.findById(1)).thenReturn(Optional.of(user));

        User user1 = this.userService.fetchUserInfoBasedOnUserId(1);
        Mockito.verify(userRepository1,Mockito.times(1)).findById(1) ;

        Assert.assertEquals(user1.getUserId(),null);





    }
}
