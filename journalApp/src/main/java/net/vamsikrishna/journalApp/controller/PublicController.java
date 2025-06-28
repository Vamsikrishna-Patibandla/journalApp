package net.vamsikrishna.journalApp.controller;

import net.vamsikrishna.journalApp.entity.User;
import net.vamsikrishna.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){

        userService.saveNewUser(user);
    }

}
