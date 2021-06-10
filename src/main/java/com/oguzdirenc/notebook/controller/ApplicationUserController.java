package com.oguzdirenc.notebook.controller;
import com.oguzdirenc.notebook.domain.ApplicationUser;
import com.oguzdirenc.notebook.exception.UserValidationException;
import com.oguzdirenc.notebook.payload.JWTLoginSuccessResponse;
import com.oguzdirenc.notebook.payload.LoginRequest;
import com.oguzdirenc.notebook.security.JwtTokenProvider;
import com.oguzdirenc.notebook.service.ApplicationUserService;
import com.oguzdirenc.notebook.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Objects;

import static com.oguzdirenc.notebook.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserValidator userValidator;

    @Autowired
    public ApplicationUserController(ApplicationUserService applicationUserService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserValidator userValidator) {
        this.applicationUserService = applicationUserService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userValidator = userValidator;
    }

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

  Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername()
                        ,loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody ApplicationUser user, BindingResult result){

        userValidator.validate(user,result);

        if(result.hasFieldErrors()) throw new UserValidationException(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());

        ApplicationUser newUser = applicationUserService.saveUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
