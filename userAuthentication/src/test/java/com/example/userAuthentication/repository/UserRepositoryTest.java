package com.example.userAuthentication.repository;

import com.example.userAuthentication.domain.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private UserData userData;

    @BeforeEach
    public void setUp() throws Exception {
        userData = new UserData("John@gmail.com" , "JOHN123");
    }
    @AfterEach
    public void tearDown() throws Exception {
        userData = null;
    }

    @Test
    public void testSaveUserSuccess() {
        userRepository.save(userData);
        UserData object = userRepository.findById(userData.getUserEmail()).get();
        assertEquals(userData.getUserEmail(), object.getUserEmail());
    }

    @Test
    public void testLoginUserSuccess() {
        userRepository.save(userData);
        UserData object = userRepository.findById(userData.getUserEmail()).get();
        assertEquals(userData.getUserEmail(), object.getUserEmail());
    }
}
