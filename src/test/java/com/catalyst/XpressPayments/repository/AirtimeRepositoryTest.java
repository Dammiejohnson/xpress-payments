package com.catalyst.XpressPayments.repository;

import com.catalyst.XpressPayments.model.Airtime;
import com.catalyst.XpressPayments.model.Role;
import com.catalyst.XpressPayments.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AirtimeRepositoryTest {

    @Autowired
    private AirtimeRepository airtimeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByRequestId() {
        User user = User.builder()
                .id(1)
                .firstName("test")
                .lastName("test")
                .email("test@gmail.com")
                .phoneNumber("0988812233")
                .role(Role.USER)
                .build();
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());

        Airtime airtime = Airtime.builder()
                .requestId("23456")
                .responseCode("00")
                .responseMessage("Successful")
                .user(savedUser)
                .build();

       Airtime airtime1=  airtimeRepository.save(airtime);
        assertNotNull(airtimeRepository.findByRequestId("23456"));

        assertEquals(savedUser.getId(), airtime1.getUser().getId());


    }

}