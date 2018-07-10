package com.interact.restapis.controller;


import com.interact.restapis.model.User;
import com.interact.restapis.repository.UserRepository;
import com.interact.restapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inter-act")
public class UserController {

    @Autowired
    private UserService userService;

    //Get all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }


    //Get single user
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    //Get single user using EMAIL ID
    @GetMapping("/user/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email) {
        return userService.getUserByEmail(email);
    }

    //Get user ID using EMAIL
    @GetMapping("/users/id/{email}")
    public Long getUserIdByEmail(@PathVariable(value = "email") String email) {
        User user = userService.getUserByEmail(email);
        if(user == null)
            return (long)-1;
        return user.getId();
    }


//    public ResponseEntity<User> addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password){
    //Create new user
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User u = userService.addUser(user);
        if(u == null)
            return new ResponseEntity<User>(u, HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable(value = "id") Long id){
        if(userService.updateUser(user, id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        if(!userService.deleteUser(id)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
