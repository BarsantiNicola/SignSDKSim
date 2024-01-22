package org.example.signsdkdemo.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.signsdkdemo.domain.exceptions.UnauthorizedException;
import org.example.signsdkdemo.domain.services.SessionService;
import org.example.signsdkdemo.infrastructure.models.Session;
import org.example.signsdkdemo.infrastructure.repository.SessionRepository;
import org.example.signsdkdemo.infrastructure.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final TokenRepository tokenRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void checkSession(String session, String origin) throws UnauthorizedException {
        sessionRepository
                .getSessionBySessionToken(session)
                .filter(token-> token.getOrigin().equals(origin))
                .orElseThrow(UnauthorizedException::new);
    }

    @Override
    public Session checkAuth(String token, String origin) throws UnauthorizedException {

        return tokenRepository.getAuthTokenByToken(token).map( authToken -> {
            Session sessionToken = buildSessionToken(origin, authToken.getDuration());
            sessionRepository.insert(sessionToken);
            return sessionToken;

        }).orElseThrow(UnauthorizedException::new);
    }

    private Session buildSessionToken(String origin, int duration ){
        UUID uuid = UUID.randomUUID();
        return Session
                .builder()
                .origin(origin)
                .sessionToken(uuid.toString())
                .duration(duration).build();
    }
}
