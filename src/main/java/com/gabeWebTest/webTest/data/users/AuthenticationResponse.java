package com.gabeWebTest.webTest.data.users;

public class AuthenticationResponse {

    private String token;
    private String message;
    private boolean isSuccessful;

    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthenticationResponse(String token, String message, boolean isSuccessful) {
        this.token = token;
        this.message = message;
        this.isSuccessful = isSuccessful;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
