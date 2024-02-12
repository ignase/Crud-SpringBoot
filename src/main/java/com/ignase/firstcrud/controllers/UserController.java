package com.ignase.firstcrud.controllers;

import com.ignase.firstcrud.models.UserModel;
import com.ignase.firstcrud.repositories.IUserRepository;
import com.ignase.firstcrud.services.UserService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping(value="/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRepository userRepository;

    @GetMapping(value="/list")
    public String getUsers(Model model){
        List<UserModel> users = userService.getUsers();
        model.addAttribute("users", users);
        return "usersList";
    }

    @GetMapping(value="/new")
    public String addUser(Model model){
        model.addAttribute("user", new UserModel());
        return "form";
    }
    @PostMapping(value="/save")
    public String save(@Valid @ModelAttribute  UserModel userModel, Model model){
        userService.save(userModel);
        return "redirect:/list";
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
