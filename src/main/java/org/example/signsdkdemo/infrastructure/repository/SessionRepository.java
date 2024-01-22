package org.example.signsdkdemo.infrastructure.repository;

import org.example.signsdkdemo.infrastructure.models.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends MongoRepository<Session,String> {

    Optional<Session> getSessionBySessionToken(String token);
}
