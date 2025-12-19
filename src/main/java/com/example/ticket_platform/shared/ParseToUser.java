package com.example.ticket_platform.shared;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class ParseToUser {

    public UUID parseToUser(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }
}
