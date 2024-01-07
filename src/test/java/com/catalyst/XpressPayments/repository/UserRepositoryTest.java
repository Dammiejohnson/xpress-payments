package com.catalyst.XpressPayments.repository;

import com.catalyst.XpressPayments.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findById() {
        User user = new User();
        user.setId(1);
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertThat(userRepository.findById(1)).isNotNull();
    }
}