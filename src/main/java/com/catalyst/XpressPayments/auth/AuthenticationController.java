package com.catalyst.XpressPayments.auth;


import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.LogInResponse;
import com.catalyst.XpressPayments.auth.responses.SignUpResponse;
import com.catalyst.XpressPayments.exception.InvalidEmailException;
import com.catalyst.XpressPayments.exception.XpressPaymentException;
import com.catalyst.XpressPayments.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthServiceImpl authenticationService;


    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request) throws XpressPaymentException, InvalidEmailException {
        try {
            SignUpResponse response = authenticationService.signUp(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
         }catch (RuntimeException ex){
            throw new XpressPaymentException(ex.getMessage());
        }
    }


    @PostMapping("/logIn")
    public ResponseEntity<LogInResponse> logIn(@RequestBody LoginRequest request) throws XpressPaymentException {
        try {
            LogInResponse response = authenticationService.login(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (RuntimeException ex){
            throw new XpressPaymentException(ex.getMessage());
        }
    }



}
