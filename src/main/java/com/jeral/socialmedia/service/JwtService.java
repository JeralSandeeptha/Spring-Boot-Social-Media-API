package com.jeral.socialmedia.service;

public interface JwtService {
    public String generateToken(Object user, String username);
}
