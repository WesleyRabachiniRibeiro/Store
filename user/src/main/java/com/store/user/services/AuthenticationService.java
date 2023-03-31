package com.store.user.services;

import com.store.user.models.dtos.security.CredentialDto;
import com.store.user.models.dtos.security.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

    Token auth(CredentialDto credentialDto) throws AuthenticationException;
    String generateToken(Authentication authentication);
}
