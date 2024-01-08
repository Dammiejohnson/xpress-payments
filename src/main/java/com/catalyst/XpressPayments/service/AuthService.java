package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.LogInResponse;
import com.catalyst.XpressPayments.auth.responses.SignUpResponse;
import com.catalyst.XpressPayments.exception.InvalidRequestException;


public interface AuthService {
    SignUpResponse signUp(SignUpRequest request) throws InvalidRequestException;
    LogInResponse login(LoginRequest request) throws InvalidRequestException;
}
