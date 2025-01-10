package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByEmail(String email);

    @Query("{$and: [{email: ?0}, {$or: [{roles: {$elemMatch: {authority: 'ROLE_USER'}}}, " +
            "{roles: {$elemMatch: {authority: 'ROLE_ADMIN'}}}]}]}")
    UserEntity findByUsernameAndRoles(String username);
}
