package net.vamsikrishna.journalApp.service;

import net.vamsikrishna.journalApp.entity.User;
import net.vamsikrishna.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userServiceTest {

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({
            "krishna",
            "vamsi",
            "rama"
    })
    public void testAdd(String name){

        User user = userRepository.findByUserName(name);
        Assertions.assertNotNull(user, "failed for :" + name);
    }

    @ParameterizedTest
    @CsvSource({
            "1,  2, 3",
            "2, 5, 7",
            "2, 5, 3"
    })
    public void test(int a, int b, int expected){
        Assertions.assertEquals(expected , a+ b);
    }

}
