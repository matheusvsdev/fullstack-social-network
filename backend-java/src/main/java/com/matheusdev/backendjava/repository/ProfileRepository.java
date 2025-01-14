package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {

    ProfileEntity findByUser(UserEntity user);

    @Query("{ '$or': [ { 'username': { '$regex': ?0, '$options': 'i' } } ] }")
    Page<ProfileEntity> findByUsername(String username, Pageable pageable);
}
