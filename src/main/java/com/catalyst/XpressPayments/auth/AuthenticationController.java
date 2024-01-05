package com.catalyst.XpressPayments.auth;


import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthenticationResponse> signUp(
            @RequestBody SignUpRequest request
    ){
        return ResponseEntity.ok(authenticationService.signUp(request));
    }


    @PostMapping("/logIn")
    public ResponseEntity<AuthenticationResponse> logIn(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
