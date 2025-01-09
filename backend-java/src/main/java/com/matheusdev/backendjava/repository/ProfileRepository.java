package com.matheusdev.backendjava.repository;

import com.matheusdev.backendjava.entities.ProfileEntity;
import com.matheusdev.backendjava.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<ProfileEntity, String> {

    ProfileEntity findByObjectId(String objectId);

    ProfileEntity findByUser(UserEntity user);

    ProfileEntity findByUsername(String username);
}
