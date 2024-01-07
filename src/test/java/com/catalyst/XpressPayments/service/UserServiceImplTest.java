package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.exception.UserNotFoundException;
import com.catalyst.XpressPayments.model.User;
import com.catalyst.XpressPayments.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeAll
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testFindById_UserFound() throws UserNotFoundException {
        User user = new User();
        user.setId(1);
        userRepository.save(user);
        User foundUser = userService.findById(1);
        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }


    @Test
    void testFindUserByEmail_UserFound() throws UserNotFoundException {
        User user = new User();
        user.setEmail("testUser@gmail.com");
        userRepository.save(user);
        User foundUser = userService.findUserByEmail("testUser@gmail.com");
        assertNotNull(foundUser);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    void testFindUserByEmail_UserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.findUserByEmail("wrongUsert@gmail.com"));
    }
}
