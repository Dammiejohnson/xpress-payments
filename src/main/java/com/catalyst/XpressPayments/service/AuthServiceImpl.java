package com.catalyst.XpressPayments.service;

import com.catalyst.XpressPayments.auth.requests.LoginRequest;
import com.catalyst.XpressPayments.auth.requests.SignUpRequest;
import com.catalyst.XpressPayments.auth.responses.LogInResponse;
import com.catalyst.XpressPayments.auth.responses.SignUpResponse;
import com.catalyst.XpressPayments.security.JwtService;
import com.catalyst.XpressPayments.exception.InvalidEmailException;
import com.catalyst.XpressPayments.exception.XpressPaymentException;
import com.catalyst.XpressPayments.model.Role;
import com.catalyst.XpressPayments.model.User;
import com.catalyst.XpressPayments.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements  AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public SignUpResponse signUp(SignUpRequest request) throws XpressPaymentException, InvalidEmailException {
        isValidEmail(request.getEmail());
        isValidPassword(request.getPassword());
        isNameValid(request.getFirstName(), request.getLastName());
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail().toLowerCase());
        if (userOptional.isPresent()) {
            throw new XpressPaymentException("User with this email address already exist !!!");
        } else {
            User user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            User savedUser = userRepository.save(user);

            return SignUpResponse.builder()
                    .message(savedUser.getEmail() + " sign up is successful, Log in to continue.")
                    .build();
        }

    }

    @Override
    public LogInResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                         request.getEmail(),
                         request.getPassword()
                 )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return LogInResponse.builder()
                .message("LogIn is successful")
                .token(jwtToken)
                .build();

    }


    private void isValidEmail(String email) throws InvalidEmailException {
//        email must only contain letters, numbers, underscores, hyphens, and periods
//        email must contain an @ symbol
//        email must contain . after @ symbol
        if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
            throw new InvalidEmailException(email + " is not a valid Email address");
        }
    }

    private void isValidPassword(String password) throws XpressPaymentException {
//        password must have at least 8 characters, 1 upper case, 1 number, 1 special character
//        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$");
        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~@$!%^(#){/}*?&])[A-Za-z\\d@~$!%^(#){/}*?&]{8,}")) {
            throw new XpressPaymentException("Invalid password: Password must have at least 8 characters, 1 upper case, 1 number, 1 special character");
        }
    }

    private void isNameValid(String firstname, String lastname) throws XpressPaymentException {
        if (!firstname.matches("^[A-Za-z-']{2,30}$") || !lastname.matches("^[A-Za-z-']{2,30}$"))
            throw new XpressPaymentException("Invalid Firstname or Lastname");
    }
}
