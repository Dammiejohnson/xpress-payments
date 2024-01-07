package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.exception.UserNotFoundException;
import com.catalyst.XpressPayments.model.User;

public interface UserService {
    User findById(Integer id) throws UserNotFoundException;

    User findUserByEmail(String email) throws UserNotFoundException;
}
