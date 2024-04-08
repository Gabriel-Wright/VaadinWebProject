package com.gabeWebTest.webTest.security.jwt;

public class JWTRequest {

    public JWTRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JWTRequest() {
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
