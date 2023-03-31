package com.store.user.mocks;

import com.store.user.models.dtos.security.CredentialDto;
import com.store.user.models.dtos.security.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthMock {
    public static CredentialDto credential() {
        return new CredentialDto("joão@email.com", "joao123");
    }

    public static Token token() {
        return new Token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaXNzIjoiU3RvcmUiLCJleHAiOjE2ODAyMDY5NzB9.v67lSDZMysHxfcoO7Ul5-wtogebgchfEvfL8et33od0");
    }
}
