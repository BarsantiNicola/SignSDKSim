package org.example.signsdkdemo.domain.services;

import org.example.signsdkdemo.domain.exceptions.UnauthorizedException;
import org.example.signsdkdemo.infrastructure.models.Session;

public interface SessionService {

    void checkSession( String session, String origin ) throws UnauthorizedException;

    Session checkAuth(String token, String origin) throws UnauthorizedException;
}
