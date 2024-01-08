package com.catalyst.XpressPayments.auth;


import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.LogInResponse;
import com.catalyst.XpressPayments.auth.responses.SignUpResponse;
import com.catalyst.XpressPayments.exception.InvalidRequestException;
import com.catalyst.XpressPayments.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthServiceImpl authenticationService;


    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) throws InvalidRequestException {
            SignUpResponse response = authenticationService.signUp(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PostMapping("/logIn")
    public ResponseEntity<LogInResponse> logIn(@RequestBody LoginRequest request) throws InvalidRequestException  {
            LogInResponse response = authenticationService.login(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }





}
