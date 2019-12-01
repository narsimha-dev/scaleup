package com.scaleup.user.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaleup.user.exception.RecordNotFoundException;
import com.scaleup.user.model.User;
import com.scaleup.user.repository.UserRepository;
import com.scaleup.user.service.UserService;
 
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController
{
    @Autowired
    private UserService userService;
     
    @GetMapping("list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
 
        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping(value = "userId/{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        User entity = userService.getUserById(id);
 
        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(value = "addUser", produces = "application/json")
    public ResponseEntity<User> createOrUpdateUser(@Valid @RequestBody  User user)
                                                    throws RecordNotFoundException {
       System.err.println("save user DAta: "+ user);
    	User updated = userService.createOrUpdateUser(user);
    	System.err.println("after user obj added: "+ updated);
        return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping(value = "emailId/{email}", produces = "application/json")
    public ResponseEntity<User> deleteUserByEmail( @PathVariable("email") String email) throws RecordNotFoundException {
    	System.err.println("=====: "+ email);
    	userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
        } 

    @GetMapping("search/{item}")
    public List<User> searchByUserParam(@PathVariable String item) {
    	 
       return userService.getFirstName(item);    
    }
}