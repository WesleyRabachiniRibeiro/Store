package com.store.user.services;

public interface TokenService {

    boolean verifyToken(String token);
    Long returnUserId(String token);
}
