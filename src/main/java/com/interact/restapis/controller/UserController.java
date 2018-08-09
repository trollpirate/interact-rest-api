package com.interact.restapis.controller;


import com.interact.restapis.model.User;
import com.interact.restapis.repository.UserRepository;
import com.interact.restapis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }


    //Get single user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws Exception{
        User user = userService.getUser(id);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ExceptionHandler({NumberFormatException.class, ConversionFailedException.class, Exception.class})
    public ResponseEntity<String> exceptionHandler(NumberFormatException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Get single user using EMAIL ID
    @GetMapping("/email/{emailid}")
    public User getUserByEmail(@PathVariable(value = "emailid") String email) {
        return userService.getUserByEmail(email);
    }

    //Get user ID using EMAIL
//    @GetMapping("/users/id/{email}")
//    public Long getUserIdByEmail(@PathVariable(value = "email") String email) {
//        User user = userService.getUserByEmail(email);
//        if(user == null)
//            return (long)-1;
//        return user.getId();
//    }


//    public ResponseEntity<User> addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password){
    //Create new user
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User u = userService.addUser(user);
        if(u == null)
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable(value = "id") Long id){
        if(userService.updateUser(user, id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        if(!userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
