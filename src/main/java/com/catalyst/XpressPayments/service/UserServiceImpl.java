package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.model.User;
import com.catalyst.XpressPayments.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;

    @Override
    public User findById(Integer id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found"));
    }

    @Override
    public User findUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User cannot be found"));
    }
}
