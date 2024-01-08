package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.LogInResponse;
import com.catalyst.XpressPayments.auth.responses.SignUpResponse;
import com.catalyst.XpressPayments.exception.InvalidRequestException;
import com.catalyst.XpressPayments.exception.NotFoundException;
import com.catalyst.XpressPayments.exception.UserAlreadyExistException;

public interface AuthService {
    SignUpResponse signUp(SignUpRequest request) throws UserAlreadyExistException, InvalidRequestException;
    LogInResponse login(LoginRequest request) throws InvalidRequestException;
}
