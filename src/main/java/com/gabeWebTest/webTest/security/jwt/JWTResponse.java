package com.gabeWebTest.webTest.security.jwt;

public class JWTResponse {

    public JWTResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JWTResponse() {
    }

    private String jwtToken;
}
