package com.herokuapp.mrndesign.matned.controller;

import com.herokuapp.mrndesign.matned.dto.UserDTO;
import com.herokuapp.mrndesign.matned.dto.UserRegistrationDTO;
import com.herokuapp.mrndesign.matned.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public UserDTO findAllUsers(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public List<UserDTO> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/users")
    @ResponseBody
    public UserDTO addUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.add(userRegistrationDTO);
    }

    @PostMapping("/users/{id}")
    @ResponseBody
    public UserDTO updateUserLogin(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return userService.updateLogin(id,userDTO);
    }

    @PostMapping("/users/{id}/password")
    @ResponseBody
    public UserDTO updateUserPassword(@PathVariable Long id, @RequestBody UserRegistrationDTO userRegistrationDTO){
        return userService.updatePassword(id,userRegistrationDTO);
    }

    @GetMapping("/users/{id}/add-role/{userRole}")
    @ResponseBody
    public UserDTO assignRoleToUser(@PathVariable Long id, @PathVariable String userRole){
        return userService.assignNewRole(id,userRole);
    }

    @GetMapping("/users/{id}/take-away-role/{userRole}")
    @ResponseBody
    public UserDTO takeAwayRoleFromUser(@PathVariable Long id, @PathVariable String userRole){
        return userService.takeAwayRole(id,userRole);
    }



}
