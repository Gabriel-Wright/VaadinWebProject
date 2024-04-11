package com.gabeWebTest.webTest.controllers;

import com.gabeWebTest.webTest.data.users.AuthenticationResponse;
import com.gabeWebTest.webTest.data.users.User;
import com.gabeWebTest.webTest.services.AuthenticationService;
//import com.gabeWebTest.webTest.views.security.LoginView;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(
            User request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AuthenticationResponse> login (
//            User request
//    ) {
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login (User request, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        if(authenticationResponse.isSuccessful()) {
            response.setHeader("Authorization","Bearer " + authenticationResponse.getToken());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/upload"));
            // Return a ResponseEntity with headers for the redirect
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        } else {
            // Perform a redirect to the login page with an error flag
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login?error=true").build();
        }
    }

//
//    @PostMapping(value)

//    @GetMapping("/login")
//    public ResponseEntity<Object> redirectToLoginView() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", "/login-view");
//        return new ResponseEntity<>(headers, HttpStatus.FOUND);
//    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create() {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(false); // do not create one
//        if (session == null) {
//            logger.warn("Session is null.");
//        } else {
//            logger.warn("Session id = {}", session.getId());
//        }
//    }

}
