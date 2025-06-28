package net.vamsikrishna.journalApp.controller;


import net.vamsikrishna.journalApp.api.response.WeatherResponse;
import net.vamsikrishna.journalApp.cache.AppCache;
import net.vamsikrishna.journalApp.entity.JournalEntry;
import net.vamsikrishna.journalApp.entity.User;
import net.vamsikrishna.journalApp.repository.UserRepository;
import net.vamsikrishna.journalApp.service.UserService;
import net.vamsikrishna.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;



    @PutMapping()
    public ResponseEntity<?> upadateUser(@RequestBody User user){
        User userInDB = userService.findByUserName(user.getUserName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if(userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication1.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping()
    public ResponseEntity<?> greeting(){
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse = weatherService.getWeather("london");
        String greeting = "";
        if(weatherResponse != null){


            greeting =  " weather feels like" + weatherResponse.getCurrent();
        }
        return new ResponseEntity<>("Hi " + authentication1.getName() + greeting, HttpStatus.OK);



    }
}
