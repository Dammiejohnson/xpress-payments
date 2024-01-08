package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.model.User;

public interface UserService {
    User findById(Integer id) throws NotFoundException;

    User findUserByEmail(String email) throws NotFoundException;
}
