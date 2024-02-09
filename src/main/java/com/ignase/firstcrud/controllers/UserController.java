package com.ignase.firstcrud.controllers;

import com.ignase.firstcrud.models.UserModel;
import com.ignase.firstcrud.repositories.IUserRepository;
import com.ignase.firstcrud.services.UserService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value ="/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRepository userRepository;
    @GetMapping(value ="/getUsers")
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping(value = "/saveUser")
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/getWithId{id}")
    public Optional<UserModel> getUserById(@PathVariable ("id") Long id){
        return this.userService.getById(id);
    }

    @PutMapping(path = "/update{id}")
    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){

        UserModel userModel = userRepository.findById(id).get();
        userModel.setFirst_name(request.getFirst_name());
        userModel.setLast_name(request.getLast_name());
        userModel.setEmail(request.getEmail());
        userRepository.save(userModel);
        return userModel;

    }

    @DeleteMapping(path = "/deleteWithId{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);

        if (ok) return "user with id "+ id+ " deleted!";
        else return "Error, user not deleted";
    }

}
