package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.LogInResponse;
import com.catalyst.XpressPayments.auth.responses.SignUpResponse;
import com.catalyst.XpressPayments.exception.InvalidEmailException;
import com.catalyst.XpressPayments.exception.UserNotFoundException;
import com.catalyst.XpressPayments.exception.XpressPaymentException;

public interface AuthService {
    SignUpResponse signUp(SignUpRequest request) throws XpressPaymentException, UserNotFoundException, InvalidEmailException;
    LogInResponse login(LoginRequest request);
}
