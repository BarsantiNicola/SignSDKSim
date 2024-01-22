package org.example.signsdkdemo.infrastructure.repository;

import org.example.signsdkdemo.infrastructure.models.AuthToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<AuthToken,String> {

    Optional<AuthToken> getAuthTokenByToken(String token);
}
