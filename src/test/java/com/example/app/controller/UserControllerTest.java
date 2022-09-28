package com.example.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.mockito.internal.verification.VerificationModeFactory;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.example.app.controllers.UserController;
import com.example.app.model.address.Address;
import com.example.app.model.user.User;
import com.example.app.service.AddressService;
import com.example.app.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
    private MockMvc mvc;
	
   @MockBean
    private UserService userService;

   @MockBean
   private  AddressService addressService;
  

   private User user;
   private Address address;
   private String userId = "12345";
   
  @Before
  public void setUp(){
	  user = new User(userId, "alex", "a", "a");
	  address = new Address (userId, "123", "street", "town", "country");
   }
   
   @Test
   public void givenUsers_whenGetUsers_thenReturnJsonArray()
     throws Exception {
       
       User alex = new User("ol", "alex", "a", "a");
       User sasha = new User("ol", "sasha", "b", "b");
       User dima = new User("ol", "dima", "c", "c");

       List<User> allUsers = Arrays.asList(alex, sasha, dima);

       given(userService.findAll()).willReturn(allUsers);

       mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].firstName", is(alex.getFirstName())))
       .andExpect(jsonPath("$[1].firstName", is(sasha.getFirstName())))
       .andExpect(jsonPath("$[2].firstName", is(dima.getFirstName())));
       verify(userService, VerificationModeFactory.times(1)).findAll();
       reset(userService);
   }
   
   @Test
   public void shouldReturn404WhenNotFindUserById() throws Exception {
	   //String userId = "12345";
	   given(userService.get(userId)).willReturn(Optional.empty());
	   given(addressService.get(userId)).willReturn(null);
	   
	   mvc.perform(get("/users/{id}", userId)).andExpect(status().isNotFound());
   }
   
   @Test
   public void shouldFetchOneUserById() throws Exception{
	   given(userService.get(userId)).willReturn(Optional.of(user));
	   given(addressService.get(userId)).willReturn(address);
	   
	   mvc.perform(get("/users/{id}", userId))
	   .andExpect(status().isOk())
	   .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
	   .andExpect(jsonPath("$.middleName", is(user.getMiddleName())))
	   .andExpect(jsonPath("$.lastName", is(user.getLastName())))
	   .andExpect(jsonPath("$.postalCode", is(address.getPostalCode())))
	   .andExpect(jsonPath("$.street", is(address.getStreet())))
	   .andExpect(jsonPath("$.town", is(address.getTown())))
	   .andExpect(jsonPath("$.country", is(address.getCountry())));
   }
   
   @Test
   public void shouldDeleteUser() throws Exception {
	   given(userService.get(userId)).willReturn(Optional.of(user));
	   doNothing().when(userService).remove(user.getId());
	   
	   given(addressService.get(userId)).willReturn(address);
	   doNothing().when(addressService).remove(address.getUserId());
	   
	   mvc.perform(delete("/users/{id}", user.getId()))
	   .andExpect(status().isOk());
   }
   
}


