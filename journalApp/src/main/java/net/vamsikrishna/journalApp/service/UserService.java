package net.vamsikrishna.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.vamsikrishna.journalApp.entity.User;
import net.vamsikrishna.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;



    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void saveNewUser(User user){
        try{
            user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }
        catch (Exception e){
           log.info("HAahahahah");
          log.warn("HAahahahah");
          log.debug("HAahahahah");
           log.trace("HAahahahah");
            log.error("Error Occured for user : {}", user.getUserName());




        }

    }

    public void saveAdmin(User user){
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}
