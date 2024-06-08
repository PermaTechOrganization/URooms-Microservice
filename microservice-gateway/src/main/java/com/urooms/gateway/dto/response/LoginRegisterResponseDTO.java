package com.urooms.gateway.dto.response;

public class LoginRegisterResponseDTO {

    private String username;
    private String sessionId;
    private String userId;

    public LoginRegisterResponseDTO(String username, String sessionId, String userId) {
        this.username = username;
        this.sessionId = sessionId;
        this.userId = userId;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
