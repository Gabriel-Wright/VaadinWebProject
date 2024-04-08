package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.users.AuthenticationResponse;
import com.gabeWebTest.webTest.data.users.User;
import com.gabeWebTest.webTest.services.AuthenticationService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/login")
    public ModelAndView redirectToLoginView() {
        return new ModelAndView("redirect:/login-view");
    }

}
