package com.g09.webshopspringboot.domain;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse {

    boolean verified;
    Role role;


    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LoginResponse(boolean verified, Role role) {
        this.verified = verified;
        this.role = role;
    }

    public LoginResponse() {
    }
}
